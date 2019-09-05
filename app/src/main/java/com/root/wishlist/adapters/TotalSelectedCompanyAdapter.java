package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.R;

import java.util.ArrayList;
import java.util.List;


public class TotalSelectedCompanyAdapter extends BaseAdapter {


    List<Result> CompanyDetails;

    Context context;
    private android.widget.ImageView companyimage;
    private android.widget.TextView companyname;
    int numberOfItems = 0;
    ArrayList<Integer> itemsArrayList = new ArrayList<>();
    private CheckBox companyselect;

    public TotalSelectedCompanyAdapter(Context context, List<Result> CompanyDetails) {
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

        view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.total_selected_comopany_adapter, viewGroup, false));
        this.companyname = (TextView) view.findViewById(R.id.company_name);
        this.companyimage = (ImageView) view.findViewById(R.id.company_image);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        companyname.setTypeface(typeface);
        Glide.with(context).load(CompanyDetails.get(position).getProfileImageUrl().toString()).error(R.drawable.wish).into(companyimage);
        companyname.setText(CompanyDetails.get(position).getTitle().toString());
        return view;
    }


}
