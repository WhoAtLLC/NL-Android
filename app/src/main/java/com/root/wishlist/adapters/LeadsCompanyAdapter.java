package com.root.wishlist.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.activities.leads.LeadsYourConnectionAtActivity;
import com.root.wishlist.pojo.leads.MemberResult;
import com.root.wishlist.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LeadsCompanyAdapter extends RecyclerView.Adapter<LeadsCompanyAdapter.ViewHolder> {
    List<MemberResult> companyDetails;
    Context context;

    public LeadsCompanyAdapter(Context context, List<MemberResult> companyDetails) {
        this.context = context;
        this.companyDetails = companyDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder;
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.leads_companies, parent, false);

        viewHolder = new ViewHolder(v);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.companyname.setTypeface(typeface);
        // Glide.clear(holder.companyimage);


        holder.companyname.setText(companyDetails.get(position).getTitle());
        int level = companyDetails.get(position).getLevel();
        //Glide.with(context).load(image).skipMemoryCache(true).placeholder(R.drawable.wish).dontAnimate().dontAnimate().into(holder.companyimage);

        Glide.with(context).load(companyDetails.get(position).getIcon()).error(R.drawable.wish).placeholder(R.drawable.wish).dontAnimate().into(holder.companyimage);
        switch (level) {
            case 1:
                holder.connection.setBackgroundResource(R.drawable.n1);
                break;
            case 2:
                holder.connection.setBackgroundResource(R.drawable.n2);
                break;
            case 3:
                holder.connection.setBackgroundResource(R.drawable.n1);
                break;
            case 4:
                holder.connection.setBackgroundResource(R.drawable.n1);
                break;
            case 5:
                holder.connection.setBackgroundResource(R.drawable.n5);
                break;
        }
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LeadsYourConnectionAtActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("userid", companyDetails.get(position).getId());
                intent.putExtra("title", companyDetails.get(position).getTitle());
               *//* String transitionName = view.getContext().getString(R.string.transition);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(), view,   // Starting view
                        transitionName    // The String
                );
                ActivityCompat.startActivity((Activity) view.getContext(), intent, options.toBundle());*//*
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return companyDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private android.widget.ImageView companyimage;
        private android.widget.TextView companyname;
        private ImageView connection;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.connection = (ImageView) view.findViewById(R.id.connection);
            this.companyname = (TextView) view.findViewById(R.id.company_name);
            this.companyimage = (ImageView) view.findViewById(R.id.company_image);
            cardView = (CardView) view.findViewById(R.id.action_bar_container);
        }
    }


}
