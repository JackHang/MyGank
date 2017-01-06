package com.jackhang.gank.ui.fragment.fun;

import com.jackhang.gank.entity.FunEntity;
import com.jackhang.gank.mvp.BaseView;

public interface FunView extends BaseView
{
	void getDataSuccess(FunEntity data);

	void getDataFail(String msg);
}
