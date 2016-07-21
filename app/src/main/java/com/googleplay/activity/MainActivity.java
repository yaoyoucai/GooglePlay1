/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 11:38
 * 描述：主activity
 */
package com.googleplay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.googleplay.fragment.BaseFragment;
import com.googleplay.utils.FragmentUtils;
import com.googleplay.utils.UIUtils;
import com.googleplay.widget.PagerTab;


public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private PagerTab mPagerTab;
    private ViewPager mViewPager;

    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mPagerTab = (PagerTab) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mPagerTab.setViewPager(mViewPager);
        mPagerTab.setOnPageChangeListener(this);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        private String[] tabNames;

        public MainAdapter(FragmentManager fm) {
            super(fm);
            tabNames = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }


        @Override
        public Fragment getItem(int position) {
            return FragmentUtils.createFragment(position);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BaseFragment fragment = FragmentUtils.createFragment(position);
        fragment.show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
