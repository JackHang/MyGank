package com.jackhang.gank.ui.fragment.fun;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackhang.gank.R;
import com.jackhang.gank.entity.FunEntity;
import com.wuxiaolong.androidutils.library.TimeUtil;

import java.util.List;

class FunAdapter extends BaseQuickAdapter<FunEntity.ResultEntity.DataEntity, BaseViewHolder>
{
	FunAdapter(List<FunEntity.ResultEntity.DataEntity> data)
	{
		super(R.layout.item_fun, data);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, FunEntity.ResultEntity.DataEntity resultEntity)
	{
		long second = (long) resultEntity.getUnixtime() * 1000;
		String time = TimeUtil.unixTimestamp2BeijingTime(second, "yyyy年MM月dd日");
		baseViewHolder
				.setText(R.id.tv_fun_content, resultEntity.getContent())
				.setText(R.id.tv_fun_time, time);
	}
}