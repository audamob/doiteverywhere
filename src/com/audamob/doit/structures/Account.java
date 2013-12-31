package com.audamob.doit.structures;

import java.io.Serializable;

public class Account implements Serializable {

	
	String mDisplayName;
	String mImageUrl;
	
	public Account(String displayname,String imageurl) {
		// TODO Auto-generated constructor stub
		this.mDisplayName=displayname;
		this.mImageUrl=imageurl;
		
	}
}
