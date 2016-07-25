package com.googleplay.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.googleplay.activity.R;
import com.googleplay.bean.CategoryInfo;
import com.googleplay.http.HttpHelper;
import com.googleplay.utils.StringUtils;
import com.googleplay.utils.UIUtils;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/25 15:44
 * 描述：
 */
public class CategoryViewHolder extends BaseViewHolder<CategoryInfo> {

    private RelativeLayout mRl1;
    private RelativeLayout mRl2;
    private RelativeLayout mRl3;
    private ImageView mIv3;
    private ImageView mIv2;
    private ImageView mIv1;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;

    @Override
    protected View initView() {
        View view = UIUtils.inflate(R.layout.category_item);

        mRl1 = (RelativeLayout) view.findViewById(R.id.rl_1);
        mRl2 = (RelativeLayout) view.findViewById(R.id.rl_2);
        mRl3 = (RelativeLayout) view.findViewById(R.id.rl_3);

        mIv1 = (ImageView) view.findViewById(R.id.iv_1);
        mIv2 = (ImageView) view.findViewById(R.id.iv_2);
        mIv3 = (ImageView) view.findViewById(R.id.iv_3);

        mTv1 = (TextView) view.findViewById(R.id.tv_1);
        mTv2 = (TextView) view.findViewById(R.id.tv_2);
        mTv3 = (TextView) view.findViewById(R.id.tv_3);
        return view;
    }

    @Override
    protected void refreshView() {
        CategoryInfo categoryInfo = getData();

        if (StringUtils.isEmpty(categoryInfo.getImageUrl1())) {
            mIv1.setImageDrawable(null);
            mTv1.setText("");
            mRl1.setEnabled(false);
        } else {
            Glide.with(UIUtils.getContext()).load(HttpHelper.URL + "image?name=" + categoryInfo.getImageUrl1()).into(mIv1);
            mTv1.setText(categoryInfo.getName1());
            mRl1.setEnabled(true);
        }

        if (StringUtils.isEmpty(categoryInfo.getImageUrl2())) {
            mIv2.setImageDrawable(null);
            mTv2.setText("");
            mRl2.setEnabled(false);
        } else {
            Glide.with(UIUtils.getContext()).load(HttpHelper.URL + "image?name=" + categoryInfo.getImageUrl2()).into(mIv2);
            mTv2.setText(categoryInfo.getName2());
            mRl2.setEnabled(true);
        }

        if (StringUtils.isEmpty(categoryInfo.getImageUrl3())) {
            mIv3.setImageDrawable(null);
            mTv3.setText("");
            mRl3.setEnabled(false);
        } else {
            Glide.with(UIUtils.getContext()).load(HttpHelper.URL + "image?name=" + categoryInfo.getImageUrl3()).into(mIv3);
            mTv3.setText(categoryInfo.getName3());
            mRl3.setEnabled(true);
        }
    }
}
