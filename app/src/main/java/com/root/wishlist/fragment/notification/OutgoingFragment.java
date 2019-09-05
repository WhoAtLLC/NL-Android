package com.root.wishlist.fragment.notification;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.root.wishlist.adapters.OutgoingRequestAdapter;
import com.root.wishlist.pojo.notification.Archived;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.pojo.notification.Outbound;
import com.root.wishlist.presentation.notification.NotificationIOAInterface;
import com.root.wishlist.presentation.notification.NotificationIOAPresentation;
import com.root.wishlist.presentation.notification.OperationInterface;
import com.root.wishlist.presentation.notification.OperationPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.NotificationIOAView;
import com.root.wishlist.view.operationView;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class OutgoingFragment extends Fragment implements NotificationIOAView, operationView {


    private OutgoingRequestAdapter outgoingRequestAdapter;
    private ArrayList<Outbound> outboundArrayList = new ArrayList<>();
    private NotificationIOAInterface notificationIOAInterface;
    private android.support.v4.widget.SwipeRefreshLayout swipelist;
    public static OperationInterface operationInterface;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView outgoingrequestlist;
    public static HashMap<String, Object> stringObjectHashMap = new HashMap<>();
    private TextView message1txt;
    private TextView yes;
    private TextView no;
    Typeface fontface;
    private TextView contactownertxt;
    private TextView prospectortxt;
    private TextView statustxt;
    private TextView updateTxt;

    public OutgoingFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        notificationIOAInterface = new NotificationIOAPresentation(activity, OutgoingFragment.this);
        notificationIOAInterface.getNotification();
        operationInterface = new OperationPresentation(activity, OutgoingFragment.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outgoing_layout, container, false);
        this.statustxt = (TextView) view.findViewById(R.id.status_txt);
        this.updateTxt = (TextView) view.findViewById(R.id.update_txt);
        this.prospectortxt = (TextView) view.findViewById(R.id.prospector_txt);
        this.contactownertxt = (TextView) view.findViewById(R.id.contactowner_txt);
        fontface = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        statustxt.setTypeface(fontface);
        prospectortxt.setTypeface(fontface);
        contactownertxt.setTypeface(fontface);
        this.outgoingrequestlist = (RecyclerView) view.findViewById(R.id.outgoing_request_list);
        this.swipelist = (SwipeRefreshLayout) view.findViewById(R.id.swipe_list);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        outgoingrequestlist.setLayoutManager(linearLayoutManager);
        outgoingrequestlist.setHasFixedSize(true);

        swipelist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                outgoingRequestAdapter.notifyDataSetChanged();
                notificationIOAInterface.getNotification();
                swipelist.setRefreshing(false);
            }
        });

        outgoingrequestlist.addOnItemTouchListener(new OutgoingRequestAdapter(getActivity(), outgoingrequestlist, new OutgoingRequestAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                archiveDelete(outboundArrayList.get(position).getRequestID(), outboundArrayList.get(position).getStatus(), position);
                //new NotificationDialog(getContext()).archiveDelete(outboundArrayList.get(position).getRequestID(), outboundArrayList.get(position).getStatus());
                // Toast.makeText(getContext(), outboundArrayList.get(position).getStatus(), Toast.LENGTH_SHORT).show();
            }
        }));
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void getInBound(List<Inbound> inboundList) {

    }

    @Override
    public void getOutBound(List<Outbound> outboundList) {
        outboundArrayList = new ArrayList<>();
        outboundArrayList.addAll(outboundList);
        Collections.reverse(outboundList);
        outgoingRequestAdapter = new OutgoingRequestAdapter(getActivity(), outboundArrayList);
        outgoingrequestlist.setAdapter(outgoingRequestAdapter);
        updateTxt.setVisibility(View.GONE);
    }

    @Override
    public void getArchived(List<Archived> archivedList) {

    }

    @Override
    public void getarchived(boolean archive) {
        Log.d("archive", String.valueOf(archive));
        notificationIOAInterface.getNotification();

    }

    @Override
    public void getdecline(boolean decline) {
        Log.d("delete", String.valueOf(decline));
        notificationIOAInterface.getNotification();


    }

    @Override
    public void getStatus(String status) {
        Log.d("status", status);

    }

    @Override
    public void newtworkError(String connection) {
        NetworkInterface networkInterface = (NetworkInterface) getContext();
        try {
            networkInterface.connectionMessage(connection);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void archiveDelete(final int requestId, final String message, final int position) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.outgoing_notification);
        this.no = (TextView) dialog.findViewById(R.id.no);
        this.yes = (TextView) dialog.findViewById(R.id.yes);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        yes.setTypeface(fontface);
        no.setTypeface(fontface);
        message1txt.setTypeface(fontface);
        if (message.equals("accepted")) {
            message1txt.setText(" archive ?");
            //message1txt.setTextColor(Color.RED);
        } else if (message.equals("pending")) {
            message1txt.setText(" delete ?");
        }
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.25);
        dialog.getWindow().setLayout(width, height);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.equals("accepted")) {
                    updateTxt.setVisibility(View.VISIBLE);
                    OutgoingFragment.stringObjectHashMap.put("comment", "");
                    OutgoingFragment.stringObjectHashMap.put("notificatioAction", "archive");
                    OutgoingFragment.stringObjectHashMap.put("notificationID", requestId);
                    OutgoingFragment.operationInterface.getArchiveId(requestId, OutgoingFragment.stringObjectHashMap);
                    dialog.dismiss();


                } else {
                    Log.d("id", String.valueOf(requestId));
                    updateTxt.setVisibility(View.VISIBLE);
                    Log.d("id", String.valueOf(requestId));
                    stringObjectHashMap.put("comment", "");
                    stringObjectHashMap.put("notificatioAction", "decline");
                    stringObjectHashMap.put("notificationID", requestId);
                    operationInterface.getDeleteId(requestId, stringObjectHashMap);
                    dialog.dismiss();
                }
                dialog.dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }
}