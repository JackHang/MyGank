package com.jackhang.gank.view;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 * Created by JackHang on 2016/12/29.
 */

public class MyFragmentTabHost extends FragmentTabHost
{
	public MyFragmentTabHost(Context context)
	{
		super(context);
	}

	public MyFragmentTabHost(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onAttachedToWindow()
	{
		try
		{
			super.onAttachedToWindow();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
