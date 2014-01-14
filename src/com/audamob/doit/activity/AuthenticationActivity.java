package com.audamob.doit.activity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
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

import com.audamob.doit.model.Account;
import com.audamob.doit.third.AbstractConnectManager;
import com.audamob.doit.third.facebook.FacebookConnectManager;
import com.audamob.doit.third.googleplus.MomentUtil;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;

import de.passsy.holocircularprogressbar.HoloCircularProgressBar;

public class AuthenticationActivity extends Activity implements
		PlusClient.ConnectionCallbacks, PlusClient.OnConnectionFailedListener,
		PlusClient.OnAccessRevokedListener {

	// Buttons
	RelativeLayout btnFbLogin;

	AbstractConnectManager facebookAbstractConnectManager;
	AbstractConnectManager googleAbstractConnectManager;
	Activity MainActivity_activity;
	private HoloCircularProgressBar mHoloCircularProgressBar;
	private ObjectAnimator mProgressBarAnimator;
	RelativeLayout Login_Layout, Progress_Layout;
	TextView Text_Loading;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authentfication_activity_layout);

	/*	Intent intent = new Intent(AuthenticationActivity.this,
				MainContainerActivity.class);
		startActivity(intent);
		this.finish();*/
		
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
		Login_Layout = (RelativeLayout) findViewById(R.id.Login_layout);
		Text_Loading = (TextView) findViewById(R.id.text_loading);
		Progress_Layout = (RelativeLayout) findViewById(R.id.Progress_Layout);
		mHoloCircularProgressBar = (HoloCircularProgressBar) findViewById(R.id.progressBar_loading);
		animate(mHoloCircularProgressBar, animator);

	}

	private AnimatorListener animator = new AnimatorListener() {

		@Override
		public void onAnimationCancel(final Animator animation) {
			animation.end();
		}

		@Override
		public void onAnimationEnd(final Animator animation) {
			Log.d("Animator", "End");
			mHoloCircularProgressBar.setProgress(0f);
			animate(mHoloCircularProgressBar, this);
		}

		@Override
		public void onAnimationRepeat(final Animator animation) {
		}

		@Override
		public void onAnimationStart(final Animator animation) {
		}
	};
	/***
	 * Google+ Implementation
	 */

	private static final int DIALOG_GET_GOOGLE_PLAY_SERVICES = 1;

	private static final int REQUEST_CODE_SIGN_IN = 1;
	private static final int REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES = 2;

	RelativeLayout mSignInButton;
	private PlusClient mPlusClient;

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
			Log.d("Dialog", "5");
			return super.onCreateDialog(id);
		}

		int available = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		Log.d("Dialog", "1");
		if (available == ConnectionResult.SUCCESS) {
			Log.d("Dialog", "2");
			return null;
		}
		if (GooglePlayServicesUtil.isUserRecoverableError(available)) {
			Log.d("Dialog", "3");
			return GooglePlayServicesUtil.getErrorDialog(available, this,
					REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES);
		}
		Log.d("Dialog", "4");
		return new AlertDialog.Builder(this)
				.setMessage(R.string.plus_generic_error).setCancelable(true)
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

		mPlusClient.connect();
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		String currentPersonName = mPlusClient.getCurrentPerson() != null ? mPlusClient
				.getCurrentPerson().getDisplayName()
				: getString(R.string.unknown_person);
		Account Person_Account = null;
		try {

			Person_Account = CacheReadWriteUtil.restoreAccount(this);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (Person_Account == null) {
			Log.d("Account",""+mPlusClient.getCurrentPerson().toString());
			Person_Account = new Account(
					mPlusClient.getCurrentPerson().getId(), mPlusClient
							.getCurrentPerson().getDisplayName(),
					"https://plus.google.com/s2/photos/profile/"
							+ mPlusClient.getCurrentPerson().getId()
							+ "?sz=200", mPlusClient.getCurrentPerson()
							.getBirthday(), mPlusClient.getCurrentPerson()
							.getCurrentLocation(), mPlusClient
							.getCurrentPerson().getNickname());
			Log.d("Account", "Url : " + mPlusClient.getCurrentPerson().getUrl());
			try {
				CacheReadWriteUtil.saveAccount(Person_Account, this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Intent intent = new Intent(AuthenticationActivity.this,
				MainContainerActivity.class);
		startActivity(intent);
		this.finish();

	}

	@Override
	public void onDisconnected() {

		mPlusClient.connect();

	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		mConnectionResult = result;
		Progress_Layout.setVisibility(View.INVISIBLE);
		Login_Layout.setVisibility(View.VISIBLE);
		Text_Loading
				.setText(getResources().getString(R.string.connection_text));
	}

	private void animate(final HoloCircularProgressBar progressBar,
			final AnimatorListener listener) {
		final float progress = 1f;
		int duration = 3000;
		animate(progressBar, listener, progress, duration);
	}

	private void animate(final HoloCircularProgressBar progressBar,
			final AnimatorListener listener, final float progress,
			final int duration) {

		mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress",
				progress);
		mProgressBarAnimator.setDuration(duration);

		mProgressBarAnimator.addListener(listener);
		mProgressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {

			}
		});

		mProgressBarAnimator.start();

	}

}
