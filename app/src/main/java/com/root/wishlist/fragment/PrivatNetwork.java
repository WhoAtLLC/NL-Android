package com.root.wishlist.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class PrivatNetwork extends Fragment implements SelectNetworkView {


    private android.widget.TextView selecttxt;
    private TextView privatenetworktitle;
    private TextView privatenetworkmsg1;
    private TextView privatenetworkmsg2;
    int flagvalue = 0;
    private String message = "private";
    private boolean isClickable = true;
    SelectNetworkPresentationInt selectNetworkPresentationInt;
    SharedDatabase sharedDatabase;

    public PrivatNetwork(int flagvalue, String message) {
        this.flagvalue = flagvalue;
        this.message = message;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedDatabase = new SharedDatabase(getContext());
        selectNetworkPresentationInt = new SelectNetworkPresentation(PrivatNetwork.this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_privat_network, container, false);
        this.privatenetworkmsg2 = (TextView) view.findViewById(R.id.privatenetwork_msg2);
        this.privatenetworkmsg1 = (TextView) view.findViewById(R.id.privatenetwork_msg1);
        this.privatenetworktitle = (TextView) view.findViewById(R.id.privatenetwork_title);
        this.selecttxt = (TextView) view.findViewById(R.id.select_txt);
        setFontFace();
        selecttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateTo(flagvalue);
            }
        });
        return view;
    }

    private void setFontFace() {
        Typeface normalFont = Typeface.createFromAsset(getActivity().getAssets(), "Font/Gotham-Book.otf");
        privatenetworkmsg1.setTypeface(normalFont);
        selecttxt.setTypeface(normalFont);
        Typeface boldFont = Typeface.createFromAsset(getActivity().getAssets(), "Font/Gotham-Medium.otf");
        privatenetworktitle.setTypeface(boldFont);
        privatenetworkmsg2.setTypeface(boldFont);

    }

    private void navigateTo(int number) {
        if (isClickable) {
            selectNetworkPresentationInt.setNetwork(message);
            sharedDatabase.setNetwork("private");
            isClickable = false;
        }
        if (number != 1) {
            getActivity().startActivity(new Intent(getActivity(), EmailVerificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            getActivity().finish();
            getActivity().overridePendingTransition(0, 0);
        } else {
            getActivity().startActivity(new Intent(getActivity(), MoreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            getActivity().overridePendingTransition(0, 0);
        }
    }

    @Override
    public void navigatToHome(String network) {

    }
}
