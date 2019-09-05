package com.root.wishlist.fragment.notification;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.pojo.wantto.Comment;
import com.root.wishlist.presentation.leads.TheirInterestInterface;
import com.root.wishlist.presentation.leads.TheirIntrestPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.TheirInterestView;

import java.util.List;

public class NotificationBusiness extends Fragment implements TheirInterestView {

    TheirInterestInterface theirInterestInterface;
    private android.widget.TextView biotxt;
    private TextView requesttobtn;
    private TextView handlername;
    private TextView whattxt;
    private TextView mybusinessdiscuss;
    private TextView businessadditional;
    View view;
    int requestCode;
    private String requestname, prospectname, code;
    private TextView bio;
    private TextView comment;
    private android.widget.LinearLayout commentlyt;
    private android.widget.LinearLayout commentDeclinelyt;
    Typeface fontFace;
    WishlistProgressDialog wishlistProgressDialog;
    public NotificationBusiness(int requestCode, String requestname, String prospectname, String code) {
        this.requestCode = requestCode;
        this.requestname = requestname;
        this.prospectname = prospectname;
        this.code = code;
        Log.d("requestCode", String.valueOf(requestCode));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        wishlistProgressDialog=new WishlistProgressDialog(context);
        theirInterestInterface = new TheirIntrestPresentation(NotificationBusiness.this, getContext());
        wishlistProgressDialog.dialogShow();
        theirInterestInterface.wantToBusiness(requestCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notification_business, container, false);

        this.commentlyt = (LinearLayout) view.findViewById(R.id.comment_lyt);
        this.commentDeclinelyt = (LinearLayout) view.findViewById(R.id.comment_decline_lyt);
        this.comment = (TextView) view.findViewById(R.id.comment);
        this.businessadditional = (TextView) view.findViewById(R.id.business_additional);
        this.mybusinessdiscuss = (TextView) view.findViewById(R.id.my_business_discuss);
        this.whattxt = (TextView) view.findViewById(R.id.what_txt);
        this.handlername = (TextView) view.findViewById(R.id.handler_name);
        this.biotxt = (TextView) view.findViewById(R.id.bio);
        theirInterestInterface.wantToBusiness(requestCode);
        setFontface();
        if (code.equals("In")) {
            whattxt.setText("What they are looking for");
            businessadditional.setText("How they can help you");
            handlername.setText("");
        } else {
            if (prospectname.contains(" ")) {
                prospectname=prospectname.substring(0,prospectname.lastIndexOf(" "));
                whattxt.setText("Reminder of why you want the intro to " + prospectname);
            } else
                whattxt.setText("Reminder of why you want the intro to " + prospectname);
            handlername.setText(prospectname);
            businessadditional.setText("Your comments on how you offered to help " + requestname + " in return.");
        }
        return view;
    }

    @Override
    public void message(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {

    }

    @Override
    public void businessInfo(String status, String whyIntroReason, String howIntroReason, List<Comment> comments) {
       wishlistProgressDialog.dismissDialog();
        mybusinessdiscuss.setText(whyIntroReason);
        biotxt.setText(howIntroReason);
        Log.d("list", String.valueOf(comments));
        Log.d("listsize", String.valueOf(comments.size()));
        if (!comments.isEmpty()) {
            if (status.equals("declined")) {
                if (!comments.get(0).getComment().equals("")) {
                    commentlyt.setVisibility(View.GONE);
                    commentDeclinelyt.setVisibility(View.VISIBLE);
                    // commentlyt.setBackgroundColor(Color.parseColor("#d72110"));
                    comment.setText(comments.get(0).getComment());
                }

            }
            if (status.equals("accepted")) {
                if (!comments.get(0).getComment().equals("")) {
                    commentlyt.setVisibility(View.VISIBLE);
                    commentDeclinelyt.setVisibility(View.GONE);
                    // commentlyt.setBackgroundColor(Color.parseColor("#25CB3E"));
                    comment.setText(comments.get(0).getComment());
                }
            }
            Log.d("comments", comments.get(0).getComment());
        }
    }

    @Override
    public void networkMessage(String connection) {
        wishlistProgressDialog.dismissDialog();
        new AlertDialogBox(getContext()).networkMessage(connection);
    }

    private void setFontface() {
        fontFace = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        comment.setTypeface(fontFace);
        biotxt.setTypeface(fontFace);
        handlername.setTypeface(fontFace);
        whattxt.setTypeface(fontFace);
        mybusinessdiscuss.setTypeface(fontFace);
        businessadditional.setTypeface(fontFace);
    }


}

