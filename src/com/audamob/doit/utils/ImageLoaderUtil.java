package com.audamob.doit.utils;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.audamob.doit.R;

public class ImageLoaderUtil {

	ImageView mImageView,mImageViewBlurred;
	Context mContext;
	String mStringUrl;
	String mStringBaseUrl;
	Bitmap mBitmap_profile, mBitmap_activity;
	Bitmap mBitmapBlurred;
	int mUserType;
	String mUserID;

	public ImageLoaderUtil(int userType, ImageView imageview,ImageView blurred, Context context,
			String baseUrl, String url, String userID) {
		mImageView = imageview;
		mUserType = userType;
		mContext = context;
		mStringUrl = url;
		mUserID=userID;
		mStringBaseUrl = baseUrl;
		mImageViewBlurred=blurred;
		
		ThreadLoadProfilePicture ThreadLoader_profile = new ThreadLoadProfilePicture(handlerLoad);
		ThreadLoader_profile.start();
	}
	public Bitmap getImageBlurred(){
		
		return mBitmapBlurred;
	}
	public ImageLoaderUtil(ImageView imageActivity, Activity activity,
			String imageUrl) {
		mImageView = imageActivity;
		mContext = activity;
		mStringUrl = imageUrl;
		ThreadLoadActivityPicture ThreadLoader_Activity = new ThreadLoadActivityPicture(
				handlerLoad_Activity);
		ThreadLoader_Activity.start();
	}

	final Handler handlerLoad = new Handler() {

		public void handleMessage(Message msg) {
			try {

				RoundedAvatarDrawable rAvatar=new RoundedAvatarDrawable(mBitmap_profile);
				
					
				mImageView.setImageDrawable(rAvatar);
				
				if(mImageViewBlurred!=null){

					Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_followers);
					Log.d("DoItEvryWhere","niveau de code avant blurred");
					mImageViewBlurred.setImageBitmap(GraphicsUtil.fastblur(mBitmap_profile, 40));
				}
				
				Log.d("DoItEvryWhere","niveau de code apres blurred");
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	};
	final Handler handlerLoad_Activity = new Handler() {

		public void handleMessage(Message msg) {
			try {
				RoundedAvatarDrawable rAvatar = new RoundedAvatarDrawable(
						mBitmap_activity);

				mImageView.setImageDrawable(rAvatar);
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
			Bitmap bm = getUserPic(mStringBaseUrl, mStringUrl,
					mImageView.getLayoutParams().width,
					mImageView.getLayoutParams().height, mUserID);
			mBitmap_profile = bm;

			Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			msg.setData(b);
			mHandler.sendMessage(msg);

		}

		public Bitmap getUserPic(String baseUrl, String url, int width,
				int height, String userID) {
			String imageURL;
			Bitmap bitmap = null;
			int measuredWidth = width;
			int measuredHieght = height;
			
			String suffixUrl = "";
			if (mUserType == 0) {
				suffixUrl = "/picture";
			}
			Log.d("FACEBOOK", "userType : " + mUserType);

			imageURL = baseUrl + userID + suffixUrl + "?width=" + measuredWidth
					+ "&height=" + measuredHieght;
			Log.d("FACEBOOK", "imageURL : " + imageURL);

			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						imageURL).getContent());
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
					mImageView.getLayoutParams().height, mUserID);
			mBitmap_activity = bm;
			Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			msg.setData(b);
			mHandler.sendMessage(msg);

		}

		public Bitmap getUserPic(String url, int width, int height,
				String userID) {
			String imageURL = url;
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
