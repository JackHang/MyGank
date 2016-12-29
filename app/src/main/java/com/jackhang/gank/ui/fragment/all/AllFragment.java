package com.jackhang.gank.ui.fragment.all;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackhang.gank.R;
import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.MvpFragment;
import com.jackhang.gank.ui.fragment.all.list.BaseFragmentPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 所有分类界面
 * Created by JackHang on 2016/12/27.
 */
public class AllFragment extends MvpFragment<AllPresenter> implements AllViews
{
	@BindView(R.id.tl_tablayout)
	TabLayout mTablayout;
	@BindView(R.id.vp_viewpager)
	ViewPager mViewpager;
	private BaseFragmentPageAdapter mAdapter;

	@Override
	protected AllPresenter createPresenter()
	{
		return new AllPresenter(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mRootView == null) {
			mRootView = inflater.inflate(R.layout.fragment_all, null);
			ButterKnife.bind(this, mRootView);

			// 设置tablayout的初始化，和其他的indicate很相似
			mTablayout.setupWithViewPager(mViewpager);
			mAdapter = new BaseFragmentPageAdapter(getChildFragmentManager());
			mViewpager.setAdapter(mAdapter);
		}
		return mRootView;
	}

	@Override
	public void showLoading()
	{

	}

	@Override
	public void hideLoading()
	{

	}
}
