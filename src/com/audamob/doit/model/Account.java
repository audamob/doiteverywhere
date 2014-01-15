package com.audamob.doit.model;

import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mId;
	private String mDisplayName;
	private String mImageUrl;
	private String mBirthday;
	private String mCurrentLocation;
	private String mNickName;

	/**
	 * Default Constructor
	 */
	public Account() {

	}

	public Account(String id, String displayname, String image,
			String birthday, String currentlocation, String nickname) {
		// TODO Auto-generated constructor stub
		this.mId = id;
		this.mDisplayName = displayname;
		this.mImageUrl = image;
		this.mBirthday = birthday;
		this.mCurrentLocation = currentlocation;
		this.mNickName = nickname;

	}

	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}

	public String getDisplayName() {
		return mDisplayName;
	}

	public void setDisplayName(String mDisplayName) {
		this.mDisplayName = mDisplayName;
	}

	public String getImageUrl() {
		return mImageUrl;
	}

	public void setImageUrl(String mImageUrl) {
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

	public String getmNickName() {
		return mNickName;
	}

	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
	}

}
