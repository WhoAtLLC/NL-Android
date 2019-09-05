package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.pojo.search.SearchResult;
import com.root.wishlist.R;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    List<SearchResult> CompanyDetails;

    Context context;
    private ImageView companyimage;
    private TextView companyname;
    public SearchAdapter(Context context, List<SearchResult> CompanyDetails) {
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

        view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leads_companies, viewGroup, false));
        ImageView connection = (ImageView) view.findViewById(R.id.connection);
        companyname = (TextView) view.findViewById(R.id.company_name);
        companyimage = (ImageView) view.findViewById(R.id.company_image);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        companyname.setTypeface(typeface);

        Glide.with(context).load(CompanyDetails.get(position).getIcon()).placeholder(R.drawable.wish).error(R.drawable.wish).dontAnimate().into(companyimage);
        companyname.setText(CompanyDetails.get(position).getTitle());
        switch (CompanyDetails.get(position).getLevel()) {
            case 1:
                connection.setBackgroundResource(R.drawable.n1);
                break;
            case 2:
                connection.setBackgroundResource(R.drawable.n2);
                break;
            case 3:
                connection.setBackgroundResource(R.drawable.n1);
                break;
            case 4:
                connection.setBackgroundResource(R.drawable.n1);
                break;
            case 5:
                connection.setBackgroundResource(R.drawable.n5);
                break;
        }
        return view;
    }
}
