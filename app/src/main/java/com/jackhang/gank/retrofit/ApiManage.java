package com.jackhang.gank.retrofit;

import com.jackhang.gank.BuildConfig;
import com.jackhang.gank.global.MyApplication;
import com.jackhang.gank.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManage
{
	private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor()
	{
		@Override
		public Response intercept(Chain chain) throws IOException
		{
			Response originalResponse = chain.proceed(chain.request());
			if (NetWorkUtil.isNetWorkAvailable(MyApplication.sContext))
			{
				int maxAge = 60; // 在线缓存在1分钟内可读取
				return originalResponse.newBuilder()
						.removeHeader("Pragma")
						.removeHeader("Cache-Control")
						.header("Cache-Control", "public, max-age=" + maxAge)
						.build();
			}
			else
			{
				int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
				return originalResponse.newBuilder()
						.removeHeader("Pragma")
						.removeHeader("Cache-Control")
						.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
						.build();
			}
		}
	};
	private static ApiManage apiManage;
	private static File httpCacheDirectory = new File(MyApplication.sContext.getCacheDir(), "lookCache");
	private static int cacheSize = 10 * 1024 * 1024; // 10 MiB
	private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
	private static OkHttpClient client;
	private static OkHttpClient sOkHttpClient;
	private final Object mObject = new Object();
	private JuHeApi mJuHeApi;
	private GankApi ganK;

	public static ApiManage getInstance()
	{
		if (apiManage == null)
		{
			synchronized (ApiManage.class)
			{
				if (apiManage == null)
				{
					apiManage = new ApiManage();
				}
				if (client == null)
				{
					OkHttpClient.Builder builder = new OkHttpClient.Builder();

					if (BuildConfig.DEBUG)
					{
						// Log信息拦截器
						HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
						loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
						//设置 Debug Log 模式
						builder.addInterceptor(loggingInterceptor);
					}
					sOkHttpClient = builder.build();
					client = builder
							.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
							.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
							.cache(cache)
							.build();
				}
			}
		}
		return apiManage;
	}

	public JuHeApi getTopNewsService()
	{
		if (mJuHeApi == null)
		{
			synchronized (mObject)
			{
				if (mJuHeApi == null)
				{
					mJuHeApi = new Retrofit.Builder()
							.baseUrl("http://c.m.163.com")
							.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
							.client(sOkHttpClient)
							.addConverterFactory(GsonConverterFactory.create())
							.build().create(JuHeApi.class);
				}
			}
		}
		return mJuHeApi;
	}

	public GankApi getGankService()
	{
		if (ganK == null)
		{
			synchronized (mObject)
			{
				if (ganK == null)
				{
					ganK = new Retrofit.Builder()
							.baseUrl("http://gank.io")
							.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
							.client(client)
							.addConverterFactory(GsonConverterFactory.create())
							.build().create(GankApi.class);
				}
			}
		}
		return ganK;
	}

}
