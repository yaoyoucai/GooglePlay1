package com.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.googleplay.manager.ThreadManager;
import com.googleplay.utils.UIUtils;
import com.googleplay.viewholder.BaseViewHolder;
import com.googleplay.viewholder.MoreViewHolder;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/21 10:26
 * 描述：adapter基类
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter implements AbsListView.RecyclerListener {
    private List<T> mDatas;
    private BaseViewHolder mViewHolder;
    private MoreViewHolder mMoreViewHolder;
    private static final int LIST_ITEM_NORMAL = 0;
    private static final int LIST_ITEM_PAGING = 1;

    public MyBaseAdapter(ListView listView, List<T> datas) {
        listView.setRecyclerListener(this);
        setDatas(datas);
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size() + 1;
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
        int itemViewType = getItemViewType(position);
        if (itemViewType == LIST_ITEM_PAGING) {
            mViewHolder = getMoreViewHolder();
        } else {
            if (convertView == null) {
                mViewHolder = getViewHolder();
            } else {
                mViewHolder = (BaseViewHolder) convertView.getTag();
            }
            mViewHolder.setData(getDatas().get(position));
        }

        return mViewHolder.getRootView();
    }

    protected boolean hasMore() {
        return true;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return LIST_ITEM_PAGING;
        } else {
            return LIST_ITEM_NORMAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    public abstract BaseViewHolder getViewHolder();

    /**
     * 回收listView条目
     *
     * @param view
     */
    @Override
    public void onMovedToScrapHeap(View view) {

    }

    public MoreViewHolder getMoreViewHolder() {
        if (mMoreViewHolder == null) {
            mMoreViewHolder = new MoreViewHolder(hasMore(), this);
        }
        return mMoreViewHolder;
    }

    public void loadMore() {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                final List<T> datas = onLoadMore();
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null == datas) {
                            getMoreViewHolder().setData(MoreViewHolder.LOADING_ERROR);
                        } else if (datas.size() < 20) {
                            getMoreViewHolder().setData(MoreViewHolder.NO_MORE);
                        } else {
                            getMoreViewHolder().setData(MoreViewHolder.HAS_MORE);
                        }

                        if (null != datas) {
                            if (null != mDatas) {
                                mDatas.addAll(datas);
                            } else {
                                setDatas(datas);
                            }
                        }
                        notifyDataSetChanged();

                    }
                });

            }
        });
    }

    protected abstract List<T> onLoadMore();


}
