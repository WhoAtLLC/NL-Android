package com.root.wishlist.util.globalValues;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.presentation.notification.OperationInterface;
import com.root.wishlist.R;
import com.root.wishlist.fragment.notification.IncomingFragment;
import com.root.wishlist.fragment.notification.OutgoingFragment;
import com.root.wishlist.interfaces.Refreshpage;

public class NotificationDialog {

    Context context;
    private TextView message1txt;
    private TextView yes;
    private TextView no;
    OperationInterface operationInterface;
    private TextView declinetitle;
    private android.widget.EditText declineusermessage;
    private TextView countertxt;
    private TextView of2txt;
    private TextView total2txt;
    private android.widget.LinearLayout decline;
    private TextView accepttitle;
    private EditText acceptusermessage;
    private LinearLayout accept;

    public NotificationDialog(Context context) {

        this.context = context;

    }

    public void archiveDelete(final int requestId, final String message) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.outgoing_notification);
        this.no = (TextView) dialog.findViewById(R.id.no);
        this.yes = (TextView) dialog.findViewById(R.id.yes);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        if (message.equals("accepted")) {
            message1txt.setText("Delete ?");
            message1txt.setTextColor(Color.RED);
        } else if (message.equals("pending")) {
            message1txt.setText("Archive ?");
        }
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.25);
        dialog.getWindow().setLayout(width, height);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.equals("accepted")) {
                    OutgoingFragment.stringObjectHashMap.put("comment", "");
                    OutgoingFragment.stringObjectHashMap.put("notificatioAction", "archive");
                    OutgoingFragment.stringObjectHashMap.put("notificationID", requestId);
                    OutgoingFragment.operationInterface.getAcceptID(requestId, OutgoingFragment.stringObjectHashMap);
                    dialog.dismiss();
                    Refreshpage refreshpage = (Refreshpage) context;
                    refreshpage.refreshpage();
                } else {
                    OutgoingFragment.stringObjectHashMap.put("comment","");
                    OutgoingFragment.stringObjectHashMap.put("notificatioAction", "decline");
                    OutgoingFragment.stringObjectHashMap.put("notificationID", requestId);
                    OutgoingFragment.operationInterface.getAcceptID(requestId, OutgoingFragment.stringObjectHashMap);
                    dialog.dismiss();
                    Refreshpage refreshpage = (Refreshpage) context;
                    refreshpage.refreshpage();
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

    public void inComing(String message) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.incoming_notification);
        this.no = (TextView) dialog.findViewById(R.id.no);
        this.yes = (TextView) dialog.findViewById(R.id.yes);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        if (message.equals("Delete")) {
            message1txt.setText("Delete ?");
            message1txt.setTextColor(Color.RED);

        }
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.80);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.70);
        dialog.getWindow().setLayout(width, height);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public void setOnAcceptClick(final int userid) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.accept_notification);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.60);
        dialog.getWindow().setLayout(width, height);
        this.accept = (LinearLayout) dialog.findViewById(R.id.accept);
        this.total2txt = (TextView) dialog.findViewById(R.id.total2_txt);
        this.of2txt = (TextView) dialog.findViewById(R.id.of2_txt);
        this.countertxt = (TextView) dialog.findViewById(R.id.counter_txt);
        this.acceptusermessage = (EditText) dialog.findViewById(R.id.accept_user_message);
        this.accepttitle = (TextView) dialog.findViewById(R.id.accept_title);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomingFragment.stringObjectHashMap.put("comment", acceptusermessage.getText().toString());
                IncomingFragment.stringObjectHashMap.put("notificatioAction", "accept");
                IncomingFragment.stringObjectHashMap.put("notificationID", userid);
                IncomingFragment.operationInterface.getAcceptID(userid, IncomingFragment.stringObjectHashMap);
                dialog.dismiss();
                Refreshpage refreshpage = (Refreshpage) context;
                refreshpage.refreshpage();
            }
        });

        dialog.show();
    }

    public void setOnDeclineClick(final int userid) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.decline_notification);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.60);
        dialog.getWindow().setLayout(width, height);
        this.decline = (LinearLayout) dialog.findViewById(R.id.decline);
        this.total2txt = (TextView) dialog.findViewById(R.id.total2_txt);
        this.of2txt = (TextView) dialog.findViewById(R.id.of2_txt);
        this.countertxt = (TextView) dialog.findViewById(R.id.counter_txt);
        this.declineusermessage = (EditText) dialog.findViewById(R.id.decline_user_message);
        this.declinetitle = (TextView) dialog.findViewById(R.id.decline_title);

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IncomingFragment.stringObjectHashMap.put("comment", declineusermessage.getText().toString());
                IncomingFragment.stringObjectHashMap.put("notificatioAction", "decline");
                IncomingFragment.stringObjectHashMap.put("notificationID", userid);
                IncomingFragment.operationInterface.getAcceptID(userid, IncomingFragment.stringObjectHashMap);
                dialog.dismiss();
                Refreshpage refreshpage = (Refreshpage) context;
                refreshpage.refreshpage();
            }
        });
        dialog.show();
    }
}
