package com.audamob.doit.adapter;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.audamob.doit.view.activity.DoItActivityDetails;
import com.audamob.doit.view.activity.ProfileActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.audamob.doit.R;
import com.google.android.gms.internal.ac;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DoItActivitiesListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	Activity activity;
	ArrayList<DoItActivity> ListDoItActivity;

	public DoItActivitiesListAdapter(Activity act,
			ArrayList<DoItActivity> doActivitiesList) {
		activity=act;
		ListDoItActivity = new ArrayList<DoItActivity>();
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ListDoItActivity = doActivitiesList;
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
		ViewHolderDoItActivity holder;
		if (convertView == null) {
			holder = new ViewHolderDoItActivity();

			convertView = mInflater.inflate(
					R.layout.item_list_doactivity, null);

			holder.NameDoItActivity = (TextView) convertView
					.findViewById(R.id.displayName);
			holder.CategoryDoItACtivity = (TextView) convertView
					.findViewById(R.id.category);
			holder.PictureDoItActivity = (ImageView) convertView
					.findViewById(R.id.picture);
			holder.DescriptionDoItActivity = (TextView) convertView
					.findViewById(R.id.Content);
			holder.NbrFollowers = (TextView) convertView
					.findViewById(R.id.nbrfollowers);
			holder.ItemDoItActivity= (RelativeLayout) convertView
					.findViewById(R.id.itemdoitactivity);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolderDoItActivity) convertView.getTag();
		}
		
		Typeface TODO = Typeface.createFromAsset(activity.getAssets(), "DinDisplayProThin.otf");
		
		holder.PictureDoItActivity.setId(position);
		holder.NameDoItActivity.setId(position);
		holder.CategoryDoItACtivity.setId(position);
		holder.DescriptionDoItActivity.setId(position);
		holder.NbrFollowers.setId(position);
		holder.ItemDoItActivity.setId(position);
		
		holder.NameDoItActivity.setTypeface(TODO);
		holder.DescriptionDoItActivity.setTypeface(TODO);
		holder.NameDoItActivity.setText(ListDoItActivity.get(position).getDoDisplayName());
		holder.DescriptionDoItActivity.setText(ListDoItActivity.get(position).getDoDescription());
		holder.NbrFollowers.setText(""+ListDoItActivity.get(position).getDoNbreFollowers().split(",").length);
		holder.CategoryDoItACtivity.setText(ListDoItActivity.get(position).getDoCategoryName());
		
		holder.ItemDoItActivity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentDetailsDOItActivity = new Intent(activity,
						DoItActivityDetails.class);
				intentDetailsDOItActivity.putExtra("DoItActivity",ListDoItActivity.get(position) );
				activity.startActivity(intentDetailsDOItActivity);
				activity.overridePendingTransition(R.anim.push_down_in,
						R.anim.push_down_out);
			}
		});
		
		
		UrlImageViewHelper.setUrlDrawableCustom(
				holder.PictureDoItActivity.getLayoutParams().width,
				holder.PictureDoItActivity.getLayoutParams().height, holder.PictureDoItActivity,
				ListDoItActivity.get(position).getDoPicture());
		
	
		
		holder.idDoItActivity = position;

		return convertView;
	}
}

class ViewHolderDoItActivity {

	TextView NameDoItActivity;
	ImageView PictureDoItActivity;
	TextView CategoryDoItACtivity;
	TextView NbrFollowers;
	TextView DescriptionDoItActivity;
	RelativeLayout ItemDoItActivity;
	int idDoItActivity;
}
