package com.audamob.doit.third.facebook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.audamob.doit.model.User;
import com.audamob.doit.third.AbstractConnectManager;
import com.audamob.doit.utils.ApplicationConstants;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.view.activity.MainContainerActivity;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class FacebookConnectManager extends Activity implements
		AbstractConnectManager {


	// Instance of Facebook Class
	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private Facebook facebook;
	private SharedPreferences mPrefs;
	private SharedPreferences.Editor editor;
	public Activity mainActivity;

	/**
	 * Constructor of FacebookConnectManager
	 * @param mPrefsFromAuthentificationActivity
	 * @param mainActivity_activity
	 */
	public FacebookConnectManager(SharedPreferences mPrefsFromAuthentificationActivity, Activity mainActivity_activity,Facebook fb) {
		this.facebook = fb;
		this.mAsyncRunner = new AsyncFacebookRunner(facebook);
		this.mPrefs = mPrefsFromAuthentificationActivity;
		this.mainActivity = mainActivity_activity;
	}

	/**
	 * Function to login into facebook
	 * */
	@Override
	public void login() {

		String access_token = this.mPrefs.getString("access_token", null);
		long expires = this.mPrefs.getLong("access_expires", 0);
		this.editor = this.mPrefs.edit();
		
		if (access_token != null) {
			this.facebook.setAccessToken(access_token);
		}

		if (expires != 0) {
			this.facebook.setAccessExpires(expires);
		}
		if(this.facebook.isSessionValid())
			logout();
		
		if (!this.facebook.isSessionValid()) {
			Log.d("FACEBOOK", "not isSessionValid");

			this.facebook.authorize(this.mainActivity,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							Log.d("FACEBOOK", "onCancel : ");
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							editor.putString("access_token",
									facebook.getAccessToken());
							Log.d("FACEBOOK", "facebook.getAccessToken() : "+facebook.getAccessToken());

							editor.putLong("access_expires",
									facebook.getAccessExpires());
							Log.d("FACEBOOK", "facebook.getAccessExpires() : "+facebook.getAccessExpires());

							editor.commit();

							Log.d("finish - Complete","finish - Complete");

							//Une fois connecté, on récupére les infos de l'utilisateur 
							getProfileInformation();
							
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error
							Log.d("FACEBOOK", "onError : ");

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors
							Log.d("FACEBOOK", "onFacebookError : ");

						}

					});
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		this.facebook.authorizeCallback(requestCode, resultCode, data);
	}

	/**
	 * Get Profile information by making request to Facebook Graph API
	 * */
	@Override
	public User getProfileInformation() {
		Log.d("FACEBOOK","getProfileInformation");
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				final User account;				
				Log.d("FACEBOOK","Profile : "+response);
				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);

					// getting infos of the user
					final String id = profile.getString("id");
					Log.d("FACEBOOK","id :"+id);
					
					final String name = profile.getString("name");
					Log.d("FACEBOOK","name :"+name);
					
					final String gender = profile.getString("gender");
					Log.d("FACEBOOK","gender :"+gender);
					
					// getting location
					final String locale = profile.getString("location");
					JSONObject localeJson = new JSONObject(locale);
					final String location = localeJson.getString("name");
					Log.d("FACEBOOK","location :"+location);
					
					// getting work employer name
					final String work = profile.getString("work");
					Log.d("FACEBOOK","work :"+work);
					JSONObject workJson = new JSONObject(work);
					final String employer = workJson.getString("employer");
					Log.d("FACEBOOK","employer :"+employer);
					JSONObject employerJson = new JSONObject(employer);
					final String employerName = employerJson.getString("name");
					Log.d("FACEBOOK","employerName :"+employerName);

					final String email = profile.getString("email");					
					Log.d("FACEBOOK","email :"+email);

					String profilePicture = ApplicationConstants.FACEBOOK_IMG_BASE_URL
							+ id + "/picture?width=200&height=200";
					
					//Set informations in account
					account = new User(id, name, profilePicture, "",
							location, employerName, gender, "language",0);
					
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							try {
								CacheReadWriteUtil.saveAccount(account, mainActivity);
								
								//Starting MainActivity
								Intent intent = new Intent(mainActivity,
										MainContainerActivity.class);
								mainActivity.startActivity(intent);
								
								//Kill the Authentication Activity
								mainActivity.finish();
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}

					});

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
		return null;
	}

	/**
	 * Function to post to facebook wall
	 * */
	@Override
	public void postToWall() {
		// post on user's wall.
		facebook.dialog(this.mainActivity, "feed", new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {
			}

			@Override
			public void onError(DialogError e) {
			}

			@Override
			public void onComplete(Bundle values) {
			}

			@Override
			public void onCancel() {
			}
		});

	}

	/**
	 * Function to show Access Tokens
	 * */
	@Override
	public void showAccessTokens(Context context) {
		String access_token = facebook.getAccessToken();

		Toast.makeText(context,
				"Access Token: " + access_token, Toast.LENGTH_LONG).show();
	}

	/**
	 * Function to Logout user from Facebook
	 * */
	@Override
	public void logout() {
		mAsyncRunner.logout(this.mainActivity, new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Logout from Facebook", response);
				if (Boolean.parseBoolean(response) == true) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							//Activities TODO when we Logout the user
							// For Example : Redirection to the Starter Activity's Page
						}

					});

				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}

}