package com.audamob.doit.activity.SlidingMenu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.ListFragment;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.audamob.doit.*;
import com.audamob.doit.adapter.SlidingMenuListAdapter;
import com.audamob.doit.model.Account;
import com.audamob.doit.model.SlidingMenuListItem;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.audamob.doit.utils.ProfileDataUtil;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 *         List fragment, which will be used as a content for sliding out menu.
 * 
 */
public class SlidingMenuListFragment extends ListFragment {
	protected List<SlidingMenuListItem> slidingMenuList;
	private SlidingMenuBuilderBase slidingMenuBuilderBase;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// We set here a custom layout which uses holo light theme colors.
		return inflater.inflate(R.layout.sliding_menu_layout, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// We get a list from our specially created list data class.
		slidingMenuList = SlidingMenuList.getSlidingMenu(getActivity());
		if (slidingMenuList == null)
			return;

		// We pass our taken list to the adapter.
		SlidingMenuListAdapter adapter = new SlidingMenuListAdapter(
				getActivity(), R.layout.menu_item, slidingMenuList);
		setListAdapter(adapter);

		ImageView im = (ImageView) getActivity().findViewById(
				R.id.profile_image);

		Account ac = null;
		try {
			ac = CacheReadWriteUtil.restoreAccount(getActivity());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ac != null) {
			ImageLoaderUtil imLoaderUtil = new ImageLoaderUtil(im,
					getActivity(), ac.getImageUrl(), ac.getUserId());
			TextView profile_name=(TextView)getActivity().findViewById(R.id.profile_name);
			profile_name.setText(ac.getProfileName());
		}
		

	}

	// We could define item click actions here, but instead we want our builder
	// to be responsible for that.
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		l.setSelection(position);
		SlidingMenuListItem item = slidingMenuList.get(position);
		slidingMenuBuilderBase.onListItemClick(item);
	}

	// We can not provide a builder as an argument inside a fragment
	// constructor, so that is why we have separate method for that.
	public void setMenuBuilder(SlidingMenuBuilderBase slidingMenuBuilderBase) {
		this.slidingMenuBuilderBase = slidingMenuBuilderBase;
	}

}
