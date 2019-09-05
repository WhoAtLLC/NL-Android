package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.pojo.leads.getintroduce.IntroResult;
import com.root.wishlist.R;

import java.util.List;


public class GetMatualContactsAdapter extends RecyclerView.Adapter<GetMatualContactsAdapter.ViewHolder> implements RecyclerView.OnItemTouchListener {

    List<IntroResult> introResults;
    Context context;
    Typeface normalFont;

    public GetMatualContactsAdapter(Context context, List<IntroResult> introResults) {

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
        normalFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.fullname.setTypeface(normalFont);
        holder.title.setTypeface(normalFont);
        holder.designation.setTypeface(normalFont);
        holder.fullname.setText(introResults.get(position).getFirstName() + " " + introResults.get(position).getLastName());
        if (!(introResults.get(position).getTitle() + "").equals("null")) {
            holder.title.setText("" + introResults.get(position).getTitle());
        }

        String design = "" + introResults.get(position).getCompany();

        if (design.equals("null")) {
            holder.designation.setVisibility(View.GONE);
        } else {
            holder.designation.setText("" + design);
        }

    }


    @Override
    public int getItemCount() {
        return introResults.size();
    }

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public GetMatualContactsAdapter(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

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

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
}
