package com.googleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.googleplay.activity.R;
import com.googleplay.manager.ThreadManager;
import com.googleplay.utils.UIUtils;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 15:40
 * 描述：加载页面
 */
public abstract class LoadingPager extends FrameLayout {
    //默认状态
    private static final int UN_LOADING = 0;
    //加载状态
    private static final int LOADING = 1;
    //加载失败状态
    private static final int ERROR = 2;
    //加载成功，但服务器没有返回数据
    private static final int EMPTY = 3;
    //加载成功，并且服务器有数据返回
    private static final int SUCCESS = 4;
    private int mState;

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    public LoadingPager(Context context) {
        this(context, null);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mState = UN_LOADING;

        mLoadingView = createLoadingView();
        if (mLoadingView != null) {
            addView(mLoadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mErrorView = createErrorView();
        if (mErrorView != null) {
            addView(mErrorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        mEmptyView = createEmptyView();
        if (mEmptyView != null) {
            addView(mEmptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        showSafePage();
    }

    private void showSafePage() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(mState == LOADING ? VISIBLE : INVISIBLE);
        }

        if (mErrorView != null) {
            mErrorView.setVisibility(mState == ERROR ? VISIBLE : INVISIBLE);
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(mState == EMPTY ? VISIBLE : INVISIBLE);
        }

        if (mSuccessView == null && mState == SUCCESS) {
            mSuccessView = createSuccessView();
            addView(mSuccessView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        if (mSuccessView != null) {
            mSuccessView.setVisibility(mState == SUCCESS ? VISIBLE : INVISIBLE);
        }
    }


    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }

    private View createErrorView() {
        return UIUtils.inflate(R.layout.loading_page_error);
    }

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    protected abstract View createSuccessView();


    public void show() {
        if (mState == ERROR || mState == EMPTY) {
            mState = UN_LOADING;
        }
        if (mState == UN_LOADING) {
            mState = LOADING;
            LoadTask task = new LoadTask();
            ThreadManager.getLongPool().execute(task);
        }
        showSafePage();
    }

    private class LoadTask implements Runnable {
        @Override
        public void run() {
            final LoadResult result = load();
            UIUtils.runInMainThread(new Runnable() {
                @Override
                public void run() {
                    mState = result.getValue();
                    showPage();
                }
            });
        }

    }

    protected abstract LoadResult load();

    public enum LoadResult {
        ERROR(2), EMPTY(3), SUCCESS(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
