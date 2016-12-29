package com.jackhang.gank.retrofit;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 聚合API
 * https://www.juhe.cn/
 * Created by tao on 2016/10/28.
 */

@SuppressWarnings("WeakerAccess")
public interface JuHeApi
{
	//加载历史上的今天
//	@FormUrlEncoded
//	@POST("http://api.juheapi.com/japi/toh")
//	Observable<HistoryModel> loadHistoryData(@Field("v") String version, @Field("month") int month, @Field("day") int day, @Field("key") String key);
//
//	@FormUrlEncoded
//	@POST("http://v.juhe.cn/toutiao/index")
//	Observable<NewsModel> loadNewsData(@Field("key") String key, @Field("type") String type);
//
//	@FormUrlEncoded
//	@POST("http://japi.juhe.cn/joke/content/text.from")
//	Observable<FunsModel> loadNewFunsData(@Field("key") String key, @Field("page") int page, @Field("pagesize") int pagesize);
}
