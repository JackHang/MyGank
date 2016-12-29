package com.jackhang.gank.ui.fragment.home;

import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.BasePresenter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JackHang on 2016/12/28.
 */

public class HomePresenter extends BasePresenter<HomeView>
{
	HomePresenter(HomeView view)
	{
		attachView(view);
	}

	public void loadData(int page)
	{
		apiStores.getGankService()
				.getHomeData(page)
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
