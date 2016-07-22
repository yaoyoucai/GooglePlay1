package com.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.googleplay.adapter.BaseListAdapter;
import com.googleplay.bean.AppInfo;
import com.googleplay.http.protocol.HomeProtocol;
import com.googleplay.utils.UIUtils;
import com.googleplay.widget.LoadingPager;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:44
 * 描述：首页fragment
 */
public class HomeFragment extends BaseFragment {
    private List<AppInfo> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected LoadingPager.LoadResult load() {
        HomeProtocol protocol = new HomeProtocol();
        mDatas = protocol.load(0);
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        mAdapter = new HomeAdapter(listView, mDatas);
        listView.setAdapter(mAdapter);
        return listView;
    }

    private class HomeAdapter extends BaseListAdapter {
        public HomeAdapter(ListView listView, List<AppInfo> datas) {
            super(listView, datas);
        }

        @Override
        protected List<AppInfo> onLoadMore() {
            HomeProtocol protocol = new HomeProtocol();
            mDatas = protocol.load(mDatas.size());
            return mDatas;
        }
    }


}
