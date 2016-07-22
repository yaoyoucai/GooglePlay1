package com.googleplay.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.googleplay.activity.R;
import com.googleplay.bean.AppInfo;
import com.googleplay.http.HttpHelper;
import com.googleplay.utils.StringUtils;
import com.googleplay.utils.UIUtils;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/22 13:28
 * 描述：
 */
 public class HomeViewHolder extends BaseViewHolder<AppInfo> {
    private ImageView mIcon;
    private RatingBar mRating;
    private TextView mTitle;
    private TextView mSize;
    private TextView mDes;
    public HomeViewHolder() {
        super();
    }

    @Override
    protected View initView() {
        View view= UIUtils.inflate(R.layout.list_item);
        mIcon= (ImageView) view.findViewById(R.id.item_icon);
        mRating= (RatingBar) view.findViewById(R.id.item_rating);
        mTitle= (TextView) view.findViewById(R.id.item_title);
        mSize= (TextView) view.findViewById(R.id.item_size);
        mDes= (TextView) view.findViewById(R.id.item_bottom);
        return view;
    }

    @Override
    protected void refreshView() {
        AppInfo appInfo=getData();
        Glide.with(UIUtils.getContext()).load(HttpHelper.URL+"image?name="+appInfo.getIconUrl()).into(mIcon);
        mRating.setRating(appInfo.getStars());
        mTitle.setText(appInfo.getName());
        mSize.setText(StringUtils.formatFileSize(appInfo.getSize()));
        mDes.setText(appInfo.getDes());
    }
}
