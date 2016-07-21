package com.googleplay.viewholder;

import android.view.View;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 18:43
 * 描述：viewHolder基类
 */
public abstract class BaseViewHolder<T> {
    private View mView;
    private T mData;
    public BaseViewHolder() {
        this.mView = initView();
        mView.setTag(this);
    }

    protected abstract View initView();

    public View getRootView() {
        return mView;
    }

    public void setData(T data) {
        this.mData = data;
        refreshView();
    }

    protected abstract void refreshView();

    public T getData() {
        return mData;
    }
}
