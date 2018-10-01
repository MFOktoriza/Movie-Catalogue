package com.vedici.moviecatalogue.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vedici.moviecatalogue.ui.NowPlayingFragment;
import com.vedici.moviecatalogue.ui.UpcomingFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
	private int mNumOfTabs;

	public ViewPagerAdapter(FragmentManager fragmentManager, int NumOfTabs) {
		super(fragmentManager);
		this.mNumOfTabs = NumOfTabs;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new NowPlayingFragment();
			case 1:
				return new UpcomingFragment();
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return mNumOfTabs;
	}
}
