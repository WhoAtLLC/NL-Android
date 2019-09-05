package com.root.wishlist.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.activities.notificationmodule.Wanttomeet;
import com.root.wishlist.pojo.notification.Outbound;
import com.root.wishlist.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class OutgoingRequestAdapter extends RecyclerView.Adapter<OutgoingRequestAdapter.ViewHolder> implements RecyclerView.OnItemTouchListener {


    Outbound inbound;
    ArrayList<Outbound> mInboundArrayList = new ArrayList<>();
    private Context context;
    private String constantsBaseUrl;
    public OutgoingRequestAdapter(Context context, ArrayList<Outbound> outboundArrayList/*,String baseURL*/) {
        this.mInboundArrayList = outboundArrayList;
        this.context = context;
     /*   this.constantsBaseUrl=baseURL;*/
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.outgoingrequestadapter, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Typeface fontface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.username.setTypeface(fontface);
        holder.prospectordesignation.setTypeface(fontface);
        holder.prospectorcompanayname.setTypeface(fontface);
        holder.profile.setTypeface(fontface);
        holder.prospectername.setTypeface(fontface);
        inbound = mInboundArrayList.get(position);
        holder.prospectordesignation.setText("" + inbound.mProspectdesignation);
        holder.prospectorcompanayname.setText(inbound.mProspectcompanyname);
        if (!mInboundArrayList.get(position).getRecipientRead()) {
            highLightText(holder, position);

        } else {
            withoughtHighlight(holder, position);
        }
       /* Glide.with(context).load(constantsBaseUrl + mInboundArrayList.get(position).getRequestorimage()).error(R.drawable.user_profile).placeholder(R.drawable.user_profile).dontAnimate().into(holder.contactowner);


    }
*/


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Wanttomeet.class);
                intent.putExtra("handler", mInboundArrayList.get(position).getContactusername());
                intent.putExtra("requestname", mInboundArrayList.get(position).getContactusername());
                intent.putExtra("prospectname", mInboundArrayList.get(position).getProspectname());
                intent.putExtra("requestID", mInboundArrayList.get(position).getRequestID());
                intent.putExtra("status", mInboundArrayList.get(position).getStatus());
                intent.putExtra("code", "Ot");
                intent.putExtra("flag", 1);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mInboundArrayList.size();
    }


    private void withoughtHighlight(ViewHolder holder, final int position) {

        holder.username.setText(mInboundArrayList.get(position).getContactusername());
        holder.prospectername.setText(mInboundArrayList.get(position).getProspectname());
        if (mInboundArrayList.get(position).getStatus().equals("pending")) {
            if (mInboundArrayList.get(position).getContactshortbio() != null)
                holder.profile.setText("" + mInboundArrayList.get(position).getContactshortbio());
            Glide.with(context).load(R.drawable.time).into(holder.status);
        } else if (mInboundArrayList.get(position).getStatus().equals("declined")) {
//            holder.profile.setText("" + inbound.mRequestordesignation);
            Glide.with(context).load(R.drawable.red_cross).into(holder.status);
            if (mInboundArrayList.get(position).getContactshortbio() != null)
                holder.profile.setText("" + mInboundArrayList.get(position).getContactshortbio());
        } else if (mInboundArrayList.get(position).getStatus().equals("accepted")) {
            Glide.with(context).load(R.drawable.checkbox).into(holder.status);
            if (inbound.mRequestordesignation != null)
                holder.profile.setText("" + inbound.mRequestordesignation);
        }

        holder.username.setTextColor(Color.parseColor("#515656"));
        holder.prospectername.setTextColor(Color.parseColor("#515656"));
    }

    public void highLightText(ViewHolder holder, int position) {


        holder.username.setText(mInboundArrayList.get(position).getContactusername());
        if (mInboundArrayList.get(position).getContactshortbio() == null)
            holder.profile.setText("");
        else
            holder.profile.setText("" + mInboundArrayList.get(position).getContactshortbio());
        holder.prospectername.setText(mInboundArrayList.get(position).getProspectname());
        holder.username.setTextColor(Color.parseColor("#5B9EEC"));
        holder.prospectername.setTextColor(Color.parseColor("#5B9EEC"));

        Glide.with(context).load(R.drawable.timer_blue).into(holder.status);
    }

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public OutgoingRequestAdapter(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

        this.clicklistener = clicklistener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clicklistener != null) {
                    clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
            clicklistener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


   /*     private CircleImageView contactowner;*/
        private TextView username;
        private TextView profile;
        private CardView cardView;
        private TextView companyname;
        private TextView prospectername, prospectordesignation, prospectorcompanayname;
        private ImageView status;

        public ViewHolder(View view) {
            super(view);
            this.companyname = (TextView) view.findViewById(R.id.companyname);
            this.profile = (TextView) view.findViewById(R.id.profile);
            this.username = (TextView) view.findViewById(R.id.username);
            this.cardView = (CardView) view.findViewById(R.id.cardView);
            this.prospectername = (TextView) view.findViewById(R.id.prospectername);
            this.prospectordesignation = (TextView) view.findViewById(R.id.prospectordesignation);
            this.prospectorcompanayname = (TextView) view.findViewById(R.id.prospectorcompanayname);

            this.status = (ImageView) view.findViewById(R.id.status);
           /* this.contactowner = (CircleImageView) view.findViewById(R.id.contact_owner);*/
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
