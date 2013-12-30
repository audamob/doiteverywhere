package com.audamob.doit.structures;

import java.io.Serializable;

public class Account implements Serializable {

	
	String SongName;
	String Url;
	String Featring="";
	
	public Account(String SongName,String url) {
		// TODO Auto-generated constructor stub
		this.SongName=SongName;
		this.Url=url;
		
	}
	public Account(String SongName,String url,String feat) {
		// TODO Auto-generated constructor stub
		this.SongName=SongName;
		this.Url=url;
		this.Featring=feat;
		
	}
	public String getName() {
		return SongName;
	}
	public String getUrl() {
		return Url;
	}
	public String getFeatring() {
		return Featring;
	}
}
