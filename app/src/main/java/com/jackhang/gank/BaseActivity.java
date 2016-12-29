package com.jackhang.gank;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxiaolong.androidutils.library.LogUtil;

import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by JackHang on 2016/12/26.
 */

public class BaseActivity extends AppCompatActivity
{
	public Activity mActivity;
	//	public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
	private CompositeSubscription mCompositeSubscription;

	@Override
	public void setContentView(@LayoutRes int layoutResID)
	{
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
		mActivity = this;
	}

	@Override
	public void setContentView(View view)
	{
		super.setContentView(view);
		ButterKnife.bind(this);
		mActivity = this;
	}

	@Override
	public void setContentView(View view, ViewGroup.LayoutParams params)
	{
		super.setContentView(view, params);
		ButterKnife.bind(this);
		mActivity = this;
	}

	@Override
	protected void onDestroy()
	{
		onUnsubscribe();
		super.onDestroy();
	}

	public void addSubscription(Observable observable, Subscriber subscriber)
	{
		if (mCompositeSubscription == null)
		{
			mCompositeSubscription = new CompositeSubscription();
		}
		mCompositeSubscription.add(observable
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(subscriber));
	}

	public void addSubscription(Subscription subscription)
	{
		if (mCompositeSubscription == null)
		{
			mCompositeSubscription = new CompositeSubscription();
		}
		mCompositeSubscription.add(subscription);
	}

	public void onUnsubscribe()
	{
		LogUtil.d("onUnsubscribe");
		//取消注册，以避免内存泄露
		if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
		{
			mCompositeSubscription.unsubscribe();
		}
	}

	public Toolbar initToolBar(String title)
	{

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null)
		{
			setSupportActionBar(toolbar);
			TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
			toolbaTitle.setText(title);
		}
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null)
		{
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
		return toolbar;
	}

	public Toolbar initToolBarAsHome(String title)
	{

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null)
		{
			setSupportActionBar(toolbar);
			TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
			toolbaTitle.setText(title);
		}
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null)
		{
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setDisplayShowTitleEnabled(false);
		}
		return toolbar;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				super.onBackPressed();//返回
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void toastShow(@StringRes int resId) {
		Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
	}

	public void toastShow(String message) {
		Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
	}
}
