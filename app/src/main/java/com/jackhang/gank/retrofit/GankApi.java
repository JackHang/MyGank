package com.jackhang.gank.retrofit;

import com.jackhang.gank.entity.GankData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tao on 2016/10/28.
 */
public interface GankApi
{
	@GET("/api/data/all/20/{page}")
	Observable<GankData> getHomeData(@Path("page") int page);
}
