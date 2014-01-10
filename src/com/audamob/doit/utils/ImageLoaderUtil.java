package com.audamob.doit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageLoaderUtil {

	ImageView mImageView;
	Context mContext;
	String mStringUrl;
	Bitmap mBitmap_profile,mBitmap_activity;
	
    String mUserID;
	public ImageLoaderUtil(ImageView imageview, Context context, String url,String userID) {
		// TODO Auto-generated constructor stub
		mImageView = imageview;
		mContext = context;
		mStringUrl = url;
		mUserID=userID;
		
		ThreadLoadProfilePicture ThreadLoader_profile = new ThreadLoadProfilePicture(handlerLoad);
		ThreadLoader_profile.start();
	}

	public ImageLoaderUtil(ImageView imageActivity, Activity activity,
			String imageUrl) {
		mImageView = imageActivity;
		mContext = activity;
		mStringUrl = imageUrl;
		ThreadLoadActivityPicture ThreadLoader_Activity = new ThreadLoadActivityPicture(handlerLoad_Activity);
		ThreadLoader_Activity.start();
		// TODO Auto-generated constructor stub
	}

	final Handler handlerLoad = new Handler() {

		public void handleMessage(Message msg) {
			try {
				mImageView.setImageBitmap(mBitmap_profile);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	};
	final Handler handlerLoad_Activity = new Handler() {

		public void handleMessage(Message msg) {
			try {
				mImageView.setImageBitmap(mBitmap_activity);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	};

	private class ThreadLoadProfilePicture extends Thread {
		Handler mHandler;

		ThreadLoadProfilePicture(Handler h) {
			mHandler = h;
		}

		public void run() {
			//String imageURL = "https://plus.google.com/s2/photos/profile/117212352335398744452?sz=50";
			Bitmap bm = getUserPic(mStringUrl,
					mImageView.getLayoutParams().width,
					mImageView.getLayoutParams().height,
					mUserID);
			mBitmap_profile = bm;
         Log.d("RoundedPicture","117212352335398744452::"+mUserID);
			Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			msg.setData(b);
			mHandler.sendMessage(msg);

		}

		public Bitmap getUserPic(String url, int width, int height,
				String userID) {
			String imageURL;
			Bitmap bitmap = null;
			int measuredWidth = (width / 2) - 5;
			int measuredHieght = (height * 70) / 100;
			Log.d("", "Loading Picture");
			imageURL = "https://plus.google.com/s2/photos/profile/" + userID
					+ "?width=" + measuredWidth + "&height=" + measuredHieght;

			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						imageURL).getContent());
				bitmap = GraphicsUtil.GetBitmapClippedCircle(bitmap);
				bitmap = GraphicsUtil.addRoundedBorder(bitmap);
			} catch (Exception e) {
				Log.d("TAG", "Loading Picture FAILED");
				e.printStackTrace();
			}
			return bitmap;
		}
	}

	private class ThreadLoadActivityPicture extends Thread {
		Handler mHandler;

		ThreadLoadActivityPicture(Handler h) {
			mHandler = h;
		}

		public void run() {
			
			Bitmap bm = getUserPic(mStringUrl,
					mImageView.getLayoutParams().width,
					mImageView.getLayoutParams().height,
					mUserID);
			mBitmap_activity = bm;
    		Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			msg.setData(b);
			mHandler.sendMessage(msg);

		}

		public Bitmap getUserPic(String url, int width, int height,
				String userID) {
			String imageURL=url;
			Bitmap bitmap = null;
			
			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						imageURL).getContent());
				bitmap = GraphicsUtil.GetBitmapClippedCircle(bitmap);
				bitmap = GraphicsUtil.addRoundedBorder(bitmap);
			} catch (Exception e) {
				Log.d("TAG", "Loading Picture FAILED");
				e.printStackTrace();
			}
			return bitmap;
		}
	}

}
