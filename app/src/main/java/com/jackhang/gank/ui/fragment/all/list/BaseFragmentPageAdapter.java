package com.jackhang.gank.ui.fragment.all.list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jackhang.gank.R;
import com.jackhang.gank.global.MyApplication;

/**
 * 主界面中，“所有”fragment的adapter
 */

public class BaseFragmentPageAdapter extends FragmentPagerAdapter {
    private static String[] mTitles;
    private final PageFactory mPageFactory;


    public BaseFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        mTitles = MyApplication.sContext.getResources().getStringArray(R.array.GankType);
        // 创建一个工厂
        mPageFactory = new PageFactory();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mPageFactory.getFragment(getPageTitle(position).toString());
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
