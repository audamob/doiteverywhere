package com.audamob.doit.model;

import java.io.Serializable;

import com.google.android.gms.plus.model.people.Person.Image;

public class Account implements Serializable {

	String mId;
	String mDisplayName;
	String mImageUrl;
	String mBirthday;
	String mCurrentLocation;
	String mNickName;
	
	
	public Account(String id,String displayname,String image,String birthday,String currentlocation,String nickname) {
		// TODO Auto-generated constructor stub
		this.mId=id;
		this.mDisplayName=displayname;
		this.mImageUrl=image;
		this.mBirthday=birthday;
		this.mCurrentLocation=currentlocation;
		this.mNickName=nickname;
		
	}
	public String getUserId(){
		return mId;
	}
	public String getProfileName(){
		return mDisplayName;
	}
	public String getImageUrl(){
		return mImageUrl;
	}
	public String getProfileBirthday(){
		return mBirthday;
	}
	public String getProfileLocation(){
		return mCurrentLocation;
	}
	
	
	
}
