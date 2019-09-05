package com.root.wishlist.adapters;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.R;

import java.util.ArrayList;

public class MyWishListMembersAdopter extends RecyclerView.Adapter<MyWishListMembersAdopter.ViewHolder> {

    private ArrayList<Result> resultArrayList;
    Context context;
    Typeface normaFont;

    public MyWishListMembersAdopter(Context context, ArrayList<Result> resultArrayList) {
        this.context = context;
        this.resultArrayList = resultArrayList;
    }

    @Override
    public MyWishListMembersAdopter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.mywish_custom_listadopter, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyWishListMembersAdopter.ViewHolder holder, int position) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.title.setTypeface(typeface);
        holder.lead.setTypeface(typeface);
        holder.title.setText(resultArrayList.get(position).getTitle());
        holder.lead.setText("" + resultArrayList.get(position).getLeads());
    }

    @Override
    public int getItemCount() {
        return resultArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView companyimage;
        private TextView title;
        private TextView lead;

        public ViewHolder(View view) {
            super(view);
            this.lead = (TextView) view.findViewById(R.id.lead);
            this.title = (TextView) view.findViewById(R.id.title);
            this.companyimage = (ImageView) view.findViewById(R.id.company_image);
        }
    }
}
