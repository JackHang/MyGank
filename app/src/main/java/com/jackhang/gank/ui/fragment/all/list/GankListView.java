package com.jackhang.gank.ui.fragment.all.list;

import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.mvp.BaseView;

public interface GankListView extends BaseView
{
	void getDataSuccess(GankData mGankData);

	void getDataFail(String msg);
}
