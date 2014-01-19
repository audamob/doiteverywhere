package com.audamob.doit.model;

import java.io.Serializable;

public class DoItActivity implements Serializable {

	private String doIdActivity;
	
	private String doDisplayName;
	private String doPicture;
	private String doCategoryName;
	private int doNbreFollowers;
	private String doDescription;
	private String doState;
	private String doVideos;
	private String doImages;
	private String doComments;
	private String doStartDate;
	private String doEndDate;
	
	
	public String getDoIdActivity() {
		return doIdActivity;
	}

	public void setDoIdActivity(String doIdActivity) {
		this.doIdActivity = doIdActivity;
	}

	public String getDoVideos() {
		return doVideos;
	}

	public void setDoVideos(String doVideos) {
		this.doVideos = doVideos;
	}

	public String getDoImages() {
		return doImages;
	}

	public void setDoImages(String doImages) {
		this.doImages = doImages;
	}

	public String getDoComments() {
		return doComments;
	}

	public void setDoComments(String doComments) {
		this.doComments = doComments;
	}

	public String getDoStartDate() {
		return doStartDate;
	}

	public void setDoStartDate(String doStartDate) {
		this.doStartDate = doStartDate;
	}

	public String getDoEndDate() {
		return doEndDate;
	}

	public void setDoEndDate(String doEndDate) {
		this.doEndDate = doEndDate;
	}

	public DoItActivity() {
		// TODO Auto-generated constructor stub
	}
	
	public DoItActivity(String doDisplayName, String doPicture,
			String doCategoryName, int doNbreFollowers, String doDescription,
			String doState) {
		super();
		this.doDisplayName = doDisplayName;
		this.doPicture = doPicture;
		this.doCategoryName = doCategoryName;
		this.doNbreFollowers = doNbreFollowers;
		this.doDescription = doDescription;
		this.doState = doState;
	}

	public String getDoDisplayName() {
		return doDisplayName;
	}
	public void setDoDisplayName(String doDisplayName) {
		this.doDisplayName = doDisplayName;
	}
	public String getDoPicture() {
		return doPicture;
	}
	public void setDoPicture(String doPicture) {
		this.doPicture = doPicture;
	}
	public String getDoCategoryName() {
		return doCategoryName;
	}
	public void setDoCategoryName(String doCategoryName) {
		this.doCategoryName = doCategoryName;
	}
	public int getDoNbreFollowers() {
		return doNbreFollowers;
	}
	public void setDoNbreFollowers(int doNbreFollowers) {
		this.doNbreFollowers = doNbreFollowers;
	}
	public String getDoDescription() {
		return doDescription;
	}
	public void setDoDescription(String doDescription) {
		this.doDescription = doDescription;
	}
	public String getDoState() {
		return doState;
	}
	public void setDoState(String doState) {
		this.doState = doState;
	}
	
	
	
	
	
	
}
