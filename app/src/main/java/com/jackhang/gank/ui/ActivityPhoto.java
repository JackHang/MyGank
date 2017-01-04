package com.jackhang.gank.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.jackhang.gank.BaseActivity;
import com.jackhang.gank.R;
import com.jackhang.gank.util.GlideUtil;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by JackHang on 2017/1/4.
 */

public class ActivityPhoto extends BaseActivity
{
	@BindView(R.id.photo)
	ImageView photo;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_photo);

		GlideUtil.loadStaticPic(photo, getIntent().getStringExtra("photo"));

		PhotoViewAttacher mAttacher = new PhotoViewAttacher(photo);
		mAttacher.update();
	}
}
