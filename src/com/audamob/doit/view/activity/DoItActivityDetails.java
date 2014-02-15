package com.audamob.doit.view.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.service.ActivityService;
import com.audamob.doit.service.FollowService;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.audamob.doit.view.activity.SlidingMenu.ActivityBase;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DoItActivityDetails extends ActivityBase {

	TextView doDisplayName,doCategoryName,doNbreFollowers,doDescription;
	ImageView doPicture,categoryimage;
	RelativeLayout followButton;
	Activity context;
	DoItActivity doitActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		setContentView(R.layout.layout_doit_details);
		Typeface TODO = Typeface.createFromAsset(getAssets(), "DinDisplayProThin.otf");
		ActionBar bar = getActionBar();
		context = this;
		
		bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(
				R.color.flat_clouds)));
		doDisplayName=(TextView)findViewById(R.id.doDisplayName);
		doCategoryName=(TextView)findViewById(R.id.doCategoryName);
		doNbreFollowers=(TextView)findViewById(R.id.doNbreFollowers);
		doDescription=(TextView)findViewById(R.id.doDescription);
		doPicture=(ImageView)findViewById(R.id.doPicture);
		categoryimage=(ImageView)findViewById(R.id.categoryimage);
		doDisplayName.setTypeface(TODO);
		
		doitActivity=(DoItActivity) getIntent().getSerializableExtra("DoItActivity");
		
		doDisplayName.setText(doitActivity.getDoDisplayName());
		doCategoryName.setText(doitActivity.getDoCategoryName());
		doNbreFollowers.setText(""+doitActivity.getDoNbreFollowers().split(",").length);
		doDescription.setText(doitActivity.getDoDescription());
		followButton=(RelativeLayout)findViewById(R.id.followButton);
		followButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FollowService followService = new FollowService(context);
				try {
					Log.e("FOLLOW"," je suis ici 1");
					
					followService.addNewFollowToActivity(doitActivity.getDoIdActivity(), "0" +
							"" +
							"1");
					//CacheReadWriteUtil.restoreAccount(context).getmId());
					
				} catch (Exception e) {
					// TODO: handle exception
					Log.e("FOLLOW"," error  : "+e.getMessage());
				}
				
			}
		});
		
		UrlImageViewHelper.setUrlDrawableCustom(
				doPicture.getLayoutParams().width,
				doPicture.getLayoutParams().height,
				doPicture,
				doitActivity.getDoPicture());
		
		ImageLoaderUtil imLoaderUtil = new ImageLoaderUtil(categoryimage,
				this, "http://upload.wikimedia.org/wikipedia/de/archive/6/65/20110312135854!Extreme_Activity_Logo.jpg");
	

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
