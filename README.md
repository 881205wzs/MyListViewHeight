# MyListViewHeight
动态计算ListView高度

## 效果图
<img src="https://github.com/881205wzs/MyListViewHeight/raw/master/default_1.jpg" height="420" width="200"/>&nbsp;<img src="https://github.com/881205wzs/MyListViewHeight/raw/master/default_2.jpg" height="420" width="200"/>

## 说明：

关键代码：
```java
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
            // 这里的第一个参数必须使用widthSpec，
            // 如果使用0的话，无法计算出随内容变化而变化的Item的真正高度值
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
```

详情见：<a href="https://davis.blog.csdn.net/article/details/105369693">Android 动态计算ListView高度</a>
