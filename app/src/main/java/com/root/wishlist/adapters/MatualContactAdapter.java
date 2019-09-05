package com.root.wishlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.root.wishlist.R;

import java.util.ArrayList;

/**
 * Created by dal on 23/9/16.
 */
public class MatualContactAdapter extends BaseAdapter {

    ArrayList<String> name = new ArrayList<>();
    Context context;
    private android.widget.TextView contactname;
    private android.widget.TextView profile;
    private android.widget.TextView companydescription;

    public MatualContactAdapter(Context context, ArrayList<String> name) {
        this.context = context;
        this.name = name;

    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {

         view1 = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matualcontactlist, viewGroup, false));
        this.companydescription = (TextView) view1.findViewById(R.id.companydescription);
        this.profile = (TextView) view1.findViewById(R.id.profile);
        this.contactname = (TextView) view1.findViewById(R.id.contactname);

        contactname.setText(name.get(i).toString());
        return view1;
    }
}
