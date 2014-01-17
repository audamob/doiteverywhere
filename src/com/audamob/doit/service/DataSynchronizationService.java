package com.audamob.doit.service;

import android.os.AsyncTask;

/**
 * Background Async Task to Load all Datas by making HTTP Request
 * */

public class DataSynchronizationService extends
		AsyncTask<String, String, String> {

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

//	// Progress Dialog
//	private ProgressDialog pDialog;
//	public List<Face> faceList;
//	
//	private String friensArray = "";
//	private String friensName = "";
//	private String friensWon = "";
//	private String friensLost = "";
//
//	// Creating JSON Parser object
//	JSONParser jsonParser = new JSONParser();
//
//	ArrayList<HashMap<String, String>> placeList;
//
//	// products JSONArray
//	JSONArray place = null;
//
//	// ALL JSON node names
//	private static final String TAG_MESSAGES = "messages";
//	private static final String TAG_ID = "id";
//	private static final String TAG_NAME = "name";
//	private static final String TAG_WON = "won";
//	private static final String TAG_LOST = "lost";
//	private static final String TAG_LOCATION = "location";
//
//	private static final String CHOOSEN_FRIENDS = "choosen_friends";
//	private static final String CHOOSEN_FRIENDS_NAME = "choosen_friendsname";
//	private static final String CHOOSEN_FRIENDS_WON = "choosen_friendswon";
//	private static final String CHOOSEN_FRIENDS_LOST = "choosen_friendslost";
//	private static final String CHOOSEN_MASH_TYPE = "choosen_mash_type";
//
//	
//	// Places JSON url
//	private static final String FACEMASH_JSON_URL = "http://audahub.fr//facemash/json/";
//
//	private Context currentContext;
//	private String fileName ="random.json";
//
//	public DataSynchronizationService(Context currentContext,String fileName) {
//		this.currentContext = currentContext;
//		faceList = new ArrayList<Face>();
//		this.fileName = fileName;
//
//	}
//
//	/**
//	 * Get Places from Json File
//	 */
//	public List<Face> getFacesFromJsonFile(String url) {
//
//		// this.placesDS.truncatPlaceTable();
//		// Building Parameters
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//		// getting JSON string from URL
//		JSONObject json = jsonParser.makeHttpRequest(url, "GET", params);
//
//		// Check your log cat for JSON reponse
//		Log.d("Random Faces JSON: ", json.toString());
//
//		try {
//			place = json.getJSONArray(TAG_MESSAGES);
//			// looping through All messages
//
//			for (int i = 0; i < place.length(); i++) {
//				JSONObject c = place.getJSONObject(i);
//
//				// Storing each json item in variable
//				String id = c.getString(TAG_ID);
//				String name = c.getString(TAG_NAME);
//				int won = c.getInt(TAG_WON);
//				int lost = c.getInt(TAG_LOST);
//				String location = c.getString(TAG_LOCATION);
//
//				// Create combinaison Object
//				Face face = new Face();
//				face.setId_face(id);
//				face.setName(name);
//				face.setWon(won);
//				face.setLost(lost);
//				face.setLocation(location);
//
//				faceList.add(face);
//			}
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		Log.i("faceList size", "" + faceList.size());
//		return faceList;
//
//		// End Get Places from Json File
//	}
//
//	/**
//	 * Before starting background thread Show Progress Dialog
//	 * */
//	@Override
//	protected void onPreExecute() {
//		super.onPreExecute();
//		pDialog = new ProgressDialog(this.currentContext);
//		pDialog.setMessage("Loading Random Faces ...\nPlease wait");
//		pDialog.setIndeterminate(false);
//		pDialog.setCancelable(false);
//		pDialog.show();
//	}
//
//	/**
//	 * getting Inbox JSON
//	 * */
//	protected String doInBackground(String... args) {
//
//		getFacesFromJsonFile(FACEMASH_JSON_URL+this.fileName);
//		return null;
//	}
//
//	/**
//	 * After completing background task Dismimport fr.audasoft.facemash.activity.FaceMashActivity;
//import fr.audasoft.facemash.util.JSONParser;
//import fr.audasoft.faemash.model.Face;
//iss the progress dialog
//	 * **/
//	protected void onPostExecute(String file_url) {
//		// dismiss the dialog after getting all products
//		
//		for (Face randomFace : faceList) {
//			friensArray = friensArray + randomFace.getId_face() + ",";
//
//			friensName = friensName + randomFace.getName() + ",";
//
//			friensWon = friensWon + randomFace.getWon() + ",";
//
//			friensLost = friensLost + randomimport fr.audasoft.facemash.activity.FaceMashActivity;
//			import fr.audasoft.facemash.util.JSONParser;
//			import fr.audasoft.faemash.model.Face;
//Face.getLost() + ",";
//
//		}
//		Log.i("array ",""+friensArray.split(",").length);
//		Log.i("friensName ",""+friensName.split(",").length);
//		Log.i("friensWon",""+friensWon.split(",").length);
//		Log.i("friensLost ",""+friensLost.split(",").length);
//		
//		Intent settingIntent = new Intent(this.currentContext,
//				FaceMashActivity.class);
//		settingIntent.putExtra(CHOOSEN_FRIENDS, friensArray);
//		settingIntent.putExtra(CHOOSEN_FRIENDS_NAME, friensName);
//		settingIntent.putExtra(CHOOSEN_FRIENDS_WON, friensWon);
//		settingIntent.putExtra(CHOOSEN_FRIENDS_LOST, friensLost);
//		settingIntent.putExtra(CHOOSEN_MASH_TYPE, "random");
//		this.currentContext.startActivity(settingIntent);
//
//		pDialog.dismiss();
//
//	}

}
