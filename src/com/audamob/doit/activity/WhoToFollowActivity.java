package com.audamob.doit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.audamob.doit.R;

public class WhoToFollowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_who_to_follow);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.who_to_follow, menu);
		return true;
	}

}
