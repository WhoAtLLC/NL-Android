package com.root.wishlist.adapters;

import android.content.Context;
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
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class IncomingRequestAdapter extends RecyclerView.Adapter<IncomingRequestAdapter.ViewHolder> implements RecyclerView.OnItemTouchListener {
    private Context context;
    List<Inbound> mInboundArrayList = new ArrayList<>();

    Inbound inbound;
    private String constantsBaseUrl;

    public IncomingRequestAdapter(Context context, List<Inbound> mInboundArrayList, String baseURL) {

        this.mInboundArrayList = mInboundArrayList;
        this.context = context;
        this.constantsBaseUrl=baseURL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.incomingrequestadapter, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Typeface fontface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.username.setTypeface(fontface);
        holder.profile.setTypeface(fontface);
        holder.companyname.setTypeface(fontface);
        holder.prospectername.setTypeface(fontface);
        holder.prospectordesignation.setTypeface(fontface);
        holder.prospectorcompanayname.setTypeface(fontface);

        inbound = mInboundArrayList.get(position);
        if (mInboundArrayList.get(position).getHighlight()) {
            highLightText(holder, position);

        } else {
            withOughtHighLight(holder, position);
        }
        Glide.with(context).load(constantsBaseUrl + mInboundArrayList.get(position).getRequestorimage()).error(R.drawable.user_profile).placeholder(R.drawable.user_profile).dontAnimate().into(holder.contactowner);


    }


    @Override
    public int getItemCount() {
        return mInboundArrayList.size();
    }


    private void withOughtHighLight(ViewHolder viewHolder, int position) {

        viewHolder.username.setText(mInboundArrayList.get(position).getRequestorname());
        viewHolder.profile.setText("" + inbound.mRequestordesignation);
        viewHolder.companyname.setText("" + inbound.mRequestorcompanyname);
        viewHolder.prospectername.setText(mInboundArrayList.get(position).getProspectname());
        viewHolder.prospectordesignation.setText("" + inbound.mProspectdesignation);
        viewHolder.prospectorcompanayname.setText(mInboundArrayList.get(position).getProspectcompanyname());
        if (mInboundArrayList.get(position).getStatus().equals("pending")) {
            Glide.with(context).load(R.drawable.time).into(viewHolder.status);
        } else if (mInboundArrayList.get(position).getStatus().equals("declined")) {
            Glide.with(context).load(R.drawable.red_cross).into(viewHolder.status);
        } else if (mInboundArrayList.get(position).getStatus().equals("accepted")) {
            Glide.with(context).load(R.drawable.checkbox).into(viewHolder.status);
        }
    }

    public void highLightText(ViewHolder viewHolder, int position) {
        viewHolder.username.setText(mInboundArrayList.get(position).getRequestorname());
        viewHolder.profile.setText("" + inbound.mRequestordesignation);
        viewHolder.companyname.setText("" + inbound.mRequestorcompanyname);
        viewHolder.prospectername.setText(mInboundArrayList.get(position).getProspectname());
        viewHolder.prospectordesignation.setText("" + inbound.mProspectdesignation);
        viewHolder.prospectorcompanayname.setText(mInboundArrayList.get(position).getProspectcompanyname());
        viewHolder.username.setTextColor(Color.parseColor("#5B9EEC"));
        viewHolder.prospectorcompanayname.setTextColor(Color.parseColor("#5B9EEC"));
        Glide.with(context).load(R.drawable.timer_blue).into(viewHolder.status);


    }

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public IncomingRequestAdapter(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

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
        private CircleImageView contactowner;
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
            this.contactowner = (CircleImageView) view.findViewById(R.id.contact_owner);
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
