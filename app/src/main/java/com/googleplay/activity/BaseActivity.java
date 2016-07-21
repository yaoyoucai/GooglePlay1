package com.googleplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:11
 * 描述：activity抽象基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static BaseActivity mForegroundActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        mForegroundActivity = this;
    }

    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }
}
