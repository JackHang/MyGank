package com.jackhang.gank.enums;

import android.support.v4.app.Fragment;

import com.jackhang.gank.R;
import com.jackhang.gank.ui.fragment.all.AllFragment;
import com.jackhang.gank.ui.fragment.fun.FunFragment;
import com.jackhang.gank.ui.fragment.home.HomeFragment;


public enum TabMenus {
    TAB_HOME("feed", R.drawable.ic_bottomtabbar_feed, HomeFragment.class, new KeyValue("title", "all")),
    TAB_ALL("discover", R.drawable.ic_bottomtabbar_discover, AllFragment.class),
    TAB_CUSTOM("fun", R.drawable.ic_bottomtabbar_laugh, FunFragment.class);

    String tag;
    Class<? extends Fragment> clazz;
    int iconId;
    KeyValue[] arguments = null;

    TabMenus(String tag, int iconId, Class<? extends Fragment> clazz, KeyValue... arguments) {
        this.tag = tag;
        this.iconId = iconId;
        this.clazz = clazz;
        this.arguments = arguments;
    }

    public String getTag() {
        return tag;
    }

    public Class<? extends Fragment> getClazz() {
        return clazz;
    }

    public int getIconId() {
        return iconId;
    }

    public KeyValue[] getArguments() {
        return arguments;
    }
}
