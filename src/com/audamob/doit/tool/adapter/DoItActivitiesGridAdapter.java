package com.audamob.doit.tool.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.tool.utils.net.ImageLoaderUtil;

public class DoItActivitiesGridAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	Activity activity;
	ArrayList<DoItActivity> ListDoItActivity;

	public DoItActivitiesGridAdapter(Activity act,
			ArrayList<DoItActivity> List) {
		activity=act;
		ListDoItActivity = new ArrayList<DoItActivity>();
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ListDoItActivity = List;
	}

	public int getCount() {
		return ListDoItActivity.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolderImage_edit holder;
		if (convertView == null) {
			holder = new ViewHolderImage_edit();

			convertView = mInflater.inflate(
					R.layout.item_grid_activities_profile_layout, null);

			holder.Name = (TextView) convertView
					.findViewById(R.id.grid_item_label_activity);
			holder.ImageActivity = (ImageView) convertView
					.findViewById(R.id.grid_item_image_activity);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolderImage_edit) convertView.getTag();
		}
		holder.ImageActivity.setId(position);
		holder.Name.setId(position);
		Typeface TODO = Typeface.createFromAsset(activity.getAssets(), "DinDisplayProThin.otf");
		holder.Name.setTypeface(TODO);
		holder.Name.setText(ListDoItActivity.get(position).getDoDisplayName());
		
		ImageLoaderUtil imLoaderUtil = new ImageLoaderUtil(holder.ImageActivity,
				activity, ListDoItActivity.get(position).getDoPicture());
		
		holder.id = position;

		return convertView;
	}
}

class ViewHolderImage_edit {

	TextView Name;
	ImageView ImageActivity;

	int id;
}
