package com.root.wishlist.fragment.getinteroduceto;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.pojo.wantto.Comment;
import com.root.wishlist.presentation.leads.TheirInterestInterface;
import com.root.wishlist.presentation.leads.TheirIntrestPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.TheirInterestView;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.List;


public class TheirInterest extends Fragment implements TheirInterestView {

    TheirInterestInterface theirInterestInterface;
    private android.widget.TextView biotxt;
    private TextView requesttobtn;
    private TextView handlername;
    private TextView whattxt;
    private TextView mybusinessdiscuss;
    private TextView businessadditional;
    String handle;
    Bundle bundle;
    View view;
    Typeface normalFont;
    WishlistProgressDialog wishlistProgressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        wishlistProgressDialog = new WishlistProgressDialog(context);
        wishlistProgressDialog.dialogShow();
        theirInterestInterface = new TheirIntrestPresentation(TheirInterest.this, getContext());
    }

    public TheirInterest() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_their_interest, container, false);
        this.businessadditional = (TextView) view.findViewById(R.id.business_additional);
        this.mybusinessdiscuss = (TextView) view.findViewById(R.id.my_business_discuss);
        this.whattxt = (TextView) view.findViewById(R.id.what_txt);
        this.handlername = (TextView) view.findViewById(R.id.handler_name);
        this.biotxt = (TextView) view.findViewById(R.id.bio);
        bundle = getArguments();
        handle = bundle.getString("handle");
        // theirInterestInterface.setTheirInterest("ebird");
        theirInterestInterface.setTheirInterest(handle);
        normalFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        businessadditional.setTypeface(normalFont);
        mybusinessdiscuss.setTypeface(normalFont);
        whattxt.setTypeface(normalFont);
        handlername.setTypeface(normalFont);
        biotxt.setTypeface(normalFont);
        return view;
    }

    @Override
    public void message(String handle, String shortBio, String bio, String image, String businessDiscussion, String businessAdditional) {
        wishlistProgressDialog.dismissDialog();
        handlername.setText(handle + "'s Bio");
        whattxt.setText("What " + handle + "is looking for");
        mybusinessdiscuss.setText(businessDiscussion);
        businessadditional.setText(businessAdditional);
        biotxt.setText(bio);
        //Log.d("message", handle);
    }

    @Override
    public void businessInfo(String status, String whyIntroReason, String howIntroReason, List<Comment> comments) {

    }

    @Override
    public void networkMessage(String connection) {
        wishlistProgressDialog.dismissDialog();
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
        //new AlertDialogBox(getContext()).networkMessage(connection);
    }


}
