package com.googleplay.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.googleplay.adapter.MyBaseAdapter;
import com.googleplay.bean.CategoryInfo;
import com.googleplay.http.protocol.CategoryProtocol;
import com.googleplay.utils.UIUtils;
import com.googleplay.viewholder.BaseViewHolder;
import com.googleplay.viewholder.CategoryTitleHolder;
import com.googleplay.viewholder.CategoryViewHolder;
import com.googleplay.widget.LoadingPager;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:45
 * 描述：
 */
public class CategoryFragment extends BaseFragment {

    private List<CategoryInfo> mDatas;

    @Override
    protected LoadingPager.LoadResult load() {
        CategoryProtocol categoryProtocol = new CategoryProtocol();
        mDatas = categoryProtocol.load(0);
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new CategoryAdapter(listView, mDatas));
        return listView;
    }

    private class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {
        private int position = 0;

        public CategoryAdapter(ListView listView, List<CategoryInfo> datas) {
            super(listView, datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            return super.getView(position, convertView, parent);
        }

        @Override
        public BaseViewHolder getViewHolder() {
            if (mDatas.get(position).isTitle()) {
                return new CategoryTitleHolder();
            } else {
                return new CategoryViewHolder();
            }
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount()+1;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        protected boolean hasMore() {
            return false;
        }

        @Override
        protected List<CategoryInfo> onLoadMore() {
            return null;
        }
    }
}
