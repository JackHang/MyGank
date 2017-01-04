package com.jackhang.gank.ui.fragment.all;

import com.jackhang.gank.mvp.BasePresenter;

/**
 * Created by JackHang on 2016/12/29.
 */

public class AllPresenter extends BasePresenter<AllViews>
{
	AllPresenter(AllViews views)
	{
		attachView(views);
	}
}
