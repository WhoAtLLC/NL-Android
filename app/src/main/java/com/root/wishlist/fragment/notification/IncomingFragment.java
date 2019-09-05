package com.root.wishlist.fragment.notification;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.root.wishlist.R;
import com.root.wishlist.activities.NotificationActivity;
import com.root.wishlist.activities.notificationmodule.Wanttomeet;
import com.root.wishlist.adapters.IncomingRequestAdapter;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.Constants;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.util.globalValues.coachmark.YourConnectionCoachmark;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.pojo.notification.Archived;
import com.root.wishlist.pojo.notification.Inbound;
import com.root.wishlist.pojo.notification.Outbound;
import com.root.wishlist.presentation.notification.NotificationIOAInterface;
import com.root.wishlist.presentation.notification.NotificationIOAPresentation;
import com.root.wishlist.presentation.notification.OperationInterface;
import com.root.wishlist.presentation.notification.OperationPresentation;
import com.root.wishlist.util.OnclickListenerRecycle;
import com.root.wishlist.view.NotificationIOAView;
import com.root.wishlist.view.operationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IncomingFragment extends Fragment implements NotificationIOAView, operationView {

    private TextView accept;
    private TextView decline;
    private TextView block;
    private android.widget.LinearLayout accptlyt;
    private TextView archive;
    private TextView calcle;
    private android.widget.LinearLayout archivelyt;
    //dialog

    private IncomingRequestAdapter incomingRequestAdapter;
    private ArrayList<Inbound> inboundArrayList = new ArrayList<>();
    private NotificationIOAInterface notificationIOAInterface;
    private Inbound inbound;
    WishlistProgressDialog wishlistProgressDialog;
    public static OperationInterface operationInterface;
    private android.support.v4.widget.SwipeRefreshLayout swipelist;

    public static HashMap<String, Object> stringObjectHashMap = new HashMap<>();
    private TextView updatetxt;
    private RecyclerView incomingrequestlist;
    LinearLayoutManager linearLayoutManager;
    SharedDatabase sharedDatabase;
    private TextView requestertxt;
    private TextView prospectortxt;
    private TextView statustxt;
    Typeface fontface;
    private TextView message1ext;
    private Constants constants;

    public IncomingFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sharedDatabase = new SharedDatabase(getActivity());
        constants = new Constants(getActivity());
        wishlistProgressDialog = new WishlistProgressDialog(getContext());
        notificationIOAInterface = new NotificationIOAPresentation(activity, IncomingFragment.this);
        wishlistProgressDialog.dialogShow();
        notificationIOAInterface.getNotification();
        operationInterface = new OperationPresentation(activity, IncomingFragment.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.incoming_layout, container, false);
        fontface = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        this.statustxt = (TextView) view.findViewById(R.id.status_txt);
        this.prospectortxt = (TextView) view.findViewById(R.id.prospector_txt);
        this.requestertxt = (TextView) view.findViewById(R.id.requester_txt);


        statustxt.setTypeface(fontface);
        prospectortxt.setTypeface(fontface);
        requestertxt.setTypeface(fontface);
        if (sharedDatabase.getnotification() == false) {
            new YourConnectionCoachmark(getActivity()).validationdialog("notification_page", true);
        }
        this.incomingrequestlist = (RecyclerView) view.findViewById(R.id.incoming_request_list);
        this.updatetxt = (TextView) view.findViewById(R.id.update_txt);
        this.swipelist = (SwipeRefreshLayout) view.findViewById(R.id.swipe_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        incomingrequestlist.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        incomingrequestlist.setLayoutManager(linearLayoutManager);
        swipelist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                notificationIOAInterface.getNotification();
                swipelist.setRefreshing(false);
                NotificationActivity.viewpagerAdapter.notifyDataSetChanged();
            }
        });

        incomingrequestlist.addOnItemTouchListener(new IncomingRequestAdapter(getActivity(), incomingrequestlist, new IncomingRequestAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                archiveDelete(position, inboundArrayList.get(position).getStatus(), inboundArrayList.get(position).getRequestID());
            }
        }));

        incomingrequestlist.addOnItemTouchListener(new OnclickListenerRecycle(getContext(), incomingrequestlist, new OnclickListenerRecycle.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getContext(), Wanttomeet.class);
                intent.putExtra("handler", inboundArrayList.get(position).getRequestorusername());
                intent.putExtra("requestname", inboundArrayList.get(position).getRequestorname());
                intent.putExtra("prospectname", inboundArrayList.get(position).getProspectname());
                intent.putExtra("requestID", inboundArrayList.get(position).getRequestID());
                intent.putExtra("status", inboundArrayList.get(position).getStatus());
                intent.putExtra("code", "In");
                intent.putExtra("flag", 0);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getInBound(List<Inbound> inboundList) {
        wishlistProgressDialog.dismissDialog();
        updatetxt.setVisibility(View.GONE);
        String requestorcompanyname;
        String prospectdesignation;
        String requestordesignation;
        inboundArrayList = new ArrayList<>();
        for (int i = 0; i < inboundList.size(); i++) {
            try {

                int requestId = inboundList.get(i).getRequestID();
                boolean heighlight = inboundList.get(i).getHighlight();
                String requestorname = inboundList.get(i).getRequestorname();
                String prospectname = inboundList.get(i).getProspectname();
                String status = inboundList.get(i).getStatus();
                String requestorimage = inboundList.get(i).getRequestorimage();
                String prospectcompanyname = inboundList.get(i).getProspectcompanyname();
                if (!inboundList.get(i).getProspectdesignation().isEmpty()) {
                    prospectdesignation = inboundList.get(i).getProspectdesignation().get(0);
                } else {
                    prospectdesignation = "";
                }
                if (!inboundList.get(i).getRequestordesignation().isEmpty()) {
                    requestordesignation = inboundList.get(i).getRequestordesignation().get(0);
                } else {
                    requestordesignation = "";
                }

                if (!inboundList.get(i).getRequestorcompanyname().isEmpty()) {
                    requestorcompanyname = inboundList.get(i).getRequestorcompanyname().get(0);
                } else {
                    requestorcompanyname = "";
                }


                inbound = new Inbound(inboundList.get(i).getRequestorusername(), requestId, status, requestorname, prospectname, prospectdesignation, requestordesignation, prospectcompanyname, requestorcompanyname, requestorimage, heighlight);
                inboundArrayList.add(inbound);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        incomingRequestAdapter = new IncomingRequestAdapter(getActivity(), inboundArrayList, constants.BASE_URL);
        incomingrequestlist.setAdapter(incomingRequestAdapter);
        updatetxt.setVisibility(View.GONE);
    }

    @Override
    public void getOutBound(List<Outbound> outboundList) {

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
        notificationIOAInterface.getNotification();

    }

    @Override
    public void newtworkError(String connection) {
        wishlistProgressDialog.dismissDialog();
        //new AlertDialogBox(getContext()).networkMessage(connection);
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

    public void archiveDelete(final int position, String status, final int userid) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.incoming_notification);
        this.message1ext = (TextView) dialog.findViewById(R.id.message1_ext);
        this.archivelyt = (LinearLayout) dialog.findViewById(R.id.archive_lyt);
        this.calcle = (TextView) dialog.findViewById(R.id.calcle);
        this.archive = (TextView) dialog.findViewById(R.id.archive);
        this.accptlyt = (LinearLayout) dialog.findViewById(R.id.accpt_lyt);
        this.block = (TextView) dialog.findViewById(R.id.block);
        this.decline = (TextView) dialog.findViewById(R.id.decline);
        this.accept = (TextView) dialog.findViewById(R.id.accept);
        calcle.setTypeface(fontface);
        archive.setTypeface(fontface);
        block.setTypeface(fontface);
        decline.setTypeface(fontface);
        accept.setTypeface(fontface);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.25);
        dialog.getWindow().setLayout(width, height);
        if (status.equals("pending")) {
            accptlyt.setVisibility(View.VISIBLE);
            archivelyt.setVisibility(View.GONE);
        } else {
            accptlyt.setVisibility(View.GONE);
            archivelyt.setVisibility(View.VISIBLE);
            message1ext.setText(getString(R.string.deletemessage) + " archive ?");
        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetxt.setVisibility(View.VISIBLE);
                dialog.dismiss();
                IncomingFragment.stringObjectHashMap.put("comment", "");
                IncomingFragment.stringObjectHashMap.put("notificatioAction", "accept");
                IncomingFragment.stringObjectHashMap.put("notificationID", userid);
                IncomingFragment.operationInterface.getAcceptID(userid, stringObjectHashMap);

            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetxt.setVisibility(View.VISIBLE);
                IncomingFragment.stringObjectHashMap.put("comment", "");
                IncomingFragment.stringObjectHashMap.put("notificatioAction", "decline");
                IncomingFragment.stringObjectHashMap.put("notificationID", userid);
                IncomingFragment.operationInterface.getDeleteId(userid, stringObjectHashMap);
                dialog.dismiss();
                inboundArrayList.remove(position);
            }
        });
        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.dismiss();
                Toast.makeText(getContext(), "coming soon", Toast.LENGTH_SHORT).show();
            }
        });
        calcle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatetxt.setVisibility(View.VISIBLE);
                IncomingFragment.stringObjectHashMap.put("comment", "");
                IncomingFragment.stringObjectHashMap.put("notificatioAction", "archive");
                IncomingFragment.stringObjectHashMap.put("notificationID", userid);
                operationInterface.getArchiveId(userid, stringObjectHashMap);
                dialog.dismiss();
                inboundArrayList.remove(position);
            }
        });
        dialog.show();

    }

}