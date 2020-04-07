package com.davis.mylistviewheight;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class ListCommonAdapter extends CommonAdapter<String> {

    public ListCommonAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position, String item) {
        holder.getTextView(R.id.txt_item_info).setText(item);
    }

    public void setListViewHeight(ListView listview){
        ListAdapter adapter = listview.getAdapter();
        if(adapter == null){
            return;
        }

        int totalHeight = 0;
        // 计算ListView的宽度
        int listViewWidth = ((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth();
        int widthSpec = View.MeasureSpec.makeMeasureSpec(listViewWidth, View.MeasureSpec.AT_MOST);

        for(int i=0;i<adapter.getCount();i++){
            View view = adapter.getView(i, null, listview);
            // 这里的第一个参数必须使用widthSpec，如果使用0的话，无法计算出随内容变化而变化的Item的真正高度值
            view.measure(widthSpec, 0);
            totalHeight += view.getMeasuredHeight();
        }

        int dividerHeight = listview.getDividerHeight() * (adapter.getCount() - 1);
        totalHeight += dividerHeight;
        Log.i("ListViewHeight", "ListView DividerHeight : " + dividerHeight);

        int paddingHeight = listview.getPaddingTop() + listview.getPaddingBottom();
        totalHeight += paddingHeight;
        Log.i("ListViewHeight", "ListView PaddingHeight : " + paddingHeight);

        Log.i("ListViewHeight", "ListView TotalHeight : " + totalHeight);
        ViewGroup.LayoutParams layoutParams = listview.getLayoutParams();
        layoutParams.height = totalHeight;
        listview.setLayoutParams(layoutParams);

        this.refresh();
    }
}
