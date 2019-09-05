package com.root.wishlist.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.activities.leads.MemberCompanyDetailsNew;
import com.root.wishlist.pojo.leads.ResultLeads;
import com.root.wishlist.R;

import java.util.ArrayList;
import java.util.List;

public class MemberLeadAdopter extends RecyclerView.Adapter<MemberLeadAdopter.ViewHolder> {
    private Context Mcontext;
    List<ResultLeads> memberList = new ArrayList<>();

    public MemberLeadAdopter(Context context, List<ResultLeads> memberList) {
        this.Mcontext = context;
        this.memberList = memberList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.member_customlist, parent, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Typeface typeface = Typeface.createFromAsset(Mcontext.getAssets(), "Font/Gotham-Book.otf");
        holder.totalmember.setTypeface(typeface);
        holder.companudetail.setTypeface(typeface);
        holder.companyname.setTypeface(typeface);
        holder.companyname.setText(memberList.get(position).getHandle());
        holder.companudetail.setText(memberList.get(position).getShortBio());
        holder.totalmember.setText("" + memberList.get(position).getLeads());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MemberCompanyDetailsNew.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("userName", memberList.get(position).getHandle());
                intent.putExtra("short_Bio", memberList.get(position).getShortBio());
                intent.putExtra("profileImage", memberList.get(position).getAvatar());
                intent.putExtra("leads", memberList.get(position).getLeads().toString());
                view.getContext().startActivity(intent);
                Log.d("leads",memberList.get(position).getLeads().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return memberList.size();
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
            cardView = (CardView) view.findViewById(R.id.cardView);
        }
    }


}
