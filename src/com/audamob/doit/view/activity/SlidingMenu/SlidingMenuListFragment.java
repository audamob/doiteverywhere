package com.audamob.doit.view.activity.SlidingMenu;

import java.io.IOException;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.audamob.doit.R;
import com.audamob.doit.model.User;
import com.audamob.doit.model.SlidingMenuListItem;
import com.audamob.doit.utils.CacheReadWriteUtil;
import com.audamob.doit.utils.ImageLoaderUtil;
import com.audamob.doit.view.activity.MainContainerActivity;
import com.audamob.doit.view.activity.ProfileActivity;
import com.audamob.doit.view.activity.StreamFragmentActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 *         List fragment, which will be used as a content for sliding out menu.
 * 
 */
public class SlidingMenuListFragment extends ListFragment implements
		OnClickListener {
	protected List<SlidingMenuListItem> slidingMenuList;
	private SlidingMenuBuilderBase slidingMenuBuilderBase;
	SlidingMenu menu;

	public SlidingMenuListFragment(SlidingMenu menu) {
		// TODO Auto-generated constructor stub
		this.menu = menu;
	}

	public void setMenuBuilder(SlidingMenuBuilderBase slidingMenuBuilderBase) {
		this.slidingMenuBuilderBase = slidingMenuBuilderBase;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// We set here a custom layout which uses holo light theme colors.
		View SlidingMenuLayout = inflater.inflate(R.layout.layout_sliding_menu,
				null);

		return SlidingMenuLayout;

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		UI_LoadProfileView();
		UI_CreateMenuListView();

	}

	public void UI_LoadProfileView() {
		ImageView im = (ImageView) getActivity().findViewById(
				R.id.profile_image);
		TextView profile_name = (TextView) getActivity().findViewById(
				R.id.profile_name);
		Typeface TODO = Typeface.createFromAsset(getActivity().getAssets(),
				"DinDisplayProThin.otf");
		TextView row_title=(TextView) getActivity().findViewById(
				R.id.row_title_1);
		row_title.setTypeface(TODO);
		row_title=(TextView) getActivity().findViewById(
				R.id.row_title_2);
		row_title.setTypeface(TODO);
		row_title=(TextView) getActivity().findViewById(
				R.id.row_title_3);
		row_title.setTypeface(TODO);
		row_title=(TextView) getActivity().findViewById(
				R.id.row_title_4);
		row_title.setTypeface(TODO);
		row_title=(TextView) getActivity().findViewById(
				R.id.row_title_5);
		row_title.setTypeface(TODO);
		row_title=(TextView) getActivity().findViewById(
				R.id.row_title_6);
		row_title.setTypeface(TODO);
		
		profile_name.setTypeface(TODO);
		row_title.setTypeface(TODO);
		
		User ac = null;
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
			ImageLoaderUtil imLoaderUtil = new ImageLoaderUtil(im,null,
					getActivity(), ac.getImageUrl(), ac.getUserId());
		
			profile_name.setText(ac.getProfileName());
		}

		
		im.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentProfile = new Intent(getActivity(),
						ProfileActivity.class);
				getActivity().startActivity(intentProfile);
				getActivity().overridePendingTransition(R.anim.push_down_in,
						R.anim.push_down_out);
			}
		});

	}

	public void UI_CreateMenuListView() {
		RelativeLayout ItemMenu_1, ItemMenu_2, ItemMenu_3, ItemMenu_4, ItemMenu_5,ItemMenu_6;
		ItemMenu_1 = (RelativeLayout) getActivity().findViewById(
				R.id.ItemMenu_1);
		ItemMenu_2 = (RelativeLayout) getActivity().findViewById(
				R.id.ItemMenu_2);
		ItemMenu_3 = (RelativeLayout) getActivity().findViewById(
				R.id.ItemMenu_3);
		ItemMenu_4 = (RelativeLayout) getActivity().findViewById(
				R.id.ItemMenu_4);
		ItemMenu_5 = (RelativeLayout) getActivity().findViewById(
				R.id.ItemMenu_5);
		ItemMenu_6 = (RelativeLayout) getActivity().findViewById(
				R.id.ItemMenu_6);
		ItemMenu_1.setOnClickListener(this);
		ItemMenu_2.setOnClickListener(this);
		ItemMenu_3.setOnClickListener(this);
		ItemMenu_4.setOnClickListener(this);
		ItemMenu_5.setOnClickListener(this);
		ItemMenu_6.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ItemMenu_1:
			UpdateUiSelecor();
			ActifSelector(R.id.selector_1);
			MainContainerActivity.ChangeCurrentFragment(1);

			menu.toggle();
			return;

		case R.id.ItemMenu_2:
			UpdateUiSelecor();
			ActifSelector(R.id.selector_2);
			MainContainerActivity.ChangeCurrentFragment(2);
			menu.toggle();
			break;
		case R.id.ItemMenu_3:
			UpdateUiSelecor();
			ActifSelector(R.id.selector_3);

			MainContainerActivity.ChangeCurrentFragment(3);
			menu.toggle();
			return;

		case R.id.ItemMenu_4:
			UpdateUiSelecor();
			ActifSelector(R.id.selector_4);

			MainContainerActivity.ChangeCurrentFragment(4);
			menu.toggle();
			return;

		case R.id.ItemMenu_5:
			UpdateUiSelecor();
			ActifSelector(R.id.selector_5);

			MainContainerActivity.ChangeCurrentFragment(5);
			menu.toggle();
			return;
		case R.id.ItemMenu_6:
			
			
			return;
		default:
			break;
		}
	}

	public void ActifSelector(int Rid) {
		getActivity().findViewById(Rid).setVisibility(View.VISIBLE);
	}

	public void UpdateUiSelecor() {
		RelativeLayout s1, s2, s3, s4, s5;
		s1 = (RelativeLayout) getActivity().findViewById(R.id.selector_1);
		s2 = (RelativeLayout) getActivity().findViewById(R.id.selector_2);
		s3 = (RelativeLayout) getActivity().findViewById(R.id.selector_3);
		s4 = (RelativeLayout) getActivity().findViewById(R.id.selector_4);
		s5 = (RelativeLayout) getActivity().findViewById(R.id.selector_5);

		s1.setVisibility(View.INVISIBLE);
		s2.setVisibility(View.INVISIBLE);
		s3.setVisibility(View.INVISIBLE);
		s4.setVisibility(View.INVISIBLE);
		s5.setVisibility(View.INVISIBLE);
	}

}
