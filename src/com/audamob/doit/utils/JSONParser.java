package com.audamob.doit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest(String url, String method,
			List<NameValuePair> params) {

		HttpEntity httpEntity;
		Log.e("FOLLOW"," JSON : 1");
		// Making HTTP request
		try {

			// check for request method
			if (method == "POST") {
				// request method is POST
				// defaultHttpClient
				Log.e("FOLLOW"," JSON : 2");
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
				try {
					HttpResponse httpResponse = httpClient.execute(httpPost);
				} catch (Exception e) {
					// TODO: handle exception
					Log.e("FOLLOW"," error catch "+e);
				}
				
//				Log.e("FOLLOW"," JSON : 55");
//				if (httpResponse != null) {
//					Log.e("FOLLOW"," JSON : 66");
//					httpEntity = httpResponse.getEntity();
//					Log.e("FOLLOW"," JSON : 77");
//					is = httpEntity.getContent();
//					Log.e("FOLLOW"," JSON : 4 : "+is);
//				} else {
//					Log.e("FOLLOW", "httpResponse is null ");
//				}

			} else if (method == "PUT") {
				Log.e("FOLLOW"," JSON : lé lé");
				// request method is PUT
				// defaultHttpClient
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPut = new HttpPost(url);
				Log.e("httpPut", httpPut.toString());
				httpPut.setEntity(new UrlEncodedFormEntity(params));

				HttpResponse httpResponse = httpClient.execute(httpPut);
				int status = httpResponse.getStatusLine().getStatusCode();

				if (status == HttpStatus.SC_OK) {
					httpEntity = httpResponse.getEntity();
					json = httpEntity != null ? EntityUtils.toString(
							httpEntity, "iso-8859-1") : null;
				} else {
					Log.e("Server responded with error status ", "" + status);
				}

			} else if (method == "GET") {
				// request method is GET
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(params, "utf-8");
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				if (httpResponse != null) {
					httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
				} else {
					Log.e("JSON Parser", "httpResponse is null ");
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Log.e("FOLLOW"," JSON : 11");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			Log.e("FOLLOW"," JSON : 12");
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("FOLLOW"," JSON : 13");
		}

		try {
			if (is != null) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				json = sb.toString();
			} else {
				Log.e("JSON Parser", "is( httpEntity.getContent() ) - is null ");
			}
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}