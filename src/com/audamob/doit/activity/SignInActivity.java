/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.audamob.doit.activity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;
import com.audamob.doit.R;
import com.audamob.doit.third.MomentUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SignInActivity extends Activity implements 
        PlusClient.ConnectionCallbacks, PlusClient.OnConnectionFailedListener,
        PlusClient.OnAccessRevokedListener {

    private static final int DIALOG_GET_GOOGLE_PLAY_SERVICES = 1;

    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final int REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES = 2;

  
    private PlusClient mPlusClient;
    private SignInButton mSignInButton;
   
    private ConnectionResult mConnectionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        mPlusClient = new PlusClient.Builder(this, this, this)
                .setActions(MomentUtil.ACTIONS)
                .build();

     
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  int available = GooglePlayServicesUtil.isGooglePlayServicesAvailable(SignInActivity.this);
	                if (available != ConnectionResult.SUCCESS) {
	                    showDialog(DIALOG_GET_GOOGLE_PLAY_SERVICES);
	                    return;
	                }

	                try {
	                   
	                    mConnectionResult.startResolutionForResult(SignInActivity.this, REQUEST_CODE_SIGN_IN);
	                } catch (IntentSender.SendIntentException e) {
	                    // Fetch a new result to start.
	                    mPlusClient.connect();
	                }
			}
		});
       
    }

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
          
        } else {
           
            mPlusClient.disconnect();
        }
        mPlusClient.connect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        String currentPersonName = mPlusClient.getCurrentPerson() != null
                ? mPlusClient.getCurrentPerson().getDisplayName()
                : getString(R.string.unknown_person);
      }

    @Override
    public void onDisconnected() {
        mPlusClient.connect();
      
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        mConnectionResult = result;
    }

   
}
