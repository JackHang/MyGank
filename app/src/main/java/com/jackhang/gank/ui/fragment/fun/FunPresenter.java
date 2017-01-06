package com.jackhang.gank.ui.fragment.fun;

import com.jackhang.gank.entity.FunEntity;
import com.jackhang.gank.mvp.BasePresenter;
import com.jackhang.gank.retrofit.JuHeKey;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JackHang on 2017/1/6.
 */

public class FunPresenter extends BasePresenter<FunView>
{
	FunPresenter(FunView view)
	{
		attachView(view);
	}

	void loadData(int page)
	{
		apiStores.getTopNewsService()
				.loadNewFunsData(JuHeKey.FunKey, page, 10)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<FunEntity>()
				{
					@Override
					public void onCompleted()
					{
						mvpView.hideLoading();
					}

					@Override
					public void onError(Throwable e)
					{
						mvpView.getDataFail(e.getMessage());
					}

					@Override
					public void onNext(FunEntity data)
					{
						mvpView.getDataSuccess(data);
					}
				});
	}
}
