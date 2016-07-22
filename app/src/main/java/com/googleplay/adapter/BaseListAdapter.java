package com.googleplay.adapter;

import android.widget.ListView;

import com.googleplay.bean.AppInfo;
import com.googleplay.viewholder.BaseViewHolder;
import com.googleplay.viewholder.HomeViewHolder;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/22 14:38
 * 描述：
 */
public abstract class BaseListAdapter extends MyBaseAdapter<AppInfo> {
    public BaseListAdapter(ListView listView, List<AppInfo> datas) {
        super(listView,datas);
    }
    @Override
    public BaseViewHolder getViewHolder() {
        return new HomeViewHolder();
    }

}
