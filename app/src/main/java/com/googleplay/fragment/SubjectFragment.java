package com.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.googleplay.adapter.MyBaseAdapter;
import com.googleplay.bean.SubjectInfo;
import com.googleplay.http.protocol.SubjectProtocol;
import com.googleplay.utils.UIUtils;
import com.googleplay.viewholder.BaseViewHolder;
import com.googleplay.viewholder.SubjectViewHolder;
import com.googleplay.widget.LoadingPager;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:44
 * 描述：
 */
public class SubjectFragment extends BaseFragment {

    private List<SubjectInfo> mDatas;

    @Override
    protected LoadingPager.LoadResult load() {
        SubjectProtocol subjectProtocol = new SubjectProtocol();
        mDatas = subjectProtocol.load(0);
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new SubjectAdapter(listView,mDatas));
        return listView;
    }

    private class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {
        public SubjectAdapter(ListView listView, List<SubjectInfo> datas) {
            super(listView, datas);
        }

        @Override
        public BaseViewHolder getViewHolder() {
            return new SubjectViewHolder();
        }

        @Override
        protected List<SubjectInfo> onLoadMore() {
            SubjectProtocol subjectProtocol = new SubjectProtocol();
            mDatas = subjectProtocol.load(mDatas.size());
            return mDatas;
        }
    }
}
