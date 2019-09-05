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
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.activities.notificationmodule.Wanttomeet;
import com.root.wishlist.adapters.ArchiveAdoptor;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
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

public class ArchiveFragment extends Fragment implements NotificationIOAView, operationView {


    ArchiveAdoptor archiveAdoptor;
    ArrayList<Archived> archivedArrayList = new ArrayList<>();
    Archived archived;
    NotificationIOAInterface notificationIOAInterface;
    WishlistProgressDialog wishlistProgressDialog;
    private android.support.v4.widget.SwipeRefreshLayout swipelist;
    //for dialog
    private TextView message1txt;
    private TextView yes;
    private TextView no;
    OperationInterface operationInterface;
    private RecyclerView archiverequestlist;
    LinearLayoutManager linearLayoutManager;
    Typeface fontface;
    private TextView requestertxt;
    private TextView prospectortxt;
    private TextView statustxt;
    int listPosition = 0;
    private TextView updatetxt;

    public ArchiveFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        wishlistProgressDialog = new WishlistProgressDialog(getContext());
        notificationIOAInterface = new NotificationIOAPresentation(activity, ArchiveFragment.this);
        wishlistProgressDialog.dialogShow();
        notificationIOAInterface.getNotification();
        operationInterface = new OperationPresentation(activity, ArchiveFragment.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.archive_layout, container, false);
        this.updatetxt = (TextView) view.findViewById(R.id.update_txt);
        fontface = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        this.statustxt = (TextView) view.findViewById(R.id.status_txt);
        this.prospectortxt = (TextView) view.findViewById(R.id.prospector_txt);
        this.requestertxt = (TextView) view.findViewById(R.id.requester_txt);
        this.archiverequestlist = (RecyclerView) view.findViewById(R.id.archive_request_list);
        this.swipelist = (SwipeRefreshLayout) view.findViewById(R.id.swipe_list);
        statustxt.setTypeface(fontface);
        prospectortxt.setTypeface(fontface);
        requestertxt.setTypeface(fontface);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        archiverequestlist.setLayoutManager(linearLayoutManager);
        archiverequestlist.setHasFixedSize(true);
        swipelist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                notificationIOAInterface.getNotification();
                swipelist.setRefreshing(false);
            }
        });
        archiverequestlist.addOnItemTouchListener(new ArchiveAdoptor(getActivity(), archiverequestlist, new ArchiveAdoptor.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                listPosition = position;
                //Toast.makeText(getContext(),""+ archivedArrayList.get(position).getRequestID(), Toast.LENGTH_SHORT).show();
                archiveDelete("Delete", archivedArrayList.get(position).getRequestID());
            }
        }));
        archiverequestlist.addOnItemTouchListener(new OnclickListenerRecycle(getContext(), archiverequestlist, new OnclickListenerRecycle.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), Wanttomeet.class);
                intent.putExtra("requestname", archivedArrayList.get(position).getContactusername());
                intent.putExtra("prospectname", archivedArrayList.get(position).getProspectname());
                intent.putExtra("requestID", archivedArrayList.get(position).getRequestID());
                intent.putExtra("status", archivedArrayList.get(position).getStatus());
                intent.putExtra("handler", archivedArrayList.get(position).getContactusername());
                intent.putExtra("code", "Ar");
                intent.putExtra("flag", 2);
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

    }

    @Override
    public void getOutBound(List<Outbound> outboundList) {

    }

    @Override
    public void getArchived(List<Archived> archivedList) {
        wishlistProgressDialog.dismissDialog();
        archivedArrayList = new ArrayList<>();
        for (int i = 0; i < archivedList.size(); i++) {
            try {
                int requestCode = archivedList.get(i).getRequestID();
                String contactusername = archivedList.get(i).getContactusername();
                String prospectname = archivedList.get(i).getProspectname();
                String status = archivedList.get(i).getStatus();
                boolean highlight = archivedList.get(i).getHighlight();
                archived = new Archived(requestCode, status, contactusername, prospectname, highlight, archivedList.get(i).getContactowner());
                archivedArrayList.add(archived);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        archiveAdoptor = new ArchiveAdoptor(getActivity(), archivedArrayList);
        archiverequestlist.setAdapter(archiveAdoptor);
        updatetxt.setVisibility(View.GONE);
    }

    @Override
    public void getarchived(boolean archive) {

    }

    @Override
    public void getdecline(boolean decline) {

        Log.d("delete", String.valueOf(decline));
        notificationIOAInterface.getNotification();
    }

    @Override
    public void getStatus(String status) {

    }

    @Override
    public void newtworkError(String connection) {
        wishlistProgressDialog.dismissDialog();
        //new AlertDialogBox(getContext()).networkMessage(connection);
    }

    public void archiveDelete(String message, final int userID) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.outgoing_notification);

        this.no = (TextView) dialog.findViewById(R.id.no);
        this.yes = (TextView) dialog.findViewById(R.id.yes);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        no.setTypeface(fontface);
        yes.setTypeface(fontface);
        message1txt.setTypeface(fontface);
        if (message.equals("Delete")) {
            message1txt.setText(" delete ?");
            //message1txt.setTextColor(Color.RED);

        }
        final HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.25);
        dialog.getWindow().setLayout(width, height);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                updatetxt.setVisibility(View.VISIBLE);
                Log.d("id", String.valueOf(userID));
                stringObjectHashMap.put("comment", "");
                stringObjectHashMap.put("notificatioAction", "decline");
                stringObjectHashMap.put("notificationID", userID);
                operationInterface.getDeleteId(userID, stringObjectHashMap);
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
