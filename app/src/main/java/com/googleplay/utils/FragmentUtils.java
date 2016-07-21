package com.googleplay.utils;

import com.googleplay.fragment.AppFragment;
import com.googleplay.fragment.BaseFragment;
import com.googleplay.fragment.CategoryFragment;
import com.googleplay.fragment.GameFragment;
import com.googleplay.fragment.HomeFragment;
import com.googleplay.fragment.HotFragment;
import com.googleplay.fragment.RecommendFragment;
import com.googleplay.fragment.SubjectFragment;

import java.util.HashMap;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:48
 * 描述：创建fragment的工具类
 */
public class FragmentUtils {
    private static HashMap<Integer, BaseFragment> mFragments = new HashMap<>();

    private static final int TAB_HOME = 0;
    private static final int TAB_APP = 1;
    private static final int TAB_GAME = 2;
    private static final int TAB_SUBJECT = 3;
    private static final int TAB_RECOMMEND = 4;
    private static final int TAB_CATEGORY = 5;
    private static final int TAB_HOT = 6;

    /**
     * 创建fragment
     *
     * @param position
     * @return
     */
    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case TAB_HOME:
                    fragment = new HomeFragment();
                    break;
                case TAB_APP:
                    fragment = new AppFragment();
                    break;
                case TAB_GAME:
                    fragment = new GameFragment();
                    break;
                case TAB_SUBJECT:
                    fragment = new SubjectFragment();
                    break;
                case TAB_RECOMMEND:
                    fragment = new RecommendFragment();
                    break;
                case TAB_CATEGORY:
                    fragment = new CategoryFragment();
                    break;
                case TAB_HOT:
                    fragment = new HotFragment();
                    break;
            }
            mFragments.put(position, fragment);
        }


        return fragment;
    }
}
