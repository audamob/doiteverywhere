package com.audamob.doit.activity;

import java.io.IOException;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.UiComponent.SwipeyTabs;
import com.audamob.doit.activity.SlidingMenu.ActivityBase;
import com.audamob.doit.adapter.SwipeyTabsPagerAdapter;
import com.audamob.doit.model.User;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.utils.ImageLoaderUtil;

public class ProfileActivity extends ActivityBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_profile_activity);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.withe)));

		ImageView im = (ImageView) findViewById(R.id.profile_picture);

		User ac = null;
		try {
			ac = CacheReadWriteUtil.restoreAccount(this);
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated
			e.printStackTrace();
		}

		ImageLoaderUtil imLoaderUtil = new ImageLoaderUtil(im,
				this, ac.getImageUrl(), ac.getUserId());
	
		/*UrlImageViewHelper.setUrlDrawableCustom(im.getLayoutParams().width,
				im.getLayoutParams().height, im, ac.getImageUrl());*/
		Typeface TODO = Typeface.createFromAsset(getAssets(),
				"DinDisplayProThin.otf");
		TextView AccountName, AccountAge, AccountLocation, AccountOrganisation;
		AccountName = (TextView) findViewById(R.id.profile_name_p);
		AccountAge = (TextView) findViewById(R.id.profile_age_p);
		AccountLocation = (TextView) findViewById(R.id.profile_location_p);

		AccountOrganisation = (TextView) findViewById(R.id.profile_Activity_p);

		AccountName.setText(ac.getProfileName() + ",");
		AccountAge.setText(ac.getProfileBirthday());
		AccountLocation.setText(ac.getProfileLocation());

		AccountOrganisation.setText(ac.getPosteOrganisation());
		AccountAge.setTypeface(TODO);
		AccountLocation.setTypeface(TODO);

		AccountOrganisation.setTypeface(TODO);
		CreateProfileTab(6);

		/*
		 * GridView
		 * GridActivities=(GridView)findViewById(R.id.GridView_DoneActivity);
		 * ArrayList<DoItActivity> ListDoneActivities=new
		 * ArrayList<DoItActivity>(); ListDoneActivities.add(new
		 * DoItActivity("Surfing",
		 * "http://www.funfix.com/images/icons/icon_black_locations.png"));
		 * ListDoneActivities.add(new DoItActivity("SkyDiving",
		 * "http://hotspottagger.com/images/markers/surfing_icon.png"));
		 * GridActivtiesProfileAdapter AdapterGrid=new
		 * GridActivtiesProfileAdapter(this, ListDoneActivities);
		 * GridActivities.setAdapter(AdapterGrid);
		 */
	}

	public void CreateProfileTab(int i) {
		SwipeyTabs mTabs;
		final ViewPager mViewPager;

		FragmentManager fragmentManager;
		mViewPager = (ViewPager) findViewById(R.id.viewpagerProfile);
		mTabs = (SwipeyTabs) findViewById(R.id.swipeytabsProfile);
		fragmentManager = getSupportFragmentManager();

		try {
			mViewPager.removeAllViews();
		} catch (Exception e) {
		}

		String[] TITLES = { getResources().getString(R.string.Suggestion_Text),
				getResources().getString(R.string.Nearby_text),
				getResources().getString(R.string.Following_text
						),
				getResources().getString(R.string.Follower_Text) };

		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(this,
				fragmentManager, TITLES, mViewPager, i);
		mViewPager.setAdapter(adapter);
		mTabs.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(mTabs);

	}

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
}
