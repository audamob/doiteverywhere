package com.audamob.doit.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.audamob.doit.R;

public class StreamFragmentActivity extends Fragment {

	EditText SearchEdit, SearchEditAlbum;

	ListView ListVideo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.stream_fragment_layout,
				null);
		final String title = getArguments().getString("title");

		return root;

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

}