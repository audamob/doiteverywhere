package com.audamob.doit.service;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.ListView;

import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.service.manager.ActivityServiceManager;

public class ActivityService {

	ActivityServiceManager doActivityServiceManager;

	public ActivityService(Activity context, ListView doListDoItActivities) {
		doActivityServiceManager = new ActivityServiceManager(context,
				"activity.json", doListDoItActivities);
		doActivityServiceManager.execute();
	}

	public void addNewActivity() {

	}

	public void getActivityDetails() {

	}

	/**
	 * Get All Activities in the selected Category
	 * @return ArrayList<DoItActivity>
	 */
	public ArrayList<DoItActivity> getAllActivities() {

		ArrayList<DoItActivity> activities = new ArrayList<DoItActivity>();
		activities = doActivityServiceManager.getActivities();
		return activities;

	}

	public void getActivitiesByCreteria() {

	}

	/**
	 * Add New Follow to the selected Activity
	 * @param activityId
	 * @param followersId
	 */
	public void addNewFollowToActivity(String activityId, String followersId) {

		doActivityServiceManager.addNewFollowToActivity(activityId, followersId);

	}

}
