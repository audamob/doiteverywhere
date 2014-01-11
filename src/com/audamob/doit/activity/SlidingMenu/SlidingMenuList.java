package com.audamob.doit.activity.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import com.audamob.doit.R;
import com.audamob.doit.model.SlidingMenuListItem;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public final class SlidingMenuList {

	/**
	 * Building a list that will be used as a data for list fragment, which is a
	 * sliding menu content.
	 * 
	 * @param activity
	 * @return List filled with SlidingMenuListItem objects.
	 */
	public static final List<SlidingMenuListItem> getSlidingMenu(
			Activity activity) {

		List<SlidingMenuListItem> list = new ArrayList<SlidingMenuListItem>();

		SlidingMenuListItem MenuItem;
		MenuItem=new SlidingMenuListItem(2, activity.getResources().getString(R.string.Profile_text), 
				activity.getResources().getDrawable(R.drawable.ic_profile));
		list.add(MenuItem);
		MenuItem=new SlidingMenuListItem(0, activity.getResources().getString(R.string.Nearby_text), 
				activity.getResources().getDrawable(R.drawable.ic_nearby));
		list.add(MenuItem);
		
		MenuItem=new SlidingMenuListItem(1, activity.getResources().getString(R.string.Stream_text), 
				activity.getResources().getDrawable(R.drawable.ic_stream));
		list.add(MenuItem);
		
		MenuItem=new SlidingMenuListItem(1, activity.getResources().getString(R.string.Interested_text), 
				activity.getResources().getDrawable(R.drawable.ic_stream));
		list.add(MenuItem);
		
		MenuItem=new SlidingMenuListItem(1, activity.getResources().getString(R.string.Explore_text), 
				activity.getResources().getDrawable(R.drawable.ic_stream));
		list.add(MenuItem);
		
		MenuItem=new SlidingMenuListItem(3, activity.getResources().getString(R.string.Following_text), 
				activity.getResources().getDrawable(R.drawable.ic_stream));
		list.add(MenuItem);
		
		MenuItem=new SlidingMenuListItem(3, activity.getResources().getString(R.string.Settings_text), 
				activity.getResources().getDrawable(R.drawable.ic_settings));
		list.add(MenuItem);
		
		return list;
	}
}