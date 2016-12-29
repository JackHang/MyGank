package com.jackhang.gank.ui.fragment.all.list;

import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.BasePresenter;
import com.jackhang.gank.retrofit.GankValue;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GankListPresenter extends BasePresenter<GankListView>
{
	GankListPresenter(GankListView view)
	{
		attachView(view);
	}

	public void loadData(String GankType, int page)
	{
		apiStores.getGankService()
				.getHomeData(GankType, GankValue.pageSize, page)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<GankData>()
				{
					@Override
					public void onCompleted()
					{
						mvpView.hideLoading();
					}

					@Override
					public void onError(Throwable e)
					{

					}

					@Override
					public void onNext(GankData mGankData)
					{
						mvpView.getDataSuccess(mGankData);
					}
				});
	}
}
