package com.jackhang.gank.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.jackhang.gank.BaseActivity;
import com.jackhang.gank.R;
import com.jackhang.gank.enums.KeyValue;
import com.jackhang.gank.enums.TabMenus;
import com.jackhang.gank.util.SnackBarUtil;
import com.jackhang.gank.util.UIUtils;
import com.jackhang.gank.view.MyFragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by JackHang on 2016/12/26.
 */
public class MainActivity extends BaseActivity
{
	public int tabIndex = 0;
	@BindView(R.id.tabhost)
	MyFragmentTabHost mTabHost;

	// 保存更新通知的小圆点
	private List<ImageView> newsNotifyViews = new ArrayList<>();
	private View mRootView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		initView();
		initTabHost();
	}

	private void initView() {
		mRootView = View.inflate(this, R.layout.layout_main_content, null);
		setContentView(mRootView);
	}

	private void initTabHost() {
		mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);
		// 去掉间隔
		mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

		TabMenus[] tabManures = TabMenus.values();

		for (int i = 0; i < tabManures.length; i++) {
			TabMenus tabMenuse = tabManures[i];

			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabMenuse.getTag());

			tabSpec.setIndicator(getIndicator(tabMenuse));

			// 传入需要的参数
			KeyValue[] arguments = tabMenuse.getArguments();

			Bundle bundle = null;

			if (arguments != null && arguments.length > 0) {
				bundle = new Bundle();
				for (KeyValue argument : arguments) {
					bundle.putString(argument.key, argument.value);
				}
			}
			mTabHost.addTab(tabSpec, tabMenuse.getClazz(), bundle);
		}
		mTabHost.setCurrentTab(tabIndex);
	}


	/**
	 * 获取指示器
	 *
	 * @param tabMenus
	 * @return
	 */
	private View getIndicator(TabMenus tabMenus) {
		RelativeLayout indicatorView = (RelativeLayout) UIUtils.inflateView(this, R.layout.layout_bottom_indicator, null);

		indicatorView.setGravity(Gravity.CENTER);

		ImageView icon = (ImageView) indicatorView.findViewById(R.id.iv_icon);
		ImageView notify = (ImageView) indicatorView.findViewById(R.id.iv_new_notify);
		notify.setVisibility(ImageView.INVISIBLE);
		newsNotifyViews.add(notify);
		icon.setImageResource(tabMenus.getIconId());
		return indicatorView;
	}

	long oldTime = 0;

	public void onBackPressed() {
		long newTime = System.currentTimeMillis();

		if (newTime - oldTime < 1000) {
			super.onBackPressed();
		} else {
			SnackBarUtil.show(findViewById(R.id.tabcontent), "再按一次退出");
			oldTime = newTime;
		}

	}
}
