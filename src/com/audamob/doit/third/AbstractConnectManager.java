package com.audamob.doit.third;

import com.audamob.doit.model.Account;

public interface AbstractConnectManager {

	void login();

	Account getProfileInformation();

	void postToWall();

	void logout();

}
