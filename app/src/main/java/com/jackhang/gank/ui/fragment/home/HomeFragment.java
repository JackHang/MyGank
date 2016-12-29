package com.jackhang.gank.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jackhang.gank.R;
import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.MvpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JackHang on 2016/12/27.
 */

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView
{
	@BindView(R.id.recycle_meizi)
	RecyclerView mRecycleHome;
	@BindView(R.id.prograss)
	ProgressBar mPrograss;
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
		mPrograss.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading()
	{
		mPrograss.setVisibility(View.GONE);
	}
}
