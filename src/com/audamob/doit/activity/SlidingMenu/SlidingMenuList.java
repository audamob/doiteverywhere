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

		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_angry_id,
				activity.getResources().getString(
						R.slidingmenu.list_item_angry_label), activity
						.getResources().getString(
								R.slidingmenu.list_item_angry_icon)));

		list.add(new SlidingMenuListItem(R.slidingmenu.list_item_basic_id,
				activity.getResources().getString(
						R.slidingmenu.list_item_basic_label), activity
						.getResources().getString(
								R.slidingmenu.list_item_basic_icon)));


		return list;
	}
}