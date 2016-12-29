package com.jackhang.gank.ui.fragment.all.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class GankListFragment extends MvpFragment<GankListPresenter> implements GankListView
{
	@BindView(R.id.recycle_home)
	RecyclerView mRecycleHome;
	@BindView(R.id.progress)
	ProgressBar mProgressBar;
	private GankListAdapter mHomeAdapter;
	private int page = 1;
	private String mGankType;

	@Override
	protected GankListPresenter createPresenter()
	{
		return new GankListPresenter(this);
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 获取参数
		Bundle arguments = getArguments();
		if (arguments != null) {
			mGankType = arguments.getString("title");
		}
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
		mvpPresenter.loadData(mGankType, page);
		mHomeAdapter = new GankListAdapter(null);
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
						mvpPresenter.loadData(mGankType, page);
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
		mProgressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading()
	{
		mProgressBar.setVisibility(View.GONE);
	}
}
