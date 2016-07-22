package com.googleplay.viewholder;

import android.view.View;
import android.widget.RelativeLayout;

import com.googleplay.activity.R;
import com.googleplay.adapter.MyBaseAdapter;
import com.googleplay.utils.UIUtils;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/22 15:15
 * 描述：
 */
public class MoreViewHolder extends BaseViewHolder<Integer> implements View.OnClickListener {
    public static final int LOADING_ERROR = 0;
    public static final int HAS_MORE = 1;
    public static final int NO_MORE = 2;

    private RelativeLayout mRlLoading;
    private RelativeLayout mRlError;

    private MyBaseAdapter mAdapter;

    public <T> MoreViewHolder(boolean hasMore, MyBaseAdapter myBaseAdapter) {
        setData(hasMore ? HAS_MORE : NO_MORE);
        mAdapter = myBaseAdapter;
    }

    @Override
    protected View initView() {
        View view = UIUtils.inflate(R.layout.list_more_loading);
        mRlLoading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        mRlError = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        mRlError.setOnClickListener(this);
        return view;
    }

    @Override
    protected void refreshView() {
        mRlLoading.setVisibility(getData() == HAS_MORE ? View.VISIBLE : View.GONE);
        mRlError.setVisibility(getData() == LOADING_ERROR ? View.VISIBLE : View.GONE);
    }

    @Override
    public View getRootView() {
        if (getData() == HAS_MORE) {
            loadMore();
        }
        return super.getRootView();
    }

    private void loadMore() {
       mAdapter.loadMore();
    }

    @Override
    public void onClick(View v) {
        loadMore();
    }
}
