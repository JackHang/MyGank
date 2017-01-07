package com.jackhang.gank.ui.fragment.fun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jackhang.gank.BaseFragment;
import com.jackhang.gank.R;
import com.jackhang.gank.entity.FunEntity;
import com.jackhang.gank.mvp.MvpFragment;
import com.jackhang.gank.util.SnackBarUtil;
import com.wuxiaolong.androidutils.library.LogUtil;

import butterknife.BindView;

/**
 * 笑话大全界面
 * Created by JackHang on 2016/12/27.
 */
public class FunFragment extends MvpFragment<FunPresenter> implements FunView
{
	@BindView(R.id.recycle_home)
	RecyclerView mRecycleHistory;
	@BindView(R.id.progress)
	ProgressBar mProgress;
	FunAdapter funsAdapter = null;
	private int page = 1;

	@Override
	protected FunPresenter createPresenter()
	{
		return new FunPresenter(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		mvpPresenter.loadData(page);
		funsAdapter = new FunAdapter(null);
		funsAdapter.openLoadAnimation();
		funsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener()
		{
			@Override
			public void onLoadMoreRequested()
			{
				mRecycleHistory.post(new Runnable()
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
		mRecycleHistory.setLayoutManager(new LinearLayoutManager(this.getActivity()));
		mRecycleHistory.setAdapter(funsAdapter);
	}

	@Override
	public void getDataSuccess(FunEntity data)
	{
		funsAdapter.addData(data.getResult().getData());
	}

	@Override
	public void getDataFail(String msg)
	{
		SnackBarUtil.show(getView(), msg);
		LogUtil.e(msg);
	}

	@Override
	public void showLoading()
	{
		mProgress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading()
	{
		mProgress.setVisibility(View.GONE);
	}
}
