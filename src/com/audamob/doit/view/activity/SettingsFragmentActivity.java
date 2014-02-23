package com.audamob.doit.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.audamob.doit.R;
import com.audamob.doit.tool.listener.DragViewListener;

public class SettingsFragmentActivity extends Fragment {

	EditText SearchEdit, SearchEditAlbum;

	ListView ListVideo;

	int windowwidth;
	int windowheight;

	private MarginLayoutParams mLayoutParams;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) inflater.inflate(
				R.layout.layout_settings_fragment, null);
		final String title = getArguments().getString("title");

		return root;

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

		super.onStart();
		windowwidth = getActivity().getWindowManager().getDefaultDisplay()
				.getWidth();
		windowheight = getActivity().getWindowManager().getDefaultDisplay()
				.getHeight();
//		final RelativeLayout balls = (RelativeLayout) getActivity()
//				.findViewById(R.id.Test);
//		mLayoutParams=(MarginLayoutParams)balls.getLayoutParams();
//		balls.setOnTouchListener(new DragViewListener(getActivity(), balls, mLayoutParams));
	}

}