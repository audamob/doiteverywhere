package com.audamob.doit.activity;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.activity.SlidingMenu.ActivityBase;
import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DoItActivityDetails extends ActivityBase {

	TextView doDisplayName,doCategoryName,doNbreFollowers,doDescription;
	ImageView doPicture,categoryimage;
	RelativeLayout followButton;
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
		categoryimage=(ImageView)findViewById(R.id.categoryimage);
		doDisplayName.setTypeface(TODO);
		
		DoItActivity doitActivity=(DoItActivity) getIntent().getSerializableExtra("DoItActivity");
		
		doDisplayName.setText(doitActivity.getDoDisplayName());
		doCategoryName.setText(doitActivity.getDoCategoryName());
		doNbreFollowers.setText(""+doitActivity.getDoNbreFollowers().split(",").length);
		doDescription.setText(doitActivity.getDoDescription());
		followButton=(RelativeLayout)findViewById(R.id.followButton);
		followButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
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
