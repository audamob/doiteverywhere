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
import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.model.User;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.google.android.gms.internal.dp;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DoItActivityDetails extends ActivityBase {

	TextView doDisplayName,doCategoryName,doNbreFollowers,doDescription;
	ImageView doPicture;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doit_details);
		Typeface TODO = Typeface.createFromAsset(getAssets(), "DinDisplayProThin.otf");
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.withe)));
		doDisplayName=(TextView)findViewById(R.id.doDisplayName);
		doCategoryName=(TextView)findViewById(R.id.doCategoryName);
		doNbreFollowers=(TextView)findViewById(R.id.doNbreFollowers);
		doDescription=(TextView)findViewById(R.id.doDescription);
		doPicture=(ImageView)findViewById(R.id.doPicture);
		
		doDisplayName.setTypeface(TODO);
		
		DoItActivity doitActivity=(DoItActivity) getIntent().getSerializableExtra("DoItActivity");
		
		doDisplayName.setText(doitActivity.getDoDisplayName());
		doCategoryName.setText(doitActivity.getDoCategoryName());
		doNbreFollowers.setText(""+doitActivity.getDoNbreFollowers().split(",").length);
		doDescription.setText(doitActivity.getDoDescription());
		
		
		UrlImageViewHelper.setUrlDrawableCustom(
				doPicture.getLayoutParams().width,
				doPicture.getLayoutParams().height,
				doPicture,
				doitActivity.getDoPicture());
		
		
	

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
