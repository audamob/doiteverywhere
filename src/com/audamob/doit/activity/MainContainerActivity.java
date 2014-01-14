package com.audamob.doit.activity;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.audamob.doit.R;
import com.audamob.doit.UiComponent.SwipeyTabs;
import com.audamob.doit.activity.SlidingMenu.ActivityBase;
import com.audamob.doit.activity.SlidingMenu.SlidingMenuBuilderConcrete;
import com.audamob.doit.adapter.SwipeyTabsPagerAdapter;

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
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.withe)));
		activity = this;
		setContentView(R.layout.swipeytab_layout);
		ChangeCurrentFragment(2);

	}

	public static String[] CreateTableString(int i) {
		switch (i) {
		case 1:
			String[] tab_1 = { activity.getResources().getString(
					R.string.Done_text) };
			return tab_1;

		default:

			String[] tab_2 = {
					activity.getResources().getString(R.string.Done_text),
					activity.getResources()
							.getString(R.string.In_Progress_text) };
			return tab_2;

		}

	}

	public static void ChangeCurrentFragment(int i) {
		SwipeyTabs mTabs;
		ViewPager mViewPager;
		FragmentManager fragmentManager;
		mViewPager = (ViewPager) activity.findViewById(R.id.viewpager);
		mTabs = (SwipeyTabs) activity.findViewById(R.id.swipeytabs);
		fragmentManager = activity.getSupportFragmentManager();
		try {
			mViewPager.removeAllViews();

		} catch (Exception e) {
			// TODO: handle exception
		}

		String[] TITLES = CreateTableString(i);
		Log.d("ID_Tabs", "Id : " + 1);

		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(activity,
				fragmentManager, TITLES, mViewPager, i);
		mViewPager.setAdapter(adapter);
		mTabs.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(mTabs);

		mViewPager.setCurrentItem(0);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		super.onResume();

	}

}
