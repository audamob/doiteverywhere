package com.audamob.doit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.audamob.doit.R;
import com.audamob.doit.third.AbstractConnectManager;
import com.audamob.doit.third.FacebookConnectManager;

public class MainActivity extends Activity {

	// Buttons
	RelativeLayout btnFbLogin;
	Button btnFbGetProfile;
	AbstractConnectManager facebookAbstractConnectManager;
	AbstractConnectManager googleAbstractConnectManager;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnFbLogin = (RelativeLayout) findViewById(R.id.FB_Login);
		btnFbGetProfile = (Button) findViewById(R.id.btn_get_profile);

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
		btnFbGetProfile.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				facebookAbstractConnectManager.getProfileInformation();
			}
		});

		RelativeLayout gp_login=(RelativeLayout)findViewById(R.id.GP_Login);
		gp_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i_gp=new Intent(MainActivity.this, SignInActivity.class);
				startActivity(i_gp);
			}
		});
	}

	
}
