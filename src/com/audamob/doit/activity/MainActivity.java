package com.audamob.doit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.audamob.doit.R;
import com.audamob.doit.third.AbstractConnectManager;
import com.audamob.doit.third.FacebookConnectManager;

public class MainActivity extends Activity {

	// Buttons
	Button btnFbLogin;
	Button btnFbGetProfile;
	AbstractConnectManager facebookAbstractConnectManager;
	AbstractConnectManager googleAbstractConnectManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnFbLogin = (Button) findViewById(R.id.btn_fblogin);
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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
