package com.root.wishlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.root.wishlist.R;

import java.util.ArrayList;

/**
 * Created by dal on 28/9/16.
 */
public class leadsheetListAdapter extends BaseAdapter {

    ArrayList<String> notificationtime;
    Context context;

    public leadsheetListAdapter(ArrayList<String> notificationtime, Context context) {
        this.context = context;
        this.notificationtime = notificationtime;
    }

    @Override
    public int getCount() {
        return notificationtime.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

         view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leadsheetlist, viewGroup, false));
        return null;
    }
}
