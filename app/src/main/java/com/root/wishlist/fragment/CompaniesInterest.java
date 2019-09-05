package com.root.wishlist.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.root.wishlist.adapters.CompanyOfInterestAdapter;
import com.root.wishlist.database.CompanyListDatabase;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.util.globalValues.coachmark.YourConnectionCoachmark;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.pojo.mywishlist.Result;
import com.root.wishlist.presentation.registration.CompanyOfInterestInterface;
import com.root.wishlist.presentation.registration.CompanyOfInterestPresentation;
import com.root.wishlist.R;
import com.root.wishlist.util.OnclickListenerRecycle;
import com.root.wishlist.view.CompanyofInterestView;
import com.root.wishlist.interfaces.Refreshpage;
import com.root.wishlist.interfaces.UpdateNumberOfItems;

import java.util.ArrayList;
import java.util.List;

import me.wangyuwei.loadingview.LoadingView;


public class CompaniesInterest extends Fragment implements CompanyofInterestView, Refreshpage {

    public static List<Result> companyName = new ArrayList<>();
    CompanyOfInterestAdapter companyOfInterestAdapter;
    private RecyclerView companyList;
    ArrayList<Integer> totalno = new ArrayList<>();
    private int pageNumber = 1;
    CompanyOfInterestInterface companyOfInterestInterface;
    WishlistProgressDialog wishlistProgressDialog;
    CompanyListDatabase companyListDatabase;
    public static List<Result> totalSelectedComapny = new ArrayList<>();
    private android.support.v4.widget.SwipeRefreshLayout switchtorefresh;
    private TextView updatetxt;
    int vi = 1;
    SharedDatabase sharedDatabase;
    private boolean isLoading = true;
    private me.wangyuwei.loadingview.LoadingView searchProgressBar;
    LinearLayoutManager linearLayoutManager;
    int displayedposition = 0;
    int visibleThreshold = 5;

    public CompaniesInterest() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedDatabase = new SharedDatabase(getContext());
        wishlistProgressDialog = new WishlistProgressDialog(getActivity());
        companyOfInterestInterface = new CompanyOfInterestPresentation(CompaniesInterest.this, getContext());
        wishlistProgressDialog.dialogShow();
        if (!sharedDatabase.getMybusinessCoach()) {
            new YourConnectionCoachmark(getActivity()).validationdialog("mybusiness", true);
        }
        companyOfInterestInterface.loadComapnyList(pageNumber);
        companyListDatabase = new CompanyListDatabase(getActivity());
        totalSelectedComapny = companyListDatabase.getCounterCompany();
        new DownloadFilesTask().execute(" ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_companies_interest, container, false);
        this.searchProgressBar = (LoadingView) view.findViewById(R.id.searchProgressBar);
        this.updatetxt = (TextView) view.findViewById(R.id.update_txt);
        this.switchtorefresh = (SwipeRefreshLayout) view.findViewById(R.id.switchto_refresh);
        this.companyList = (RecyclerView) view.findViewById(R.id.companyList);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        companyList.setLayoutManager(linearLayoutManager);
        companyList.setHasFixedSize(true);
        companyOfInterestAdapter = new CompanyOfInterestAdapter(getContext(), companyName);
        companyList.setAdapter(companyOfInterestAdapter);
        switchtorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //wishlistProgressDialog.dialogShow();
                //companyOfInterestInterface.loadComapnyList(pageNumber);
                switchtorefresh.setRefreshing(false);
                companyOfInterestAdapter.notifyDataSetChanged();


            }
        });


        companyList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                companyName.add(null);
                companyOfInterestAdapter.notifyItemInserted(companyName.size() - 1);
                companyName.remove(companyName.size() - 1);
                companyOfInterestAdapter.notifyItemRemoved(companyName.size());
                // if (TotalSelectedCompanies.refreashList == 1) {
                if (isLoading && linearLayoutManager.getItemCount() <= (linearLayoutManager.findLastVisibleItemPosition() + visibleThreshold)) {
                    // ++pageNumber;
                    searchProgressBar.setVisibility(View.VISIBLE);
                    searchProgressBar.start();
                    /// progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#5B9EEC"), PorterDuff.Mode.MULTIPLY);
                    companyOfInterestInterface.loadComapnyList(pageNumber);
                    isLoading = false;

                    //}
                }
            }
        });
        companyList.addOnItemTouchListener(new OnclickListenerRecycle(getActivity(), companyList, new OnclickListenerRecycle.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                totalno.add(position);
                companyListDatabase.insertCounterCompanyTable(companyName.get(position).getId(), companyName.get(position).getTitle(), companyName.get(position).getProfileImageUrl());
                companyListDatabase.deleteCompanyListItem(companyName.get(position).getId());
                companyName.remove(position);
                UpdateNumberOfItems updateNumberOfItems = (UpdateNumberOfItems) getContext();
                updateNumberOfItems.updateCounter();
                companyOfInterestAdapter.notifyDataSetChanged();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return view;
    }


    @Override
    public void getCompanyList(List<Result> resultLeadses) {
        wishlistProgressDialog.dismissDialog();
        searchProgressBar.setVisibility(View.GONE);
        searchProgressBar.stop();
        companyName.clear();
        companyName.addAll(resultLeadses);
        setListData();
        isLoading = true;
       // pageNumber++;

    }

    private void setListData() {
        if (vi == 0)
            updatetxt.setVisibility(View.GONE);
        totalSelectedComapny.clear();
        int j;
        //linearLayoutManager.scrollToPositionWithOffset(displayedposition, companyName.size());
        totalSelectedComapny = companyListDatabase.getCounterCompany();
        for (int i = 0; i < companyName.size(); i++) {

            for (j = 0; j < totalSelectedComapny.size(); j++) {

                Result result = totalSelectedComapny.get(j);
                int id = result.getId();

                if (id == companyName.get(i).getId()) {
                    companyName.remove(i);

                }
            }
        }

        companyOfInterestAdapter.notifyDataSetChanged();
        companyOfInterestAdapter.notifyItemInserted(companyName.size());
        wishlistProgressDialog.dismissDialog();
    }

    @Override
    public void getnext(int next) {
         pageNumber = next;
    }

    @Override
    public void newtWorkconnection(String connection) throws IllegalAccessException, ClassNotFoundException, java.lang.InstantiationException {
        wishlistProgressDialog.dismissDialog();
        // new AlertDialogBox(getContext()).networkMessage(connection);
        NetworkInterface networkInterface = (NetworkInterface) getContext();
        networkInterface.connectionMessage(connection);
    }


    @Override
    public void refreshpage() {
        if (TotalSelectedCompanies.refreashList == 1) {
            updatetxt.setVisibility(View.VISIBLE);
            //++pageNumber;
            companyOfInterestInterface.loadComapnyList(pageNumber);
            vi = 0;
            TotalSelectedCompanies.refreashList = 0;
        }
    }


    class DownloadFilesTask extends AsyncTask<String, Integer, Long> {
        @Override
        protected Long doInBackground(String... strings) {
            return null;
        }
        // Do the long-running work in here

    }

}
