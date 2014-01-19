package com.audamob.doit.third.facebook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.audamob.doit.activity.MainContainerActivity;
import com.audamob.doit.model.User;
import com.audamob.doit.third.AbstractConnectManager;
import com.audamob.doit.utils.ApplicationConstants;
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
	public FacebookConnectManager(SharedPreferences mPrefsFromAuthentificationActivity, Activity mainActivity_activity) {
		facebook = new Facebook(ApplicationConstants.FACEBOOK_APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(facebook);
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
		editor = this.mPrefs.edit();
		
		if (access_token != null) {
			facebook.setAccessToken(access_token);

			Log.d("FB Sessions", "" + facebook.isSessionValid());
		}

		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		if (!facebook.isSessionValid()) {
			facebook.authorize(this.mainActivity,
					new String[] { "email", "publish_stream" },
					new DialogListener() {

						@Override
						public void onCancel() {
							// Function to handle cancel event
						}

						@Override
						public void onComplete(Bundle values) {
							// Function to handle complete event
							// Edit Preferences and update facebook acess_token
							
							editor.putString("access_token",
									facebook.getAccessToken());
							editor.putLong("access_expires",
									facebook.getAccessExpires());
							editor.commit();
							
							Log.d("finish - Complete","finish - Complete");
							//Starting MainActivity
							Intent intent = new Intent(mainActivity,
									MainContainerActivity.class);
							startActivity(intent);
							
							//Kill the Authentication Activity
							mainActivity.finish();
						}

						@Override
						public void onError(DialogError error) {
							// Function to handle error

						}

						@Override
						public void onFacebookError(FacebookError fberror) {
							// Function to handle Facebook errors

						}

					});
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}

	/**
	 * Get Profile information by making request to Facebook Graph API
	 * */
	@Override
	public User getProfileInformation() {
		final User account = new User();
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);

					// getting name of the user
					final String name = profile.getString("name");
					account.setmDisplayName(name);

					// getting email of the user
					final String email = profile.getString("email");

					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Name: " + name + "\nEmail: " + email,
									Toast.LENGTH_LONG).show();
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
	public void showAccessTokens() {
		String access_token = facebook.getAccessToken();

		Toast.makeText(getApplicationContext(),
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