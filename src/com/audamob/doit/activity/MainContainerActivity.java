package com.audamob.doit.activity;

import java.util.ArrayList;
import java.util.List;

import com.audamob.doit.*;
import com.audamob.doit.activity.SlidingMenu.ActivityBase;
import com.audamob.doit.activity.SlidingMenu.SlidingMenuBuilderConcrete;
import com.audamob.doit.adapter.SwipeyTabsAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 *         This activity demonstrates simple and more optimised approach how a
 *         sliding menu can be added to our activity. Because this Activity
 *         extends our made ActivityBase class, all the work of creating sliding
 *         menu is done there. So, every time you want to add sliding menu to
 *         any activity, you just need to extend base activity class and
 *         override setSlidingMenu method. Also you need to provide a concrete
 *         sliding menu builder class, which defines, what actions to do, when
 *         you press on separate list items from the menu.
 * 
 */
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

	private static String[] TITLES = new String[4];

	public static String PlaylistName = "";
	private SwipeyTabs mTabs;
	private ViewPager mViewPager;
	public static Activity activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		activity = this;
		TITLES[0] = getResources().getString(R.string.Nearby_text);
		TITLES[1] = getResources().getString(R.string.To_Do_text);
		TITLES[2] = getResources().getString(R.string.In_Progress_text);
		TITLES[3] = getResources().getString(R.string.Done_text);

		setContentView(R.layout.activity_swipeytab);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.withe)));

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTabs = (SwipeyTabs) findViewById(R.id.swipeytabs);

		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(this,
				getSupportFragmentManager());
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

	private class SwipeyTabsPagerAdapter extends FragmentPagerAdapter implements
			SwipeyTabsAdapter {

		private final Context mContext;

		public SwipeyTabsPagerAdapter(Context context, FragmentManager fm) {
			super(fm);

			this.mContext = context;
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return SwipeyTabFragment_1.newInstance(TITLES[position]);
			} else if (position == 1) {
				return SwipeyTabFragment_2.newInstance(TITLES[position]);
			} else if (position == 2) {
				return SwipeyTabFragment_3.newInstance(TITLES[position]);
			} else {
				return SwipeyTabFragment_4.newInstance(TITLES[position]);
			}
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		public TextView getTab(final int position, SwipeyTabs root) {
			TextView view = (TextView) LayoutInflater.from(mContext).inflate(
					R.layout.swipey_tab_indicator, root, false);
			view.setText(TITLES[position]);
			view.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mViewPager.setCurrentItem(position);
				}
			});

			return view;
		}

	}

}
