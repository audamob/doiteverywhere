package com.audamob.doit.model;

import android.graphics.drawable.Drawable;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class SlidingMenuListItem {
	public int Id;
	public String Name;
	public Drawable IconResourceId;
	

	public SlidingMenuListItem() {
	}

	public SlidingMenuListItem(int id, String name, Drawable iconResourceId) {
		this.Id = id;
		this.Name = name;
		this.IconResourceId = iconResourceId;
		
	}
}
