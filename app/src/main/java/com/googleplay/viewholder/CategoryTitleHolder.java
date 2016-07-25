package com.googleplay.viewholder;

import android.view.View;
import android.widget.TextView;

import com.googleplay.activity.R;
import com.googleplay.bean.CategoryInfo;
import com.googleplay.utils.UIUtils;

/**
 * 作者：yh
 * 版本：1.0
 * 创建日期：2016/7/25 15:41
 * 描述：
 */
public class CategoryTitleHolder extends BaseViewHolder<CategoryInfo> {

    private TextView mTitle;

    @Override
    protected View initView() {
        View view = UIUtils.inflate(R.layout.category_item_title);
        mTitle = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    protected void refreshView() {
        CategoryInfo categoryInfo = getData();
        mTitle.setText(categoryInfo.getTitle());
    }
}
