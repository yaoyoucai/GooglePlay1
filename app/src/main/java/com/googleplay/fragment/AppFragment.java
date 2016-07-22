package com.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.googleplay.adapter.BaseListAdapter;
import com.googleplay.bean.AppInfo;
import com.googleplay.http.protocol.AppProtocol;
import com.googleplay.utils.UIUtils;
import com.googleplay.widget.LoadingPager;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:44
 * 描述：
 */
public class AppFragment extends BaseFragment {

    private List<AppInfo> mDatas;

    private AppAdapter mAdapter;
    @Override
    protected LoadingPager.LoadResult load() {
        AppProtocol appProtocol=new AppProtocol();
        mDatas = appProtocol.load(0);
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView listView= new ListView(UIUtils.getContext());
        mAdapter=new AppAdapter(listView,mDatas);
        listView.setAdapter(mAdapter);
        return listView;
    }

    private class AppAdapter extends BaseListAdapter{
        public AppAdapter(ListView listView,List<AppInfo> datas) {
            super(listView,datas);
        }

        @Override
        protected List<AppInfo> onLoadMore() {
            AppProtocol appProtocol=new AppProtocol();
            mDatas = appProtocol.load(mDatas.size());
            return mDatas;
        }
    }
}
