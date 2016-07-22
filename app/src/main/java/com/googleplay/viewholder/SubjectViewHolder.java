package com.googleplay.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.googleplay.activity.R;
import com.googleplay.bean.SubjectInfo;
import com.googleplay.http.HttpHelper;
import com.googleplay.utils.UIUtils;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/22 16:58
 * 描述：
 */
public class SubjectViewHolder extends BaseViewHolder<SubjectInfo> {
    private ImageView mIcon;
    private TextView mDes;

    @Override
    protected View initView() {
        View view = UIUtils.inflate(R.layout.subject_item);
        mIcon = (ImageView) view.findViewById(R.id.item_icon);
        mDes = (TextView) view.findViewById(R.id.item_txt);
        return view;
    }

    @Override
    protected void refreshView() {
        SubjectInfo subjectInfo = getData();
        Glide.with(UIUtils.getContext()).load(HttpHelper.URL+"image?name="+subjectInfo.getUrl()).into(mIcon);
        mDes.setText(subjectInfo.getDes());
    }
}
