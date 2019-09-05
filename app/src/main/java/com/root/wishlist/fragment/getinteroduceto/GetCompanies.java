package com.root.wishlist.fragment.getinteroduceto;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.root.wishlist.adapters.GetIntroCompaniesAdapter;
import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.pojo.wantto.CompaniesofInterest;
import com.root.wishlist.presentation.leads.IntroduceCompanyList;
import com.root.wishlist.presentation.leads.IntroduceCompanyPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.IntroduceCompanyView;

import java.util.ArrayList;
import java.util.List;


public class GetCompanies extends Fragment implements IntroduceCompanyView {

    private android.widget.TextView reasonText;
    IntroduceCompanyList introduceCompanyList;
    GetIntroCompaniesAdapter getIntroCompaniesAdapter;
    Bundle bundle;
    int pageNumber = 1;
    List<IntroCompanies> companies = new ArrayList<>();
    private android.support.v7.widget.RecyclerView getcompanies;
    LinearLayoutManager linearLayoutManager;
    boolean isLoading = true;

    public GetCompanies() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        introduceCompanyList = new IntroduceCompanyPresentation(getContext(), GetCompanies.this);
        introduceCompanyList.getIntroCompanies(bundle.getString("handle"), pageNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        this.getcompanies = (RecyclerView) view.findViewById(R.id.get_companies);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        getcompanies.setLayoutManager(linearLayoutManager);
        getcompanies.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getIntroCompaniesAdapter = new GetIntroCompaniesAdapter(companies, getContext());
        getcompanies.setAdapter(getIntroCompaniesAdapter);

        getcompanies.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                companies.add(null);
                getIntroCompaniesAdapter.notifyItemInserted(companies.size() - 1);
                companies.remove(companies.size() - 1);
                getIntroCompaniesAdapter.notifyItemRemoved(companies.size());

                if (dy > 0) {
                    int displayedposition = 5;
                    if ((displayedposition + linearLayoutManager.findLastVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {

                        if (isLoading) {
                            ++pageNumber;
                            introduceCompanyList.getIntroCompanies(bundle.getString("handle"), pageNumber);
                            isLoading = false;
                        }
                    }
                }

            }
        });
        return view;
    }


    @Override
    public void setCompanies(List<IntroCompanies> companies1) {
        companies.addAll(companies1);
        getIntroCompaniesAdapter.notifyDataSetChanged();
        getIntroCompaniesAdapter.notifyItemInserted(companies.size());

    }

    @Override
    public void getWantToCompany(List<CompaniesofInterest> companiesofInterests) {

    }

    @Override
    public void getPageNumber(int pNumber) {

    }

}
