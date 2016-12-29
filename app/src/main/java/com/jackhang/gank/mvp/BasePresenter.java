package com.jackhang.gank.mvp;

import com.jackhang.gank.retrofit.ApiManage;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by JackHang on 2016/12/26.
 */
public class BasePresenter<V> {
	public V mvpView;
	protected ApiManage apiStores;
	private CompositeSubscription mCompositeSubscription;

	public void attachView(V mvpView) {
		this.mvpView = mvpView;
		apiStores = ApiManage.getInstance();
	}


	public void detachView() {
		this.mvpView = null;
		onUnsubscribe();
	}

	//RxJava取消注册，以避免内存泄露
	public void onUnsubscribe() {
		if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
			mCompositeSubscription.unsubscribe();
		}
	}


	public void addSubscription(Observable observable, Subscriber subscriber) {
		if (mCompositeSubscription == null) {
			mCompositeSubscription = new CompositeSubscription();
		}
		mCompositeSubscription.add(observable
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(subscriber));
	}
}
