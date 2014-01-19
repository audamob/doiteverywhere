package com.audamob.doit.model;

import java.io.Serializable;

public class User implements Serializable {

	String mId;
	String mDisplayName;
	String mImageUrl;
	String mBirthday;
	String mCurrentLocation;

	String PosteOrganisation;
	String Gender;
	String Language;

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
		PosteOrganisation = posteOrganisation;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public User(String id, String displayname, String image,
			String birthday, String currentlocation, String postOrganisation,
			String Gender, String Language) {
		// TODO Auto-generated constructor stub
		this.mId = id;
		this.mDisplayName = displayname;
		this.mImageUrl = image;
		this.mBirthday = birthday;
		this.mCurrentLocation = currentlocation;

		this.PosteOrganisation = postOrganisation;
		this.Gender = Gender;
		this.Language = Language;

	}

	public String getLanguage() {
		return this.Language;
	}

	public String getGender() {
		return this.Gender;
	}

	public String getPosteOrganisation() {
		return this.PosteOrganisation;
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

}
