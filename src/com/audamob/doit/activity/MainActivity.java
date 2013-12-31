package com.audamob.doit.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.activity.SlidingMenuImplementation.SlidingMenuFromClassActivity;
import com.audamob.doit.third.AbstractConnectManager;
import com.audamob.doit.third.FacebookConnectManager;
import com.audamob.doit.third.googleplus.MomentUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;

public class MainActivity extends Activity implements
		PlusClient.ConnectionCallbacks, PlusClient.OnConnectionFailedListener,
		PlusClient.OnAccessRevokedListener {

	// Buttons
	RelativeLayout btnFbLogin;
	
	AbstractConnectManager facebookAbstractConnectManager;
	AbstractConnectManager googleAbstractConnectManager;
	Activity MainActivity_activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainActivity_activity = this;
		/**
		 * Google +implementation
		 */

		mPlusClient = new PlusClient.Builder(this, this, this).setActions(
				MomentUtil.ACTIONS).build();
		/*
		 * mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
		 * mSignInButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub int available = GooglePlayServicesUtil
		 * .isGooglePlayServicesAvailable(MainActivity_activity); if (available
		 * != ConnectionResult.SUCCESS) {
		 * showDialog(DIALOG_GET_GOOGLE_PLAY_SERVICES); return; }
		 * 
		 * try {
		 * 
		 * mConnectionResult.startResolutionForResult(MainActivity_activity,
		 * REQUEST_CODE_SIGN_IN); } catch (IntentSender.SendIntentException e) {
		 * // Fetch a new result to start. mPlusClient.connect(); } } });
		 */
		/***
		 * Google +implementation
		 */

		btnFbLogin = (RelativeLayout) findViewById(R.id.FB_Login);
		

		// Instanciate Facebook And Google Connect Manager
		facebookAbstractConnectManager = new FacebookConnectManager();

		/**
		 * Login button Click event
		 * */
		btnFbLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("Image Button", "button Clicked");
				facebookAbstractConnectManager.login();
			}
		});

		/**
		 * Getting facebook Profile info
		 * */
	
		mSignInStatus = (TextView) findViewById(R.id.sign_in_status);
		mSignInButton = (RelativeLayout) findViewById(R.id.GP_Login);
		mSignInButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int available = GooglePlayServicesUtil
						.isGooglePlayServicesAvailable(MainActivity_activity);
				if (available != ConnectionResult.SUCCESS) {
					showDialog(DIALOG_GET_GOOGLE_PLAY_SERVICES);
					return;
				}

				try {
					
					mConnectionResult.startResolutionForResult(
					MainActivity_activity, REQUEST_CODE_SIGN_IN);
				} catch (IntentSender.SendIntentException e) {
					// Fetch a new result to start.
					mPlusClient.connect();
				}
			}
		});

	}

	/***
	 * Google+ Implementation
	 */
	 
   private static final int DIALOG_GET_GOOGLE_PLAY_SERVICES = 1;

   private static final int REQUEST_CODE_SIGN_IN = 1;
   private static final int REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES = 2;

   RelativeLayout mSignInButton ;
   private PlusClient mPlusClient;
   private TextView mSignInStatus;
  
   private ConnectionResult mConnectionResult;
   @Override
   public void onStart() {
       super.onStart();
       mPlusClient.connect();
   }

   @Override
   public void onStop() {
       mPlusClient.disconnect();
       super.onStop();
   }

  
   @Override
   protected Dialog onCreateDialog(int id) {
       if (id != DIALOG_GET_GOOGLE_PLAY_SERVICES) {
           return super.onCreateDialog(id);
       }

       int available = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
       if (available == ConnectionResult.SUCCESS) {
           return null;
       }
       if (GooglePlayServicesUtil.isUserRecoverableError(available)) {
           return GooglePlayServicesUtil.getErrorDialog(
                   available, this, REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES);
       }
       return new AlertDialog.Builder(this)
               .setMessage(R.string.plus_generic_error)
               .setCancelable(true)
               .create();
   }

   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == REQUEST_CODE_SIGN_IN
               || requestCode == REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES) {
           if (resultCode == RESULT_OK && !mPlusClient.isConnected()
                   && !mPlusClient.isConnecting()) {
               // This time, connect should succeed.
               mPlusClient.connect();
           }
       }
   }

   @Override
   public void onAccessRevoked(ConnectionResult status) {
       if (status.isSuccess()) {
           mSignInStatus.setText(R.string.revoke_access_status);
       } else {
           mSignInStatus.setText(R.string.revoke_access_error_status);
           mPlusClient.disconnect();
       }
       mPlusClient.connect();
   }

   @Override
   public void onConnected(Bundle connectionHint) {
       String currentPersonName = mPlusClient.getCurrentPerson() != null
               ? mPlusClient.getCurrentPerson().getDisplayName()
               : getString(R.string.unknown_person);
       mSignInStatus.setText(getString(R.string.signed_in_status, currentPersonName));
       updateButtons(true /* isSignedIn */);
       
       Intent i=new Intent(this, SlidingMenuFromClassActivity.class);
       startActivity(i);
       this.finish();
   }

   @Override
   public void onDisconnected() {
       mSignInStatus.setText(R.string.loading_status);
       mPlusClient.connect();
       updateButtons(false /* isSignedIn */);
   }

   @Override
   public void onConnectionFailed(ConnectionResult result) {
       mConnectionResult = result;
       updateButtons(false /* isSignedIn */);
   }

   private void updateButtons(boolean isSignedIn) {
       if (isSignedIn) {
           mSignInButton.setVisibility(View.INVISIBLE);
          
       } else {
           if (mConnectionResult == null) {
               // Disable the sign-in button until onConnectionFailed is called with result.
               mSignInButton.setVisibility(View.INVISIBLE);
               mSignInStatus.setText(getString(R.string.loading_status));
           } else {
               // Enable the sign-in button since a connection result is available.
               mSignInButton.setVisibility(View.VISIBLE);
               mSignInStatus.setText(getString(R.string.signed_out_status));
           }

         
       }
   }
}
