package com.audamob.doit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class ActivityService {

	public void addNewProfile(){
		
	}
	
	public void addNewActivity(){
		
	}
	
	public void getProfileInformation(){
		
	}
	
	public void getCategoriesByCreteria(){
		
	}
	
	public void getActivitiesByCreteria(){
		
	}

	public void getUsers(){
		
	}
	
//	public void sendRequest(String face_id, int face_won, int face_lost,
//			String location,String name) {
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost post_data = new HttpPost("http://audahub.fr/facemash/newVote.php");
//
//		try {
//			List<NameValuePair> postValues = new ArrayList<NameValuePair>(1);
//			postValues.add(new BasicNameValuePair("id", face_id));
//			postValues.add(new BasicNameValuePair("won", "" + face_won));
//			postValues.add(new BasicNameValuePair("lost", "" + face_lost));
//			postValues.add(new BasicNameValuePair("location", location));
//			postValues.add(new BasicNameValuePair("name", name));
//			post_data.setEntity(new UrlEncodedFormEntity(postValues));
//
//			String line;
//			HttpResponse response = httpclient.execute(post_data);
//			InputStream is = response.getEntity().getContent();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//			while ((line = rd.readLine()) != null) {
//				Log.d("line : ", line);
//			}
//		} catch (ClientProtocolException e) {
//			Log.d("line : ", e.toString());// host not found
//		} catch (IOException e) {
//			Log.d("line : ", e.toString());
//		}
//	}
//	
//	public void getInfos(String face_id) {
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost post_data = new HttpPost(
//				"http://audahub.fr/facemash/getinfos.php");
//
//		try {
//			List<NameValuePair> postValues = new ArrayList<NameValuePair>(1);
//			postValues.add(new BasicNameValuePair("id", face_id));
//			post_data.setEntity(new UrlEncodedFormEntity(postValues));
//
//			String line;
//			HttpResponse response = httpclient.execute(post_data);
//			InputStream is = response.getEntity().getContent();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//			while ((line = rd.readLine()) != null) {
//				Log.d("line : ", line);
//			}
//		} catch (ClientProtocolException e) {
//			Log.d("line : ", e.toString());// host not found
//		} catch (IOException e) {
//			Log.d("line : ", e.toString());
//		}
//	}
}
