package com.audamob.doit.view.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.audamob.doit.R;
import com.audamob.doit.adapter.DoItActivitiesListAdapter;
import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.service.ActivityService;

public class SwipeyTabFragmentActivity extends Fragment {

	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.layout_swipey_tab_fragment, null);
		final String title = getArguments().getString("title");
		

		return root;

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

}