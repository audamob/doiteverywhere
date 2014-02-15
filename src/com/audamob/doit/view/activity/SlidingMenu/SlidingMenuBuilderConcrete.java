package com.audamob.doit.view.activity.SlidingMenu;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.audamob.doit.R;
import com.audamob.doit.model.SlidingMenuListItem;
import com.audamob.doit.view.activity.ProfileActivity;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 *         This is concrete builder class, which extends base builder and can
 *         override default, add new list items click actions.
 * 
 */
public class SlidingMenuBuilderConcrete extends SlidingMenuBuilderBase {

	// We can define actions, which will be called, when we press on separate
	// list items. These actions can override default actions defined inside the
	// base builder. Also, you can create new actions, which will added to the
	// default ones.
	@Override
	public void onListItemClick(SlidingMenuListItem selectedSlidingMenuListItem) {
		
		switch (selectedSlidingMenuListItem.Id) {
		case 0:
			menu.toggle();
			return;

		case 2:
			menu.toggle();
			Intent intentProfile = new Intent(activity, ProfileActivity.class);
			activity.startActivity(intentProfile);
			activity.overridePendingTransition(R.anim.push_down_in,
					R.anim.push_down_out);
			break;

		default:
			break;
		}
	}

}
