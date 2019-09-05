package com.root.wishlist.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.root.wishlist.adapters.TotalSelectedCompanyAdapter;
import com.root.wishlist.database.CompanyListDatabase;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.R;
import com.root.wishlist.interfaces.Refreshpage;
import com.root.wishlist.interfaces.UpdateNumberOfItems;

import java.util.ArrayList;
import java.util.List;


public class TotalSelectedCompanies extends Fragment implements AdapterView.OnItemClickListener, Refreshpage {

    private android.widget.ListView companylist;
    TotalSelectedCompanyAdapter companyOfInterestAdapter;
    List<Result> companyDetails = new ArrayList<>();
    CompanyListDatabase companyListDatabase;
    private ArrayList<Integer> totalno = new ArrayList<>();
    SharedDatabase sharedDatabase;
    public static int more_value = 0;
    private android.support.v4.widget.SwipeRefreshLayout swiptorefresh;
    public static int refreashList = 0;

    public TotalSelectedCompanies() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //companyDetails = companyListDatabase.getCounterCompany();
        sharedDatabase = new SharedDatabase(context);
        companyListDatabase = new CompanyListDatabase(getActivity());
        companyDetails = companyListDatabase.getCounterCompany();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        companyListDatabase = new CompanyListDatabase(getActivity());
        companyDetails = companyListDatabase.getCounterCompany();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_total_selected_companies, container, false);
        this.swiptorefresh = (SwipeRefreshLayout) view.findViewById(R.id.swip_to_refresh);
        this.companylist = (ListView) view.findViewById(R.id.company_list);

        swiptorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                companyDetails = companyListDatabase.getCounterCompany();
                setListData();
                swiptorefresh.setRefreshing(false);
            }
        });

        setListData();
        companylist.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        totalno.add(i);
        companyListDatabase.insertCompanyList(companyDetails.get(i).getId(), companyDetails.get(i).getTitle(), companyDetails.get(i).getProfileImageUrl());
        companyListDatabase.deleteCounterCompanyListItem(companyDetails.get(i).getId());
        companyDetails.remove(i);
        UpdateNumberOfItems updateNumberOfItems = (UpdateNumberOfItems) getContext();
        updateNumberOfItems.updateCounter();
        companyOfInterestAdapter.notifyDataSetChanged();
        refreashList = 1;
    }


    @Override
    public void onResume() {
        super.onResume();
        setListData();
    }

    void setListData() {
        companyDetails = companyListDatabase.getCounterCompany();
        companyOfInterestAdapter = new TotalSelectedCompanyAdapter(getActivity(), companyDetails);
        companylist.setAdapter(companyOfInterestAdapter);
        companyOfInterestAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshpage() {
        setListData();
    }


}
