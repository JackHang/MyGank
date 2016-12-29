package com.jackhang.gank.mvp;

import android.content.Context;
import android.os.Bundle;

import com.jackhang.gank.BaseActivity;
import com.jackhang.gank.global.MyApplication;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity
{
	protected P mvpPresenter;

	public static Context getContext()
	{
		return MyApplication.sContext;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mvpPresenter = createPresenter();
		super.onCreate(savedInstanceState);
	}

	protected abstract P createPresenter();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mvpPresenter != null) {
			mvpPresenter.detachView();
		}
	}
}
