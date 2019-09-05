package com.root.wishlist.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.activities.EmailVerificationActivity;
import com.root.wishlist.activities.MoreActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.presentation.registration.SelectNetworkPresentation;
import com.root.wishlist.presentation.registration.SelectNetworkPresentationInt;
import com.root.wishlist.view.SelectNetworkView;


public class OpenNetwork extends Fragment implements SelectNetworkView {


    private android.widget.TextView selecttxt;
    private TextView opennetworktitle;
    private TextView opennetworkmsg1;
    private TextView opennetworkmsg2;
    int flagvalue = 0;
    SelectNetworkPresentationInt selectNetworkPresentationInt;
    String message = "open";
    private boolean isClickable = true;
    SharedDatabase sharedDatabase;

    public OpenNetwork(int flagvalue, String message) {
        this.flagvalue = flagvalue;
        this.message = message;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedDatabase = new SharedDatabase(getContext());
        selectNetworkPresentationInt = new SelectNetworkPresentation(OpenNetwork.this, getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vaa = inflater.inflate(R.layout.fragment_open_network, container, false);
        this.opennetworkmsg2 = (TextView) vaa.findViewById(R.id.opennetwork_msg2);
        this.opennetworkmsg1 = (TextView) vaa.findViewById(R.id.opennetwork_msg1);
        this.opennetworktitle = (TextView) vaa.findViewById(R.id.opennetwork_title);
        this.selecttxt = (TextView) vaa.findViewById(R.id.select_txt);
        setFontFace();
        selecttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(flagvalue);
            }
        });
        return vaa;
    }

    private void setFontFace() {
        Typeface normalFont = Typeface.createFromAsset(getActivity().getAssets(), "Font/Gotham-Book.otf");
        opennetworkmsg1.setTypeface(normalFont);
        selecttxt.setTypeface(normalFont);
        Typeface boldFont = Typeface.createFromAsset(getActivity().getAssets(), "Font/Gotham-Medium.otf");
        opennetworktitle.setTypeface(boldFont);
        opennetworkmsg2.setTypeface(boldFont);
    }

    private void navigateTo(int number) {

        if (isClickable) {
            selectNetworkPresentationInt.setNetwork(message);
            sharedDatabase.setNetwork("open");
            isClickable = false;
        }
        if (number != 1) {
            getActivity().startActivity(new Intent(getActivity(), EmailVerificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            getActivity().finish();
            getActivity().overridePendingTransition(0, 0);
        } else {
            getActivity().startActivity(new Intent(getActivity(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            getActivity().finish();
            getActivity().overridePendingTransition(0, 0);
        }
    }

    @Override
    public void navigatToHome(String network) {
        Log.d("networlkprivate", network);
    }
}
