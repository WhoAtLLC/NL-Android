package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.R;
import com.root.wishlist.util.SquareImageView;

import java.util.List;

public class GetIntroCompaniesAdapter extends RecyclerView.Adapter<GetIntroCompaniesAdapter.ViewHolder> {
    Context context;
    List<IntroCompanies> companies;
    Typeface normalFont;

    public GetIntroCompaniesAdapter(List<IntroCompanies> companies, Context context) {
        this.context = context;
        this.companies = companies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.getintrocompany, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.companyname.setText(companies.get(position).getTitle());
        Glide.with(context).load(companies.get(position).getProfileImageUrl()).error(R.drawable.wish).placeholder(R.drawable.wish).into(holder.companyimage);
        normalFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.companyname.setTypeface(normalFont);
    }


    @Override
    public int getItemCount() {
        return companies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView companyname;
        private com.root.wishlist.util.SquareImageView companyimage;

        public ViewHolder(View view) {
            super(view);
            this.companyimage = (SquareImageView) view.findViewById(R.id.company_image);
            this.companyname = (TextView) view.findViewById(R.id.company_name);
        }
    }
}
