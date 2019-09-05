package com.root.wishlist.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.root.wishlist.R;


public class Leads extends Fragment implements View.OnClickListener {
    private android.support.v4.view.ViewPager viewpager;
    private android.widget.TextView mybusiness;
    private android.widget.TextView companyinterest;
    private android.widget.TextView totalselectedcompany;
    Fragment fragment=new MembersFragment();
    FragmentManager fragmentManager;
    private android.widget.FrameLayout frameleads;


    public Leads() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leads, container, false);
        this.totalselectedcompany = (TextView) view.findViewById(R.id.total_selected_company);
        this.companyinterest = (TextView) view.findViewById(R.id.company_interest);
        this.mybusiness = (TextView) view.findViewById(R.id.my_business);

        totalselectedcompany.setOnClickListener(this);
        companyinterest.setOnClickListener(this);
        mybusiness.setOnClickListener(this);
        mybusiness.setBackgroundColor(getResources().getColor(R.color.buttonColor));
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_leads, fragment).addToBackStack(null).commit();
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.company_interest:
                fragment = new CompaniesInterest();
                companyinterest.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                mybusiness.setBackgroundColor(getResources().getColor(R.color.appColor));
                totalselectedcompany.setBackgroundColor(getResources().getColor(R.color.appColor));
                break;
            case R.id.my_business:
                fragment = new MembersFragment();
                mybusiness.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                companyinterest.setBackgroundColor(getResources().getColor(R.color.appColor));
                totalselectedcompany.setBackgroundColor(getResources().getColor(R.color.appColor));
                break;

        }
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_leads, fragment).addToBackStack(null).commit();
    }

}
