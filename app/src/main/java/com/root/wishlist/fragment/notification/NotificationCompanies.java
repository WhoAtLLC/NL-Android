package com.root.wishlist.fragment.notification;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;
import com.root.wishlist.presentation.leads.IntroduceCompanyList;
import com.root.wishlist.presentation.leads.IntroduceCompanyPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.IntroduceCompanyView;

import java.util.ArrayList;
import java.util.List;

public class NotificationCompanies extends Fragment implements IntroduceCompanyView {


    private RecyclerView getcompanies;
    private android.widget.TextView reasonText;
    IntroduceCompanyList introduceCompanyList;
    NotificationWantToCompanyAdapter getIntroCompaniesAdapter;
    int pageNumber = 1;
    int requestCode;
    LinearLayoutManager linearLayoutManager;
    List<CompaniesofInterest> companiesofInterestsNo = new ArrayList<>();
    private boolean isLoading = true;

    public NotificationCompanies(int requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        introduceCompanyList = new IntroduceCompanyPresentation(getContext(), NotificationCompanies.this);
        introduceCompanyList.wantToCompany(requestCode, pageNumber);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_companies, container, false);
        this.getcompanies = (RecyclerView) view.findViewById(R.id.get_companies);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getcompanies.setHasFixedSize(true);
        getcompanies.setLayoutManager(linearLayoutManager);
        getIntroCompaniesAdapter = new NotificationWantToCompanyAdapter(companiesofInterestsNo, getContext());
        getcompanies.setAdapter(getIntroCompaniesAdapter);
        getcompanies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getIntroCompaniesAdapter.notifyItemRemoved(companiesofInterestsNo.size());

                if (dy > 0) {
                    int displayedposition = 5;
                    if ((displayedposition + linearLayoutManager.findLastVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {


                        if (isLoading) {
                            ++pageNumber;
                            introduceCompanyList.wantToCompany(requestCode, pageNumber);
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

    }

    @Override
    public void getWantToCompany(List<CompaniesofInterest> companiesofInterests) {
        companiesofInterestsNo.addAll(companiesofInterests);
        getIntroCompaniesAdapter.notifyDataSetChanged();
        getIntroCompaniesAdapter.notifyItemInserted(companiesofInterestsNo.size());
        isLoading = true;
    }

    @Override
    public void getPageNumber(int pnumber) {
        // pageNumber = pnumber;
        pageNumber++;
    }


}
