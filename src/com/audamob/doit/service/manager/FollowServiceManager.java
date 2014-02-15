package com.audamob.doit.service.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import com.audamob.doit.utils.ApplicationConstants;
import com.audamob.doit.utils.JSONParser;

public class FollowServiceManager {

	private Activity currentContext;
	private static final String TAG_ID = "activity_id";
	private static final String TAG_FOLLOWER = "user_id";

	public FollowServiceManager(Activity currentContext) {
		this.currentContext = currentContext;
	}
	
	/**
	 * Add New Follower
	 * 
	 * @param args
	 * @return
	 */
	public String addNewFollowToActivity(String... args) {
		String idActivity = args[0];
		String idFollowers = args[1];
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(TAG_ID, idActivity));
		params.add(new BasicNameValuePair(TAG_FOLLOWER, idFollowers));
		JSONParser parser = new JSONParser();
		JSONObject json = parser.makeHttpRequest(
				ApplicationConstants.ACTIVITIES_JSON_URL + "/activity"
						+ idActivity, "PUT", params);

		try {
			Integer success = json.getInt("");
			if (success == 1) {
				// successfully updated
				// send result code 100 to notify about activity update
			} else {
				// failed to update activity
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;

	}
}
