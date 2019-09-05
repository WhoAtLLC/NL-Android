package com.root.wishlist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.interfaces.RecyclerItemPosition;

import java.util.ArrayList;
import java.util.List;

public class ChangeServerRecyclerViewAdapter extends RecyclerView.Adapter<ChangeServerRecyclerViewAdapter.ChangeServerRecyclerViewHolder> {

    private List<String> serverUrlList, serverInfoList;
    private int selectedPosition = -1;// no selection by default
    private RecyclerView recyclerView;
    private RecyclerItemPosition recyclerItemPosition;
    private Context context;
    private int selectedServerIndex;


    public ChangeServerRecyclerViewAdapter(List<String> serverInfoList, List<String> serverUrlList, RecyclerView recyclerView, RecyclerItemPosition recyclerItemPosition, Context context, int selectedServerIndex) {
        this.serverInfoList = serverInfoList;
        this.serverUrlList = serverUrlList;
        this.recyclerView = recyclerView;
        this.recyclerItemPosition = recyclerItemPosition;
        this.context = context;
        this.selectedServerIndex = selectedServerIndex;
    }

    @NonNull
    @Override
    public ChangeServerRecyclerViewAdapter.ChangeServerRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.change_server_adapter_item, viewGroup, false);
        return new ChangeServerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChangeServerRecyclerViewAdapter.ChangeServerRecyclerViewHolder changeServerRecyclerViewHolder, final int i) {

        changeServerRecyclerViewHolder.serverInfoTv.setText(serverInfoList.get(i));
        changeServerRecyclerViewHolder.serverUrlTv.setText(serverUrlList.get(i));

        if(selectedServerIndex==i)
        {
            changeServerRecyclerViewHolder.selectedServerCb.setChecked(true);
            selectedPosition = i;
        }


        changeServerRecyclerViewHolder.selectedServerCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (selectedPosition != i) {
                    CheckBox selectedServerCheckbox;
                    if (recyclerView.findViewHolderForAdapterPosition(selectedPosition) != null) {
                        View view = recyclerView.findViewHolderForAdapterPosition(selectedPosition).itemView;
                        selectedServerCheckbox = view.findViewById(R.id.selectedServerCb);

                        selectedServerCheckbox.setChecked(false);
                    }
                    selectedPosition = i;
                    if (recyclerView.findViewHolderForAdapterPosition(i) != null) {
                        View view = recyclerView.findViewHolderForAdapterPosition(i).itemView;
                        selectedServerCheckbox = view.findViewById(R.id.selectedServerCb);

                        selectedServerCheckbox.setChecked(true);


                    }

                      recyclerItemPosition.getItemPosition(i);

                }
            }
        });

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                CheckBox selectedServerCheckbox;
                selectedServerCheckbox = view.findViewById(R.id.selectedServerCb);
               /* recyclerItemTextView.setTextColor(getResources().getColor(R.color.lightgrey));
                recyclerItemTextView.setTextSize(14);*/
                selectedServerCheckbox.setChecked(false);
                selectedPosition = i;
            }
        });




    }

    @Override
    public int getItemCount() {
        return serverInfoList.size();
    }

    public class ChangeServerRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView serverInfoTv, serverUrlTv;
        private CheckBox selectedServerCb;

        public ChangeServerRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            serverInfoTv = itemView.findViewById(R.id.serverInfoTv);
            serverUrlTv = itemView.findViewById(R.id.serverUrlTv);
            selectedServerCb = itemView.findViewById(R.id.selectedServerCb);
        }
    }
}
