package com.jackhang.gank.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jackhang.gank.BaseActivity;
import com.jackhang.gank.R;

import butterknife.BindView;

/**
 * Created by JackHang on 2017/1/3.
 */

public class ActivityWeb extends BaseActivity
{
	//	@BindView(R.id.refresh)
//	SwipeRefreshLayout mRefreshLayout;
	@BindView(R.id.webview)
	WebView mWebView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_web);

		webViewSetting(mWebView);

		String url = getIntent().getExtras().getString("url", "http://www.baidu.com");
		mWebView.loadUrl(url);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void webViewSetting(WebView mWebView)
	{
		// 触摸焦点
		mWebView.setFocusable(true);
		WebSettings webSettings = mWebView.getSettings();
		//UTF-8编码方式
		webSettings.setDefaultTextEncodingName("UTF-8");
		// 浏览器不支持多窗口显示
		webSettings.setSupportMultipleWindows(false);

		webSettings.setDomStorageEnabled(true);
		// 设置支持JavaScript脚本
		webSettings.setJavaScriptEnabled(true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
		{
			webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}
		// 设置可以支持缩放
		webSettings.setSupportZoom(true);
		// 设置出现缩放工具
		webSettings.setBuiltInZoomControls(false);
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);

		mWebView.setWebViewClient(new WebViewClient()
		{
			@TargetApi(Build.VERSION_CODES.LOLLIPOP)
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
			{
				view.loadUrl(String.valueOf(request.getUrl()));
				return super.shouldOverrideUrlLoading(view, request);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}
}
