package com.root.wishlist.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DynamicListHeight {

    static  boolean heightOfList=false;
    static String valueOfList="";
    static View listItem;
    public static int setListViewHeightBasedOnChildren(ListView listView,int desireHeight) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            heightOfList=true;
            listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if(listAdapter.getCount()>0) {
            if (heightOfList && valueOfList.equals("OverDue")) {
                params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() + desireHeight) + (listItem.getMeasuredHeight()));
                heightOfList = false;
            } else {
                params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() + desireHeight) + (listItem.getMeasuredHeight() - 180));
            }
        }
        else
        {
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() + desireHeight));
        }
        listView.setLayoutParams(params);
        listView.requestLayout();

        return params.height;
    }





    public static void setExpandableListViewHeightBasedOnChildren(ExpandableListView listView) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            heightOfList=true;
            View listItem = listAdapter.getGroupView(i,false,null,listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getGroupCount())+60);

        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    public static void ChangeHeightForOverdue(String value)
    {
        valueOfList=value;
    }


}
