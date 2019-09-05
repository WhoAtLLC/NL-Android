package com.root.wishlist.util.globalValues;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.root.wishlist.R;
import com.root.wishlist.activities.ChangeServerActivity;
import com.root.wishlist.activities.LandingActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.searvices.UploadContactService;


public class AlertDialogBox {

    Context context;
    android.widget.TextView boxtitle;
    android.widget.ImageView crossimage;
    TextView title;
    TextView headermessage;
    TextView dontallow;
    TextView ok;
    TextView largemessage;
    TextView continuetxt;
    TextView idontwant;
    TextView message1txt;
    TextView alerttitle;
    TextView appInfoDetailsTV;
    EditText enterTargetServerEt;
    String returnActionAppInfo;
    TextView okBtHostUrl, cancelBtHostUrl;
    private SharedDatabase sharedDatabase;
    private String whoseCalling;


    public AlertDialogBox(Context context) {
        this.context = context;
        sharedDatabase = new SharedDatabase(context);
    }


    public void uploadContacts(String whoseCalling1) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.uploadcontact_dialog);
        dialog.setCancelable(false);

        this.idontwant = (TextView) dialog.findViewById(R.id.idont_want);
        this.continuetxt = (TextView) dialog.findViewById(R.id.continue_txt);
        this.largemessage = (TextView) dialog.findViewById(R.id.large_message);
        this.ok = (TextView) dialog.findViewById(R.id.ok);
        this.dontallow = (TextView) dialog.findViewById(R.id.dont_allow);
        this.headermessage = (TextView) dialog.findViewById(R.id.header_message);
        this.title = (TextView) dialog.findViewById(R.id.title);

        whoseCalling=whoseCalling1;
        Typeface boldFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Medium.otf");
        title.setTypeface(boldFont);
        ok.setTypeface(boldFont);
        dontallow.setTypeface(boldFont);
        largemessage.setTypeface(boldFont);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        headermessage.setTypeface(typeface);
        continuetxt.setTypeface(typeface);
        idontwant.setTypeface(typeface);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(context, YourBusinessInterest.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);*/
                //Toast.makeText(context, "fasdad", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        continuetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* wishlistProgressDialog = new WishlistProgressDialog(context);
                wishlistProgressDialog.dialogShow();*/
                new UploadContactService(context, whoseCalling);
                //new ContactFetchSearvice(context).execute();
                //context.startActivity(new Intent(context, YourBusinessInterest.class));
                dialog.dismiss();
//                wishlistProgressDialog.dismissDialog();
            }
        });
        idontwant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LandingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
        dialog.show();
    }

    public void validationdialog(String message, final int number) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.validation_dialog);
        dialog.setCancelable(false);
        this.alerttitle = (TextView) dialog.findViewById(R.id.alert_title);
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.30);
        dialog.getWindow().setLayout(width, height);
        this.ok = (TextView) dialog.findViewById(R.id.ok);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        if (number == 2) {
            alerttitle.setVisibility(View.VISIBLE);
        }
        message1txt.setText(message);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                FocusableValue focusableValue = (FocusableValue) context;
                focusableValue.focusChecked(number);
            }
        });
        dialog.show();


    }


    public interface FocusableValue {
        void focusChecked(int focus);
    }

    public void networkMessage(String message) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.validation_dialog);
        dialog.setCancelable(false);
        this.alerttitle = (TextView) dialog.findViewById(R.id.alert_title);
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.30);
        dialog.getWindow().setLayout(width, height);
        this.ok = (TextView) dialog.findViewById(R.id.ok);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);

        alerttitle.setVisibility(View.VISIBLE);
        message1txt.setText(message);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                /*ReloadActivity focusableValue = (ReloadActivity) context;
                focusableValue.reload();*/
            }
        });
        dialog.show();


    }

    public interface ReloadActivity {
        void reload();
    }

    public void forgetPassword(String message, final int number) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.validation_dialog);
        dialog.setCancelable(false);
        this.alerttitle = (TextView) dialog.findViewById(R.id.alert_title);
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.85);
        int height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.25);
        dialog.getWindow().setLayout(width, height);
        this.ok = (TextView) dialog.findViewById(R.id.ok);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        if (number == 1) {
            alerttitle.setVisibility(View.VISIBLE);
        }
        message1txt.setText(message);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }

    public String appInfoDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.app_info_dialog);
        dialog.setCancelable(false);

        this.okBtHostUrl = (TextView) dialog.findViewById(R.id.okBtHostUrl);
        this.cancelBtHostUrl = (TextView) dialog.findViewById(R.id.cancelBtHostUrl);
        this.enterTargetServerEt = (EditText) dialog.findViewById(R.id.enterTargetServerEt);



        okBtHostUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterTargetServerEt.getText().toString().length()>0)
                {
                    sharedDatabase.setTargetServer(enterTargetServerEt.getText().toString());
                    returnActionAppInfo = enterTargetServerEt.getText().toString();

                }

                else
                {
                   /* if(sharedDatabase.getTargetServer().equals(""))
                        sharedDatabase.setTargetServer("http://wishlist.whoat.net/");
                    else
                        sharedDatabase.setTargetServer(sharedDatabase.getTargetServer());*/
                   returnActionAppInfo = "";
                   dialog.dismiss();
                }


                dialog.dismiss();
                ((ChangeServerActivity) context).getCallBackDialog(returnActionAppInfo);

            }
        });
        cancelBtHostUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnActionAppInfo = "";
                ((ChangeServerActivity) context).getCallBackDialog(returnActionAppInfo);
                dialog.dismiss();

            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);

        return returnActionAppInfo;


    }



}
