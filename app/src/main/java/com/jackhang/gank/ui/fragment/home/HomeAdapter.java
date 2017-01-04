package com.jackhang.gank.ui.fragment.home;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackhang.gank.R;
import com.jackhang.gank.entity.GankData;
import com.jackhang.gank.util.GlideUtil;

import java.util.List;

/**
 * Created by JackHang on 2016/12/28.
 */

public class HomeAdapter extends BaseQuickAdapter<GankData.GankBean, BaseViewHolder>
{
	public HomeAdapter(List<GankData.GankBean> data)
	{
		super(R.layout.item_home, data);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, GankData.GankBean gankBean)
	{
		// 设置角标颜色
		int typeId = 0;
		switch (gankBean.type)
		{
			case "福利":
				typeId = R.color.yellow;
				break;
			case "Android":
				typeId = R.color.forestgreen;
				break;
			case "iOS":
				typeId = R.color.orangered;
				break;
			case "休息视频":
				typeId = R.color.purple;
				break;
			case "拓展资源":
				typeId = R.color.darkred;
				break;
			case "前端":
				typeId = R.color.mistyrose;
				break;
			default:
				typeId = R.color.colorPrimary;
				break;
		}
		baseViewHolder.setText(R.id.tv_title, gankBean.desc)
				.setText(R.id.tv_author, gankBean.who)
				.setText(R.id.tv_time, gankBean.publishedAt.substring(0, 10));

		View line = baseViewHolder.getView(R.id.v_data_type);
		line.setBackgroundResource(typeId);

		ImageView img = baseViewHolder.getView(R.id.iv_picture);
		if (gankBean.type.equals("福利"))
		{
			Glide.with(mContext).load(gankBean.url).asBitmap().into(img);
			img.setVisibility(View.VISIBLE);
		}
		else
		{
			if (gankBean.images != null && !gankBean.images.isEmpty())
			{
				GlideUtil.loadStaticPic(img, gankBean.images.get(0));
				img.setVisibility(View.VISIBLE);
			}
			else
			{
				img.setVisibility(View.GONE);
			}
		}
	}
}
