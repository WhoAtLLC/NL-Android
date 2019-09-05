package com.root.wishlist.fragment.getinteroduceto;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.root.wishlist.adapters.MyintroCompaniesAdapter;
import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;
import com.root.wishlist.presentation.leads.IntroduceCompanyList;
import com.root.wishlist.presentation.leads.IntroduceCompanyPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.IntroduceCompanyView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class MyintroCompanies extends Fragment implements IntroduceCompanyView {


    IntroduceCompanyList introduceCompanyList;
    MyintroCompaniesAdapter myintroCompaniesAdapter;
    Bundle bundle;
    static int pageNumber = 1;
    List<IntroCompanies> companiesAll = new ArrayList<>();
    String handle;
    private RecyclerView companyList;
    LinearLayoutManager linearLayoutManager;
    private android.widget.TextView titile;
    boolean isLoading = true;

    public MyintroCompanies(String handle) {
        this.handle = handle;
    }

    @Override
    public void onAttach(Context context) {
        //bundle = getArguments();
        super.onAttach(context);
        //handle = bundle.getString("handle");
        introduceCompanyList = new IntroduceCompanyPresentation(getContext(), MyintroCompanies.this);
        introduceCompanyList.getIntroCompanies(handle, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myintro_companies, container, false);
        this.titile = (TextView) view.findViewById(R.id.titile);
        this.companyList = (RecyclerView) view.findViewById(R.id.companyList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        companyList.setLayoutManager(linearLayoutManager);
        companyList.setHasFixedSize(true);

        titile.setText("Select companies you can help " + handle);
        myintroCompaniesAdapter = new MyintroCompaniesAdapter(companiesAll, getContext());
        companyList.setAdapter(myintroCompaniesAdapter);
        companyList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                companiesAll.add(null);
                myintroCompaniesAdapter.notifyItemInserted(companiesAll.size() - 1);
                companiesAll.remove(companiesAll.size() - 1);
                myintroCompaniesAdapter.notifyItemRemoved(companiesAll.size());

                if (dy > 0) {
                    int displayedposition = 5;
                    if ((displayedposition + linearLayoutManager.findLastVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {


                        if (isLoading) {
                            ++pageNumber;
                            introduceCompanyList.getIntroCompanies(handle, pageNumber);
                            isLoading = false;
                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void setCompanies(List<IntroCompanies> companies) {
        companiesAll.addAll(companies);
        myintroCompaniesAdapter.notifyDataSetChanged();
        myintroCompaniesAdapter.notifyItemInserted(companiesAll.size());
        isLoading = true;
    }

    @Override
    public void getWantToCompany(List<CompaniesofInterest> companiesofInterests) {

    }

    @Override
    public void getPageNumber(int pnumber) {
//        pageNumber = pnumber;
    }



}
