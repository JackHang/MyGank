package com.jackhang.gank.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Gank 数据解析
 * Created by JackHang on 2016/12/27.
 */

public class GankData
{
	public boolean error;

	public List<GankBean> results = new ArrayList<>();

	public class GankBean {
		public String _id;
		public String createdAt;
		public String desc;
		public String publishedAt;
		public String source;
		public String type;
		public String url;
		public boolean used;
		public String who;
		public List<String> images;
	}

	public List<GankBean> getResults()
	{
		return results;
	}
}
