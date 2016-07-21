package com.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.googleplay.viewholder.BaseViewHolder;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/21 10:26
 * 描述：adapter基类
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private List<T> mDatas;
    private BaseViewHolder mViewHolder;

    public MyBaseAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewHolder =getViewHolder();
        } else {
            mViewHolder = (BaseViewHolder) convertView.getTag();
        }
        mViewHolder.setData(getDatas().get(position));
        return mViewHolder.getRootView();
    }


    public abstract BaseViewHolder getViewHolder();
}
