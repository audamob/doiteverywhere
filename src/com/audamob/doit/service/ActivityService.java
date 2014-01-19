package com.audamob.doit.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.service.manager.ActivityServiceManager;

public class ActivityService {
	
	ActivityServiceManager doActivityServiceManager;
	
	
	public ActivityService(Context context) {
		doActivityServiceManager = new ActivityServiceManager(context, "activity.json");
	}

	public void addNewActivity() {

	}

	public void getActivityDetails() {

	}

	public List<DoItActivity> getAllActivities() {
		
		List<DoItActivity> activities = new ArrayList<DoItActivity>();
		activities = doActivityServiceManager.getActivities();
		return activities;

	}

	public void getActivitiesByCreteria() {

	}

	public void addNewFollowToActivity() {

	}

}
