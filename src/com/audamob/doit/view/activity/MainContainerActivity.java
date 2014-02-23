package com.audamob.doit.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.audamob.doit.R;
import com.audamob.doit.tool.adapter.SwipeyTabsPagerAdapter;
import com.audamob.doit.view.activity.SlidingMenu.ActivityBase;
import com.audamob.doit.view.activity.SlidingMenu.SlidingMenuBuilderConcrete;
import com.viewpagerindicator.TabPageIndicator;

public class MainContainerActivity extends ActivityBase {

	// Your need to put this method in every Activity class where you want to
	// have sliding menu.
	@Override
	public Class<?> setSlidingMenu() {
		// Each activity can have it's own sliding menu controlling builder
		// class.
		return SlidingMenuBuilderConcrete.class;
	}

	@Override
	public boolean enableHomeIconActionSlidingMenu() {
		return true;
	}

	// private static String[] TITLES = new String[4];

	public static String PlaylistName = "";

	public static MainContainerActivity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		/*
		 * ActionBar bar = getActionBar(); bar.setBackgroundDrawable(new
		 * ColorDrawable(getResources().getColor( R.color.flat_action_bar)));
		 */
		// bar.setH
		activity = this;
		setContentView(R.layout.layout_main_container);
		ChangeCurrentFragment(2);

	}

	public static String[] CreateTableString(int i) {
		switch (i) {
		case 1:
			String[] tab_1 = { activity.getResources().getString(
					R.string.Done_text) };
			return tab_1;
		case 4:
			String[] tab_4 = {
					activity.getResources().getString(R.string.Nearby_text),
					activity.getResources().getString(R.string.Suggestion_Text),
					activity.getResources().getString(R.string.Categorie_Text) };
			return tab_4;
		case 5:
			String[] tab_5 = { activity.getResources().getString(
					R.string.Settings_text) };
			return tab_5;
		default:

			String[] tab_2 = {
					activity.getResources().getString(R.string.Nearby_text),
					activity.getResources().getString(R.string.To_Do_text),
					activity.getResources()
							.getString(R.string.In_Progress_text),
					activity.getResources().getString(R.string.Done_text) };

			return tab_2;

		}

	}

	public static void ChangeCurrentFragment(int i) {
		ViewPager pager = (ViewPager) activity.findViewById(R.id.pager);
		switch (i) {
		case 3:
			pager.setCurrentItem(1);
			break;

		default:
			pager.setCurrentItem(0);
			break;
		}

		switch (i) {
		case 1:
			ShowHeaderTabs(false);
			break;
		case 5:
			ShowHeaderTabs(false);
			break;

		default:
			ShowHeaderTabs(true);
			break;
		}

		FragmentManager fragmentManager;
		fragmentManager = activity.getSupportFragmentManager();

		try {
			pager.removeAllViews();
		} catch (Exception e) {
		}

		String[] TITLES = CreateTableString(i);
		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(activity,
				fragmentManager, TITLES, pager, i);

		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) activity
				.findViewById(R.id.indicator);
		indicator.setViewPager(pager);

	}

	public static void ShowHeaderTabs(Boolean b) {
		if (b) {
			activity.findViewById(R.id.indicator).setVisibility(View.VISIBLE);
		} else {
			activity.findViewById(R.id.indicator).setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();

	}

}
