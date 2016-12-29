package com.jackhang.gank;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by JackHang on 2016/12/26.
 */

public class BaseFragment extends Fragment
{
	public Activity mActivity;
	public View mRootView;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		mActivity = getActivity();
	}

	public Toolbar initToolBar(View view, String title) {
		Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
		toolbar_title.setText(title);
		return toolbar;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void toastShow(@StringRes int resId) {
		Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
	}

	public void toastShow(String message) {
		Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
	}
}
