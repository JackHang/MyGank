package com.jackhang.gank.ui.fragment.home;

import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.BaseView;

public interface HomeView extends BaseView
{
	void getDataSuccess(GankData mGankData);

	void getDataFail(String msg);
}
