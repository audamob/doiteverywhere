package com.audamob.doit.model;

import java.io.Serializable;

public class User implements Serializable {

	private String mId;
	private String mDisplayName;
	private String mImageUrl;
	private String mBirthday;
	private String mCurrentLocation;
	private int mUserType;
	private String mPosteOrganisation;
	private String mGender;
	private String mLanguage;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmDisplayName() {
		return mDisplayName;
	}

	public void setmDisplayName(String mDisplayName) {
		this.mDisplayName = mDisplayName;
	}

	public String getmImageUrl() {
		return mImageUrl;
	}

	public void setmImageUrl(String mImageUrl) {
		this.mImageUrl = mImageUrl;
	}

	public String getmBirthday() {
		return mBirthday;
	}

	public void setmBirthday(String mBirthday) {
		this.mBirthday = mBirthday;
	}

	public String getmCurrentLocation() {
		return mCurrentLocation;
	}

	public void setmCurrentLocation(String mCurrentLocation) {
		this.mCurrentLocation = mCurrentLocation;
	}

	public void setPosteOrganisation(String posteOrganisation) {
		mPosteOrganisation = posteOrganisation;
	}

	public void setGender(String gender) {
		mGender = gender;
	}

	public void setLanguage(String language) {
		mLanguage = language;
	}

	public User(String id, String displayname, String image,
			String birthday, String currentlocation, String postOrganisation,
			String Gender, String Language,int userType) {
		// TODO Auto-generated constructor stub
		this.mId = id;
		this.mDisplayName = displayname;
		this.mImageUrl = image;
		this.mBirthday = birthday;
		this.mCurrentLocation = currentlocation;
		this.mUserType = userType; // 0 : Facebook User , 1 : Google User
		this.mPosteOrganisation = postOrganisation;
		this.mGender = Gender;
		this.mLanguage = Language;

	}

	public String getLanguage() {
		return this.mLanguage;
	}

	public String getGender() {
		return this.mGender;
	}

	public String getPosteOrganisation() {
		return this.mPosteOrganisation;
	}

	public String getUserId() {
		return mId;
	}

	public String getProfileName() {
		return mDisplayName;
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public String getProfileBirthday() {
		return mBirthday;
	}

	public String getProfileLocation() {
		return mCurrentLocation;
	}

	public int getmUserType() {
		return mUserType;
	}

	public void setmUserType(int mUserType) {
		this.mUserType = mUserType;
	}

}
