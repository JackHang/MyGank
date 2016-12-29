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
	@GET("/api/data/{type}/{pageSize}/{page}")
	Observable<GankData> getHomeData(@Path("type") String type, @Path("pageSize") int pageSize, @Path("page") int page);
}
