package com.root.wishlist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.pojo.wantto.WnatoResult;
import com.root.wishlist.R;

import java.util.List;


public class WanttoMutualContactAdapter extends RecyclerView.Adapter<WanttoMutualContactAdapter.ViewHolder> {

    List<WnatoResult> introResults;
    Context context;


    public WanttoMutualContactAdapter(Context context, List<WnatoResult> introResults) {

        this.context = context;
        this.introResults = introResults;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.intromutualcontactlist, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fullname.setText(introResults.get(position).getFirstName() + " " + introResults.get(position).getLastName());
        holder.title.setText(introResults.get(position).getTitle());
        holder.designation.setText(introResults.get(position).getCompany());

    }

    @Override
    public int getItemCount() {
        return introResults.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private android.widget.TextView fullname;
        private android.widget.TextView designation;
        private android.widget.TextView title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.designation = (TextView) view.findViewById(R.id.designation);
            this.fullname = (TextView) view.findViewById(R.id.fullname);
        }
    }
}
