package com.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.googleplay.utils.UIUtils;
import com.googleplay.widget.LoadingPager;

import java.util.List;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:38
 * 描述：fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected LoadingPager mPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPager=new LoadingPager(UIUtils.getContext()) {
            @Override
            protected View createSuccessView() {
                return BaseFragment.this.createSuccessView();
            }

            @Override
            protected LoadResult load() {
                return BaseFragment.this.load();
            }
        };
        return mPager;
    }

    /**
     * 检查数据
      * @param mDatas
     * @return
     */
    protected LoadingPager.LoadResult checkData(Object mDatas) {
        if (mDatas == null) {
            return LoadingPager.LoadResult.ERROR;
        } else if (mDatas instanceof List) {
            List dataList = (List) mDatas;
            if (dataList.size() == 0) {
                return LoadingPager.LoadResult.EMPTY;
            }
        }

        return LoadingPager.LoadResult.SUCCESS;
    }

    protected abstract LoadingPager.LoadResult load() ;

    protected abstract View createSuccessView();

    public void show(){
        mPager.show();
    };
}
