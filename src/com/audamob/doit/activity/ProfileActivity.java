package com.audamob.doit.activity;

import java.io.IOException;

import android.app.ActionBar;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.activity.SlidingMenu.ActivityBase;
import com.audamob.doit.model.User;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.audamob.doit.utils.LayoutResizerUtil;
import com.audamob.doit.utils.ObservableScrollView;

public class ProfileActivity extends ActivityBase implements
		ObservableScrollView.Callbacks {

	private LinearLayout mStickyView;
	private View mPlaceholderView;
	private ObservableScrollView mObservableScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_profile_activity_1);
		mObservableScrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
		mObservableScrollView.setCallbacks(this);

		mPlaceholderView = findViewById(R.id.placeholder);

		mStickyView = (LinearLayout) findViewById(R.id.sticky);

		mStickyView.getLayoutParams().height = LayoutResizerUtil
				.getDisplayHightInPx(this);
		mStickyView.requestLayout();

		mObservableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						onScrollChanged(mObservableScrollView.getScrollY());
					}
				});

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.flat_clouds)));

		ImageView im = (ImageView) findViewById(R.id.profile_picture);

		User ac = null;
		try {
			ac = CacheReadWriteUtil.restoreAccount(this);
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		}

		try {
			ImageLoaderUtil imLoaderUtil = new ImageLoaderUtil(im, this,
					ac.getImageUrl(), ac.getUserId());
		} catch (Exception e) {
			// TODO: handle exception
		}

		/*
		 * UrlImageViewHelper.setUrlDrawableCustom(im.getLayoutParams().width,
		 * im.getLayoutParams().height, im, ac.getImageUrl());
		 */

		Typeface TODO = Typeface.createFromAsset(getAssets(),
				"DinDisplayProThin.otf");
		TextView AccountName, AccountAge, AccountLocation, AccountOrganisation;
		AccountName = (TextView) findViewById(R.id.profile_name_p);
		AccountAge = (TextView) findViewById(R.id.profile_age_p);
		AccountLocation = (TextView) findViewById(R.id.profile_location_p);

		AccountOrganisation = (TextView) findViewById(R.id.profile_Activity_p);
		try {

			AccountName.setText(ac.getProfileName() + ",");
			AccountAge.setText(ac.getProfileBirthday());
			AccountLocation.setText(ac.getProfileLocation());

			AccountOrganisation.setText(ac.getPosteOrganisation());
			AccountAge.setTypeface(TODO);
			AccountLocation.setTypeface(TODO);

			AccountOrganisation.setTypeface(TODO);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// CreateProfileTab(6);

		Resources res = getResources();
		LocalActivityManager mlam = new LocalActivityManager(this, false);

		TabHost host = (TabHost) findViewById(R.id.swipeytabsProfile);
		mlam.dispatchCreate(savedInstanceState);
		host.setup(mlam);
		// Phone Status Activity
		TabSpec statusspec = host.newTabSpec("Phone");
		statusspec.setIndicator("Phone",
				getResources().getDrawable(R.drawable.ic_followers));
		Intent phoneStatusIntent = new Intent(this,
				TestActivity.class);
		statusspec.setContent(phoneStatusIntent);
		// Battery Status Activity
		TabSpec batteryspec = host.newTabSpec("Battery");
		batteryspec.setIndicator("Battery",
				getResources().getDrawable(R.drawable.ic_dialog_about));
		Intent batteryIntent = new Intent(this, TestActivityA.class);
		batteryspec.setContent(batteryIntent);
		// Adding all TabSpec to TabHost
		Log.d("TabHost", "tab = " + host);
		Log.d("TabHost", "tab = " + statusspec);
		Log.d("TabHost", "tab = " + batteryspec);
		host.addTab(statusspec); // Default tab
		host.addTab(batteryspec);

	}

	//
	// public void CreateProfileTab(int i) {
	// SwipeyTabs mTabs;
	// final ViewPager mViewPager;
	//
	// FragmentManager fragmentManager;
	// mViewPager = (ViewPager) findViewById(R.id.viewpagerProfile);
	// mTabs = (SwipeyTabs) findViewById(R.id.swipeytabsProfile);
	// fragmentManager = getSupportFragmentManager();
	//
	// try {
	// mViewPager.removeAllViews();
	// } catch (Exception e) {
	// }
	//
	// String[] TITLES = { getResources().getString(R.string.Suggestion_Text),
	// getResources().getString(R.string.Nearby_text),
	// getResources().getString(R.string.Following_text),
	// getResources().getString(R.string.Follower_Text) };
	//
	// SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(this,
	// fragmentManager, TITLES, mViewPager, i);
	// mViewPager.setAdapter(adapter);
	// mTabs.setAdapter(adapter);
	// mViewPager.setOnPageChangeListener(mTabs);
	//
	// }

	@Override
	public boolean enableHomeIconActionSlidingMenu() {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			this.finish();
		}

		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		overridePendingTransition(R.anim.push_down_out_back,
				R.anim.push_down_in_back);

	}

	@Override
	public void onScrollChanged(int scrollY) {
		// TODO Auto-generated method stub
		mStickyView
				.setTranslationY(Math.max(mPlaceholderView.getTop(), scrollY));
	}

	@Override
	public void onDownMotionEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpOrCancelMotionEvent() {
		// TODO Auto-generated method stub

	}
}
