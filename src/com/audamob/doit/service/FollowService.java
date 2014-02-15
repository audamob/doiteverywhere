package com.audamob.doit.service;

import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.audamob.doit.model.User;
import com.audamob.doit.service.manager.ActivityServiceManager;
import com.audamob.doit.service.manager.FollowServiceManager;

public class FollowService {

	FollowServiceManager doFollowServiceManager;

	
	public FollowService(Activity currentContext) {
		doFollowServiceManager = new FollowServiceManager(currentContext);
	}
	
	/**
	 * Add New Follow to the selected Activity
	 * @param activityId
	 * @param followersId
	 */
	public void addNewFollowToActivity(String activityId, String followersId) {
		Log.e("FOLLOW"," je suis ici 2 ");
		doFollowServiceManager.addNewFollowToActivity(activityId, followersId);

	}

	/**
	 * Get the user'information / Stream activities
	 * 
	 * @return Account
	 */
	public User cancelFollow(String followId) {
		return null;

	}

}
