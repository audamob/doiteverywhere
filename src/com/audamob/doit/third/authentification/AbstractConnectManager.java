package com.audamob.doit.third.authentification;

import android.content.Context;
import android.view.View.OnClickListener;

import com.audamob.doit.model.User;

public interface AbstractConnectManager {

	void login();

	User getProfileInformation();

	void postToWall();

	void logout();
	
	void showAccessTokens(Context context);

}
