package com.googleplay.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.googleplay.http.protocol.RecommendProtocol;
import com.googleplay.utils.UIUtils;
import com.googleplay.widget.LoadingPager;
import com.googleplay.widget.randomLayout.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/20 14:44
 * 描述：
 */
public class RecommendFragment extends BaseFragment {

    private List<String> mDatas;

    @Override
    protected LoadingPager.LoadResult load() {
        RecommendProtocol recommendProtocol = new RecommendProtocol();
        mDatas = recommendProtocol.load(0);
        return checkData(mDatas);
    }

    @Override
    protected View createSuccessView() {
        StellarMap stellarMap = new StellarMap(UIUtils.getContext());
        //设置x轴和y轴的规则
        stellarMap.setRegularity(6, 9);
        int padding = UIUtils.dip2px(13);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setAdapter(new RecommendAdapter());
        return stellarMap;
    }

    private class RecommendAdapter implements StellarMap.Adapter {

        private Random mRandom;

        public RecommendAdapter() {
            mRandom = new Random();
        }

        /**
         * 一共有多少组数据
         *
         * @return
         */
        @Override
        public int getGroupCount() {
            return 2;
        }

        /**
         * 每一组有多少数据
         *
         * @param group
         * @return
         */
        @Override
        public int getCount(int group) {
            return 15;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            TextView textView = new TextView(UIUtils.getContext());

            int red = 20 + mRandom.nextInt(220);
            int green = 20 + mRandom.nextInt(220);
            int blue = 20 + mRandom.nextInt(220);
            int rgb = Color.rgb(red, green, blue);
            textView.setTextColor(rgb);

            int size = 10 + mRandom.nextInt(15);
            textView.setTextSize(size);

            textView.setText(mDatas.get(position));
            return textView;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return (group + 1) % 2;
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return (group + 1) % 2;
        }
    }
}
