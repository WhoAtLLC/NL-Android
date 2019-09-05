package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.pojo.members.MutualContactResult;
import com.root.wishlist.R;

import java.util.ArrayList;


public class MutualContactAdopter extends RecyclerView.Adapter<MutualContactAdopter.ViewHolder> {

    private ArrayList<MutualContactResult> mResultArrayList;
    Context context;


    public MutualContactAdopter(Context context, ArrayList<MutualContactResult> resultArrayList) {
        this.context = context;
        this.mResultArrayList = resultArrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_mutualcontact_layaout, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.name.setTypeface(typeface);
        holder.company.setTypeface(typeface);
        holder.title.setTypeface(typeface);


        if ((mResultArrayList.get(position).getTitle())!= null) {
            holder.title.setVisibility(View.VISIBLE);

            holder.title.setText("" + mResultArrayList.get(position).getTitle());
        }
        holder.company.setText(mResultArrayList.get(position).getCompany());
        holder.name.setText("" + mResultArrayList.get(position).getFirstName() + " " + mResultArrayList.get(position).getLastName());
    }

    @Override
    public int getItemCount() {

        return mResultArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView title;
        private TextView company;

        public ViewHolder(View view) {
            super(view);
            this.company = (TextView) view.findViewById(R.id.company);
            this.title = (TextView) view.findViewById(R.id.title);
            this.name = (TextView) view.findViewById(R.id.name);
        }
    }
}