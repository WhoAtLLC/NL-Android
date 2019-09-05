package com.root.wishlist.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.root.wishlist.activities.leads.LeadsYourConnectionDetails;
import com.root.wishlist.pojo.leads.YourConnectionAtResult;
import com.root.wishlist.pojo.leads.YourConnectionpossibleto;
import com.root.wishlist.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MembersAdapters extends RecyclerView.Adapter<MembersAdapters.ViewHolder> implements Filterable {


    List<YourConnectionAtResult> yourConnection;
    private List<YourConnectionAtResult> mStringFilterList;
    Context context;
    private ValueFilter valueFilter;
    private String connectiontitle;
    private List<YourConnectionpossibleto> yourConnectionpossibletos = new ArrayList<>();

    public MembersAdapters(Context context, List<YourConnectionAtResult> yourConnection) {
        this.context = context;
        this.yourConnection = yourConnection;
        mStringFilterList = yourConnection;
        getFilter();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.member_customlist, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.totalmember.setTypeface(typeface);
        holder.companudetail.setTypeface(typeface);
        holder.companyname.setTypeface(typeface);
        holder.companyname.setText(yourConnection.get(position).getConnectionname());

        String val = yourConnection.get(position).getTitle();
        if (val == null)
            holder.companudetail.setVisibility(View.GONE);
        else
            holder.companudetail.setText(yourConnection.get(position).getTitle());
        holder.totalmember.setText("" + yourConnection.get(position).getConnectioncount());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectiontitle = yourConnection.get(position).getTitle();
                if (connectiontitle == null) {
                    connectiontitle = "none";
                }
                yourConnectionpossibletos = yourConnection.get(position).getConnectionpossibleto();
                Bundle yourConnectionpossibleto = new Bundle();
                yourConnectionpossibleto.putSerializable("yourConnectionpossibletos", (Serializable) yourConnectionpossibletos);
                Intent intent = new Intent(context, LeadsYourConnectionDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("connectionname", yourConnection.get(position).getConnectionname().toString());
                intent.putExtra("connectiontitle", connectiontitle);
                intent.putExtra("yourConnectionpossibleto", yourConnectionpossibleto);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return yourConnection.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {

            valueFilter = new ValueFilter();
        }

        return valueFilter;
    }

    //for filter and search
    private class ValueFilter extends Filter {


        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<YourConnectionAtResult> filterList = new ArrayList<YourConnectionAtResult>();
                for (int i = 0; i < mStringFilterList.size(); i++) {

                    if (mStringFilterList.get(i).getConnectionname().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filterList.add(new YourConnectionAtResult(mStringFilterList.get(i).getConnectionname().toString(), mStringFilterList.get(i).getTitle(), mStringFilterList.get(i).getConnectioncount(), mStringFilterList.get(i).getConnectionpossibleto()));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }

            return results;
        }

        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            yourConnection = (ArrayList<YourConnectionAtResult>) results.values;
            notifyDataSetChanged();

        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView companyname;
        private TextView companudetail;
        private TextView totalmember;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.totalmember = (TextView) view.findViewById(R.id.total_member);
            this.companudetail = (TextView) view.findViewById(R.id.company_details);
            this.companyname = (TextView) view.findViewById(R.id.company_name);
            this.cardView = (CardView) view.findViewById(R.id.cardView);

        }
    }
}
