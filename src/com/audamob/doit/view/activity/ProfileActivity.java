package com.audamob.doit.view.activity;

import java.io.IOException;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.adapter.SwipeyTabsPagerAdapter;
import com.audamob.doit.model.User;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.audamob.doit.utils.LayoutResizerUtil;
import com.audamob.doit.utils.ObservableScrollView;
import com.audamob.doit.view.activity.SlidingMenu.ActivityBase;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.viewpagerindicator.TabPageIndicator;

public class ProfileActivity extends ActivityBase implements
		ObservableScrollView.Callbacks {

	private LinearLayout mStickyView;
	private View mPlaceholderView;
	private ObservableScrollView mObservableScrollView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_profile_activity);
		mObservableScrollView = (ObservableScrollView) findViewById(R.id.scroll_view);
		mObservableScrollView.setCallbacks(this);

		mPlaceholderView = findViewById(R.id.placeholder);

		mStickyView = (LinearLayout) findViewById(R.id.sticky);

		mStickyView.getLayoutParams().height = 3 * LayoutResizerUtil
				.getDisplayHightInPx(this);
		mStickyView.requestLayout();

		mObservableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						onScrollChanged(mObservableScrollView.getScrollY());
					}
				});

		CreateUiProfile();
		CreateProfileTab(6);

	}

	public void CreateUiProfile() {
		ImageView im = (ImageView) findViewById(R.id.profile_image);

		User ac = null;
		try {
			ac = CacheReadWriteUtil.restoreAccount(this);
		} catch (ClassNotFoundException e) { // TODO Auto-generated catch

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
		ImageView bgInfo=(ImageView)findViewById(R.id._image_info);
		
		 UrlImageViewHelper.setUrlDrawableCustom(bgInfo.getLayoutParams().width,
				 bgInfo.getLayoutParams().height, bgInfo, ac.getImageUrl());
		

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
	}

	public void CreateProfileTab(int i) {

		ViewPager pager = (ViewPager) findViewById(R.id.viewpagerProfile);
		FragmentManager fragmentManager;
		fragmentManager = getSupportFragmentManager();

		try {
			pager.removeAllViews();
		} catch (Exception e) {
		}

		String[] TITLES = { getResources().getString(R.string.Suggestion_Text),
				getResources().getString(R.string.Nearby_text),
				getResources().getString(R.string.Following_text),
				getResources().getString(R.string.Follower_Text) };
		SwipeyTabsPagerAdapter adapter = new SwipeyTabsPagerAdapter(this,
				fragmentManager, TITLES, pager, i);

		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicatorProfile);
		indicator.setViewPager(pager);

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
