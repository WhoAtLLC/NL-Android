package com.root.wishlist.fragment.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;
import com.root.wishlist.R;
import com.root.wishlist.util.SquareImageView;

import java.util.List;


public class NotificationWantToCompanyAdapter extends RecyclerView.Adapter<NotificationWantToCompanyAdapter.ViewHolder> {
    Context context;
    List<CompaniesofInterest> companies;


    public NotificationWantToCompanyAdapter(List<CompaniesofInterest> companies, Context context) {
        this.context = context;
        this.companies = companies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.getintrocompany, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.companyname.setText(companies.get(i).getTitle());
        Glide.clear(holder.companyimage);
        Glide.with(context).load(companies.get(i).getProfileImageUrl()).priority(Priority.IMMEDIATE).placeholder(R.drawable.wish).into(holder.companyimage);

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.TextView companyname;
        private com.root.wishlist.util.SquareImageView companyimage;

        public ViewHolder(View view) {
            super(view);
            this.companyimage = (SquareImageView) view.findViewById(R.id.company_image);
            this.companyname = (TextView) view.findViewById(R.id.company_name);
        }
    }
}
