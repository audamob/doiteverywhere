package com.audamob.doit.adapter;

import java.util.ArrayList;

import org.w3c.dom.Text;

import com.audamob.doit.model.DoItActivity;
import com.audamob.doit.utils.ImageLoaderUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.audamob.doit.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class DoItActivitiesListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	Context activity;
	ArrayList<DoItActivity> ListDoItActivity;

	public DoItActivitiesListAdapter(Context act,
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
			
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolderDoItActivity) convertView.getTag();
		}
		holder.PictureDoItActivity.setId(position);
		holder.NameDoItActivity.setId(position);
		Typeface TODO = Typeface.createFromAsset(activity.getAssets(), "DinDisplayProThin.otf");
		holder.NameDoItActivity.setTypeface(TODO);
		holder.NameDoItActivity.setText(ListDoItActivity.get(position).getDoDisplayName());
		
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
	int idDoItActivity;
}
