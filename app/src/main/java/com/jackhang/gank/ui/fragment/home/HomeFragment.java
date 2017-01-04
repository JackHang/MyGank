package com.jackhang.gank.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.jackhang.gank.R;
import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.MvpFragment;
import com.jackhang.gank.ui.ActivityPhoto;
import com.jackhang.gank.ui.ActivityWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JackHang on 2016/12/27.
 */

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView
{
	@BindView(R.id.recycle_home)
	RecyclerView mRecycleHome;
	@BindView(R.id.progress)
	ProgressBar mProgressBar;
	private HomeAdapter mHomeAdapter;
	private int page = 1;

	@Override
	protected HomePresenter createPresenter()
	{
		return new HomePresenter(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		ButterKnife.bind(view);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		mvpPresenter.loadData(page);
		mHomeAdapter = new HomeAdapter(null);
		mHomeAdapter.openLoadAnimation();
		mHomeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
		{
			@Override
			public void onLoadMoreRequested()
			{
				mRecycleHome.post(new Runnable()
				{
					@Override
					public void run()
					{
						page++;
						mvpPresenter.loadData(page);
					}
				});
			}
		});
		mRecycleHome.setLayoutManager(new LinearLayoutManager(getContext()));
		mRecycleHome.addOnItemTouchListener(new SimpleClickListener()
		{
			@Override
			public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
			{
				GankData.GankBean bean = (GankData.GankBean) baseQuickAdapter.getData().get(i);
				if (bean.type.equals("福利"))
				{
					Intent it = new Intent();
					it.setClass(mActivity, ActivityPhoto.class);
					it.putExtra("photo", bean.url);
					startActivity(it);
				}
				else
				{
					Bundle bundle = new Bundle();
					bundle.putString("url", bean.url);
					Intent it = new Intent();
					it.setClass(mActivity, ActivityWeb.class);
					it.putExtras(bundle);
					startActivity(it);
				}
			}

			@Override
			public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
			{

			}

			@Override
			public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
			{

			}

			@Override
			public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i)
			{

			}
		});
		mRecycleHome.setAdapter(mHomeAdapter);
	}

	@Override
	public void getDataSuccess(GankData mGankData)
	{
		mHomeAdapter.addData(mGankData.getResults());
	}

	@Override
	public void getDataFail(String msg)
	{

	}

	@Override
	public void showLoading()
	{
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading()
	{
		mProgressBar.setVisibility(View.GONE);
	}
}
