package com.audamob.doit.utils;

import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ProfileDataUtil {

	/**
	 * Function loads the users facebook profile pic
	 * 
	 * @param userID
	 */
	public static BitmapDrawable getUserPic(String url) {
		Log.d("Account","url:"+url);
		String imageURL=url;
		Bitmap bitmap = null;
	
		try {
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageURL).getContent());
		/*			
			bitmap = GraphicsUtil.GetBitmapClippedCircle(bitmap);
			bitmap = GraphicsUtil.addRoundedBorder(bitmap);
			*/
		} catch (Exception e) {
			Log.d("TAG", "Loading Picture FAILED");
			e.printStackTrace();
		}
		return  new BitmapDrawable(bitmap);
	}
	
	/**
	 * Function loads the users facebook profile pic
	 * 
	 * @param userID
	 */
	
}
