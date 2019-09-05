package com.root.wishlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.root.wishlist.pojo.search.SearchResult;
import com.root.wishlist.R;

import java.util.List;


public class HotSearcAdapter extends BaseAdapter {

    List<SearchResult> CompanyDetails;
    Context context;
    private android.widget.TextView companyname;

    public HotSearcAdapter(Context context, List<SearchResult> CompanyDetails) {
        this.context = context;
        this.CompanyDetails = CompanyDetails;
    }

    @Override
    public int getCount() {
        return CompanyDetails.size();
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
    public View getView(final int position, View view, ViewGroup viewGroup) {

        view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hotsearchcompany, viewGroup, false));
        this.companyname = (TextView) view.findViewById(R.id.company_name);
        companyname.setText(CompanyDetails.get(position).getTitle().toString());

        return view;
    }
}
