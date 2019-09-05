package com.root.wishlist.util.globalValues.coachmark;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.database.SharedDatabase;

public class YourConnectionCoachmark {

    SharedDatabase sharedDatabase;
    Context context;


    public YourConnectionCoachmark(Context context) {
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
    }

    public void validationdialog(final String page, final boolean value) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.youcoachmark);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LinearLayout layt = (LinearLayout) dialog.findViewById(R.id.layt);
        final LinearLayout backimeg = (LinearLayout) dialog.findViewById(R.id.back_image);
        ImageView cancle = (ImageView) dialog.findViewById(R.id.cancle);
        layt.getBackground().setAlpha(10);
        switch (page) {
            case "mybusiness":
                backimeg.setBackgroundResource(R.drawable.mybusiness1);
                // sharedDatabase.myBusiness(value);
                break;
            case "companylist":
                backimeg.setBackgroundResource(R.drawable.companylist2);
                //sharedDatabase.companylist(value);
                break;
            case "totalCompanylist":
                backimeg.setBackgroundResource(R.drawable.companylist2);
                //sharedDatabase.totalCompanylist(value);
                break;
            case "yourconnectionat":
                //sharedDatabase.yourconnectionat(value);
                break;
            case "notification_page":
                backimeg.setBackgroundResource(R.drawable.nf);
                break;
            case "myinterorequest":
                backimeg.setBackgroundResource(R.drawable.my_intro_request);
                break;
            case "leadscompany":
                backimeg.setBackgroundResource(R.drawable.leads_company);
                break;
        }
        dialog.setCancelable(false);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (page) {
                    case "mybusiness":
                        sharedDatabase.myBusiness(value);
                        break;
                    case "companylist":
                        sharedDatabase.setCompanyCoach(value);
                        break;
                    case "totalCompanylist":
                        sharedDatabase.totalCompanylist(value);
                        break;
                    case "yourconnectionat":
                        sharedDatabase.setYourConnectionAt(value);
                        break;
                    case "notification_page":
                        sharedDatabase.setNotification_page(value);
                    case "myinterorequest":
                        sharedDatabase.setMyintro(value);
                    case "leadscompany":
                        sharedDatabase.setLeadsCompany(value);
                        break;
                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void leadsCoachmark(final String page, final boolean value, final int width) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.youcoachmark);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LinearLayout layt = (LinearLayout) dialog.findViewById(R.id.layt);
        final LinearLayout backimeg = (LinearLayout) dialog.findViewById(R.id.back_image);
        ImageView cancle = (ImageView) dialog.findViewById(R.id.cancle);
        layt.getBackground().setAlpha(10);
        switch (page) {
            case "leadscompany":
                backimeg.setBackgroundResource(R.drawable.leads_company);
                break;
        }
        backimeg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {


                   getScreen(event.getX(), event.getY(), width);
                   // System.out.print("width" + width);
                    if (width == 1440) {
                        System.out.println("getx=" + event.getX() + "gety==" + event.getY());
                    }/*
                    if (width == 1080) {
                        getScreen(event.getX(), event.getY(), width);
                        // System.out.println("getx=" + event.getX() + "gety==" + event.getY());
                    }*/

                }
                return true;
            }
        });
        dialog.setCancelable(false);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (page) {
                    case "leadscompany":
                        sharedDatabase.setLeadsCompany(value);
                        break;
                }
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void getScreen(float x, float y, int width) {

        if (width == 1080) {
            if ((x >= 630 && x <= 1002) && (y >= 1100 && y <= 1220)) {
                getDescription("Connections", context.getString(R.string.connection_description), R.drawable.connection);
                //System.out.println("your connection strength" + width);
            }
            if ((x >= 116 && x <= 320) && (y >= 702 && y <= 800)) {
                getDescription("Companies", context.getString(R.string.leads_description), R.drawable.companies);

            }
            if ((x >= 233 && x <= 416) && (y >= 510 && y <= 564)) {
                getDescription("Company List", context.getString(R.string.company_description), R.drawable.icon);

            }
            if ((x >= 424 && x <= 550) && (y >= 236 && y <= 330)) {
                getDescription("Notifications", context.getString(R.string.notification_description), R.drawable.notification);

            }
            if ((x >= 770 && x <= 870) && (y >= 433 && y <= 500)) {
                getDescription("My Account", context.getString(R.string.myaccount_description), R.drawable.account);

            }

            if ((x >= 900 && x <= 1080) && (y >= 286 && y <= 400)) {
                getDescription("More", context.getString(R.string.more_description), R.drawable.more);

            }


        }
        if (width == 720) {
            if ((x >= 440 && x <= 620) && (y >= 751 && y <= 800)) {
                getDescription("Connections", context.getString(R.string.connection_description), R.drawable.connection);
                //System.out.println("your connection strength" + width);
            }
            if ((x >= 116 && x <= 182) && (y >= 491 && y <= 550)) {
                getDescription("Companies", context.getString(R.string.leads_description), R.drawable.companies);
                // System.out.println("leads list of company" + width);
            }
            if ((x >= 170 && x <= 300) && (y >= 370 && y <= 450)) {
                getDescription("Company List", context.getString(R.string.company_description), R.drawable.icon);
                //System.out.println("companies you get an intro" + width);
            }
            if ((x >= 305 && x <= 355) && (y >= 168 && y <= 220)) {
                getDescription("Notifications", context.getString(R.string.notification_description), R.drawable.notification);
                //System.out.println("notifications" + width);
            }
            if ((x >= 524 && x <= 547) && (y >= 310 && y <= 356)) {
                getDescription("My Account", context.getString(R.string.myaccount_description), R.drawable.account);
                // System.out.println("myaccount" + width);
            }

            if ((x >= 631 && x <= 659) && (y >= 190 && y <= 250)) {
                getDescription("More", context.getString(R.string.more_description), R.drawable.more);
                //System.out.println("more" + width);
            }
        }
        if (width == 1440) {
            if ((x >= 790 && x <= 1282) && (y >= 1440 && y <= 1679)) {
                getDescription("Connections", context.getString(R.string.connection_description), R.drawable.connection);
                //System.out.println("your connection strength" + width);
            }
            if ((x >= 168 && x <= 442) && (y >= 945 && y <= 1075)) {
                getDescription("Companies", context.getString(R.string.leads_description), R.drawable.companies);
                // System.out.println("leads list of company" + width);
            }
            if ((x >= 240 && x <= 754) && (y >= 664 && y <= 801)) {
                getDescription("Company List", context.getString(R.string.company_description), R.drawable.icon);
                //System.out.println("companies you get an intro" + width);
            }
            if ((x >= 513 && x <= 827) && (y >= 331 && y <= 431)) {
                getDescription("Notifications", context.getString(R.string.notification_description), R.drawable.notification);
                //System.out.println("notifications" + width);
            }
            if ((x >= 982 && x <= 1226) && (y >= 630 && y <= 708)) {
                getDescription("My Account", context.getString(R.string.myaccount_description), R.drawable.account);
                // System.out.println("myaccount" + width);
            }

            if ((x >= 1167 && x <= 1403) && (y >= 390 && y <= 476)) {
                getDescription("More", context.getString(R.string.more_description), R.drawable.more);
                //System.out.println("more" + width);
            }
        }
    }

    public void getDescription(String connections, String details, int more) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.coach_description);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.60);
        dialog.getWindow().setLayout(width, height);
        TextView message = (TextView) dialog.findViewById(R.id.message);
        TextView title = (TextView) dialog.findViewById(R.id.title);
        ImageView companyimage = (ImageView) dialog.findViewById(R.id.company_image);
        ImageView cancle = (ImageView) dialog.findViewById(R.id.cancle);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        companyimage.setImageResource(more);
        title.setText(connections);
        message.setText(details);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
