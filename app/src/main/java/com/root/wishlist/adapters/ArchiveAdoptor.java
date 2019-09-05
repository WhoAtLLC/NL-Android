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
import com.root.wishlist.pojo.notification.Archived;
import com.root.wishlist.R;

import java.util.ArrayList;

public class ArchiveAdoptor extends RecyclerView.Adapter<ArchiveAdoptor.ViewHolder> implements RecyclerView.OnItemTouchListener {
    private Context context;
    ArrayList<Archived> archivedArrayList = new ArrayList<>();

    public ArchiveAdoptor(Context context, ArrayList<Archived> archivedArrayList) {

        this.archivedArrayList = archivedArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.archivecustom_layout, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Typeface fontface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.username.setTypeface(fontface);
        holder.prospectername.setTypeface(fontface);
        if (archivedArrayList.get(position).getHighlight()) {
            holder.username.setText(archivedArrayList.get(position).getContactusername());
            holder.prospectername.setText(archivedArrayList.get(position).getProspectname());
            holder.username.setTextColor(Color.parseColor("#5B9EEC"));
            holder.prospectername.setTextColor(Color.parseColor("#5B9EEC"));
        } else {
            holder.username.setText(archivedArrayList.get(position).getContactusername());
            holder.prospectername.setText(archivedArrayList.get(position).getProspectname());

        }
        if (archivedArrayList.get(position).getStatus().equals("pending")) {
            Glide.with(context).load(R.drawable.time).into(holder.status);
        } else if (archivedArrayList.get(position).getStatus().equals("declined")) {
            Glide.with(context).load(R.drawable.red_cross).into(holder.status);
        } else if (archivedArrayList.get(position).getStatus().equals("accepted")) {
            Glide.with(context).load(R.drawable.checkbox).into(holder.status);
        }
       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Wanttomeet.class);
                intent.putExtra("requestname", archivedArrayList.get(position).getContactusername());
                intent.putExtra("prospectname", archivedArrayList.get(position).getProspectname());
                intent.putExtra("requestID", archivedArrayList.get(position).getRequestID());
                intent.putExtra("status", archivedArrayList.get(position).getStatus());
                intent.putExtra("handler", archivedArrayList.get(position).getContactusername());
                intent.putExtra("code", "Ar");
                intent.putExtra("flag", 2);
                view.getContext().startActivity(intent);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        return archivedArrayList.size();
    }

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public ArchiveAdoptor(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

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
        private TextView prospectername;
        private ImageView status;
        private TextView username;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            this.username = (TextView) view.findViewById(R.id.username);
            this.prospectername = (TextView) view.findViewById(R.id.prospectername);
            this.status = (ImageView) view.findViewById(R.id.status);
            this.cardView = (CardView) view.findViewById(R.id.cardView);

        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}