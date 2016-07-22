package com.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.googleplay.adapter.BaseListAdapter;
import com.googleplay.bean.AppInfo;
import com.googleplay.http.protocol.GameProtocol;
import com.googleplay.utils.UIUtils;
import com.googleplay.widget.LoadingPager;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:44
 * 描述：
 */
public class GameFragment extends BaseFragment {

    private List<AppInfo> mDatas;

    @Override
    protected LoadingPager.LoadResult load() {
        GameProtocol gameProtocol = new GameProtocol();
        mDatas = gameProtocol.load(0);
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new GameAdapter(listView, mDatas));
        return listView;
    }

    protected class GameAdapter extends BaseListAdapter {
        public GameAdapter(ListView listView, List<AppInfo> datas) {
            super(listView, datas);
        }

        @Override
        protected List<AppInfo> onLoadMore() {
            GameProtocol gameProtocol = new GameProtocol();
            mDatas = gameProtocol.load(mDatas.size());
            return mDatas;
        }
    }
}
