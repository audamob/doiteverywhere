package com.audamob.doit.service.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.audamob.doit.adapter.DoItActivitiesListAdapter;
import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.utils.ApplicationConstants;
import com.audamob.doit.utils.JSONParser;

public class ActivityServiceManager extends AsyncTask<String, String, String> {

	// Progress Dialog
	private ProgressDialog pDialog;
	private ArrayList<DoItActivity> activities;

	public ArrayList<DoItActivity> getActivities() {
		return this.activities;
	}

	public void setActivities(ArrayList<DoItActivity> activities) {
		this.activities = activities;
	}

	// Creating JSON Parser object
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> activitiesList;

	// products JSONArray
	JSONArray activityJSONArray = null;

	// ALL JSON node names
	private static final String TAG_ACTIVITY_SET = "activitySet";
	private static final String TAG_ID = "id_activity";
	private static final String TAG_NAME = "name";
	private static final String TAG_PICTURE = "picture";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_CATEGORY = "category";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_IMAGES = "images";
	private static final String TAG_VIDEOS = "videos";
	private static final String TAG_COMMENTS = "comments";
	private static final String TAG_FOLLOWERS = "followers";
	private static final String TAG_START_DATE = "start_date";
	private static final String TAG_END_DATE = "end_date";
	private static final String PATH = "/doiteverywhere/json_mock";

	private Activity currentContext;
	private String fileName = "activity.json";
	private ListView doListDoItActivities;

	public ActivityServiceManager(Activity currentContext, String fileName,
			ListView doListDoItActivities) {
		this.doListDoItActivities = doListDoItActivities;
		this.currentContext = currentContext;
		activities = new ArrayList<DoItActivity>();
		this.fileName = fileName;

	}

	public ActivityServiceManager(Activity currentContext) {
		this.currentContext = currentContext;
	}
	
	public ActivityServiceManager(){
		
	}

	/**
	 * Get Activities from Mock Json file ( TEMPORAIRE )
	 */
	public ArrayList<DoItActivity> getActivitiesFromMockFile(String fileName) {
		String jsonContent = readFromFile(fileName);

		try {

			JSONObject json = new JSONObject(jsonContent);

			// Check your log cat for JSON reponse
			Log.d("All Activites JSON: ", json.toString());

			activityJSONArray = json.getJSONArray(TAG_ACTIVITY_SET);
			// looping through All messages

			for (int i = 0; i < activityJSONArray.length(); i++) {
				JSONObject c = activityJSONArray.getJSONObject(i);

				// Create activity Object
				DoItActivity activity = new DoItActivity();
				activity.setDoIdActivity(c.getString(TAG_ID));
				activity.setDoDisplayName(c.getString(TAG_NAME));
				activity.setDoCategoryName(c.getString(TAG_CATEGORY));
				activity.setDoDescription(c.getString(TAG_DESCRIPTION));
				activity.setDoPicture(c.getString(TAG_PICTURE));
				activity.setDoNbreFollowers(c.getString(TAG_FOLLOWERS));
				activity.setDoLocation(c.getString(TAG_LOCATION));
				activity.setDoComments(c.getString(TAG_COMMENTS));
				activity.setDoVideos(c.getString(TAG_VIDEOS));
				activity.setDoImages(c.getString(TAG_IMAGES));
				activity.setDoStartDate(c.getString(TAG_START_DATE));
				activity.setDoEndDate(c.getString(TAG_END_DATE));

				activities.add(activity);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Log.i("activities size", "" + activities.size());
		return activities;

		// End Get Activities from Json File

	}

	/**
	 * TO Read from the json Mock File
	 * @param fileName
	 * @return
	 */
	private String readFromFile(String fileName) {
		
		String fileContent = "";
/*
		try (BufferedReader br = new BufferedReader(new FileReader(
				PATH+fileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				fileContent = fileContent + sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		return fileContent;
	}

	/**
	 * Get Activities from Json File
	 */
	public ArrayList<DoItActivity> getActivitiesFromJsonFile(String url) {

		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		// getting JSON string from URL
		JSONObject json = jsonParser.makeHttpRequest(url, "GET", params);

		// Check your log cat for JSON reponse
		Log.d("All Activites JSON: ", json.toString());

		try {
			activityJSONArray = json.getJSONArray(TAG_ACTIVITY_SET);
			// looping through All messages

			for (int i = 0; i < activityJSONArray.length(); i++) {
				JSONObject c = activityJSONArray.getJSONObject(i);

				// Create activity Object
				DoItActivity activity = new DoItActivity();
				activity.setDoIdActivity(c.getString(TAG_ID));
				activity.setDoDisplayName(c.getString(TAG_NAME));
				activity.setDoCategoryName(c.getString(TAG_CATEGORY));
				activity.setDoDescription(c.getString(TAG_DESCRIPTION));
				activity.setDoPicture(c.getString(TAG_PICTURE));
				activity.setDoNbreFollowers(c.getString(TAG_FOLLOWERS));
				activity.setDoLocation(c.getString(TAG_LOCATION));
				activity.setDoComments(c.getString(TAG_COMMENTS));
				activity.setDoVideos(c.getString(TAG_VIDEOS));
				activity.setDoImages(c.getString(TAG_IMAGES));
				activity.setDoStartDate(c.getString(TAG_START_DATE));
				activity.setDoEndDate(c.getString(TAG_END_DATE));

				activities.add(activity);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		Log.i("activities size", "" + activities.size());
		return activities;

		// End Get Activities from Json File
	}

	
	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(this.currentContext);
		pDialog.setMessage("Loading activities ...\nPlease wait");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
	}

	/**
	 * getting Inbox JSON
	 * */
	protected String doInBackground(String... args) {

		setActivities(getActivitiesFromJsonFile(ApplicationConstants.SERVICES_API_URL
				+ this.fileName));
		
		//FIXME : TMP jusquau correction du problème de l'API de service
		//setActivities(getActivitiesFromMockFile("activity.json"));
		return null;
	}

	/**
	 * After completing background task Dismimport
	 * fr.audasoft.facemash.activity.FaceMashActivity; import
	 * fr.audasoft.facemash.util.JSONParser; import
	 * fr.audasoft.faemash.model.Face; iss the progress dialog
	 * **/
	protected void onPostExecute(String file_url) {
		// dismiss the dialog after getting all products
		pDialog.dismiss();
		Log.d("APIAPI", "1 : " + this.currentContext);
		Log.d("APIAPI", "size: " + getActivities().size());
		Log.d("APIAPI", "s " + " " + this.doListDoItActivities);
		this.doListDoItActivities.setAdapter(new DoItActivitiesListAdapter(
				this.currentContext, getActivities()));
	}
	
	public static void main(String[] args) {
		ActivityServiceManager asm = new ActivityServiceManager();
		System.out.println("content : "+asm.readFromFile("activity.json"));
	}
}
