package com.audamob.doit.model;

import java.io.Serializable;

public class DoItActivity implements Serializable {

	
	String mDisplayName;
	String mImageUrl;
	
	
	public DoItActivity(String displayname,String image) {
		// TODO Auto-generated constructor stub
	
		this.mDisplayName=displayname;
		this.mImageUrl=image;
		
		
	}
	
	public String getName(){
		return mDisplayName;
	}
	public String getImageUrl(){
		return mImageUrl;
	}
	
	
	
	
}
