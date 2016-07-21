package com.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.googleplay.adapter.MyBaseAdapter;
import com.googleplay.utils.UIUtils;
import com.googleplay.viewholder.BaseViewHolder;
import com.googleplay.widget.LoadingPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:44
 * 描述：首页fragment
 */
public class HomeFragment extends BaseFragment {
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected LoadingPager.LoadResult load() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("数据" + i);
        }
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        mAdapter = new HomeAdapter(mDatas);
        listView.setAdapter(mAdapter);
        return listView;
    }

    private class HomeAdapter extends MyBaseAdapter<String> {
        private HomeViewHolder holder;
        public HomeAdapter(List<String> datas) {
            super( datas);
        }

        @Override
        public BaseViewHolder getViewHolder() {
            return new HomeViewHolder();
        }


    }

    private class HomeViewHolder extends BaseViewHolder<String> {
        public TextView textView;

        public HomeViewHolder() {
            super();
        }

        @Override
        protected View initView() {
            textView = new TextView(UIUtils.getContext());
            textView.setTextColor(Color.RED);
            return textView;
        }

        @Override
        protected void refreshView() {
            textView.setText(getData());
        }
    }
}
