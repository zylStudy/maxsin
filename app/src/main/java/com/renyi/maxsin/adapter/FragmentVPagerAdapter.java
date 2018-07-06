package com.renyi.maxsin.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentVPagerAdapter extends FragmentPagerAdapter {
private List<Fragment> list;
	public FragmentVPagerAdapter(FragmentManager fm, List<Fragment> list) {
		super(fm);
		this.list=list;
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}


	 
}
