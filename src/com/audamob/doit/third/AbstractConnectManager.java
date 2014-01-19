package com.audamob.doit.third;

import com.audamob.doit.model.User;

public interface AbstractConnectManager {

	void login();

	User getProfileInformation();

	void postToWall();

	void logout();

}
