package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.root.wishlist.R;
import com.root.wishlist.pojo.mywishlist.Result;

import java.util.ArrayList;
import java.util.List;


public class CompanyOfInterestAdapter extends RecyclerView.Adapter<CompanyOfInterestAdapter.ViewHolder> {


    List<Result> CompanyDetails;
    Context context;
    int numberOfItems = 0;
    ArrayList<Integer> itemsArrayList = new ArrayList<>();
    private CheckBox companyselect;

    public CompanyOfInterestAdapter(Context context, List<Result> CompanyDetails) {
        this.context = context;
        this.CompanyDetails = CompanyDetails;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.company_of_interest_adapter, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.companyname.setTypeface(typeface);
        holder.companyimage.setImageResource(R.drawable.wish);
        Glide.with(context).load(CompanyDetails.get(position).getProfileImageUrl().toString()).error(R.drawable.wish).placeholder(R.drawable.wish).into(holder.companyimage);
        holder.companyname.setText(CompanyDetails.get(position).getTitle().toString());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return CompanyDetails.size();
    }

    //for counter update
    public interface UpdateNumberOfItems {
        void updateCounter(int counter);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.ImageView companyimage;
        private android.widget.TextView companyname;

        public ViewHolder(View view) {
            super(view);
            this.companyname = (TextView) view.findViewById(R.id.company_name);
            this.companyimage = (ImageView) view.findViewById(R.id.company_image);
        }
    }
}
