package com.root.wishlist.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.activities.leads.LeadsYourConnectionAtActivity;
import com.root.wishlist.adapters.LeadsCompanyAdapter;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.pojo.leads.MemberResult;
import com.root.wishlist.presentation.leads.LeadsComapnyPresentation;
import com.root.wishlist.presentation.leads.LeadsCompanyListPresentationInt;
import com.root.wishlist.util.OnclickListenerRecycle;
import com.root.wishlist.util.globalValues.InviteFriendMessage;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.util.globalValues.coachmark.YourConnectionCoachmark;
import com.root.wishlist.view.LeadsCompanyListView;

import java.util.ArrayList;
import java.util.List;

import me.wangyuwei.loadingview.LoadingView;


public class LeadsCompaniesFragment extends Fragment implements LeadsCompanyListView, View.OnClickListener {


    public static LeadsCompanyAdapter leadsCompanyAdapter;
    List<MemberResult> companyLeadses = new ArrayList<>();
    private android.widget.EditText searchdata;
    LeadsCompanyListPresentationInt leadsCompanyListPresentationInt;
    WishlistProgressDialog wishlistProgressDialog;
    int pageNumber = 1;
    private android.support.v4.widget.SwipeRefreshLayout swipelist;
    private android.widget.LinearLayout blanklayout;
    private RecyclerView memberlist;
    LinearLayoutManager linearLayoutManager;
    Typeface fontFace;
    private android.widget.TextView companiestxt;
    private android.widget.TextView connectionstxt;
    private int visible = 1;
    int displayedposition;
    private me.wangyuwei.loadingview.LoadingView searchProgressBar;
    SharedDatabase sharedDatabase;
    private TextView invitefriends;
    private boolean isLoading = true;
    int visibleThreshold = 5;

    public LeadsCompaniesFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sharedDatabase = new SharedDatabase(getContext());
        wishlistProgressDialog = new WishlistProgressDialog(getActivity());
        leadsCompanyListPresentationInt = new LeadsComapnyPresentation(LeadsCompaniesFragment.this, getActivity());


        if (sharedDatabase.loadLeadsCompany() != null && sharedDatabase.loadLeadsCompany().size() != 0) {

            companyLeadses.addAll(sharedDatabase.loadLeadsCompany());
        } else {
            wishlistProgressDialog.dialogShow();
            leadsCompanyListPresentationInt.loadComapnyList(pageNumber);
        }

        if (!sharedDatabase.getLeadsCompany()) {
            new YourConnectionCoachmark(getContext()).leadsCoachmark("leadscompany", true, getScreenSize());
        }

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leads_companies, container, false);
        this.invitefriends = (TextView) view.findViewById(R.id.invite_friends);
        this.searchProgressBar = (LoadingView) view.findViewById(R.id.searchProgressBar);
        fontFace = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        this.connectionstxt = (TextView) view.findViewById(R.id.connections_txt);
        this.companiestxt = (TextView) view.findViewById(R.id.companies_txt);
        companiestxt.setTypeface(fontFace);
        connectionstxt.setTypeface(fontFace);
        invitefriends.setTypeface(fontFace);
        invitefriends.setOnClickListener(this);
        this.memberlist = (RecyclerView) view.findViewById(R.id.memberlist);
        this.blanklayout = (LinearLayout) view.findViewById(R.id.blank_layout);
        this.swipelist = (SwipeRefreshLayout) view.findViewById(R.id.swipe_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setHasFixedSize(true);
        //companyLeadses.clear();
        if (companyLeadses.size() <= 0 || companyLeadses == null) {
            blanklayout.setVisibility(View.VISIBLE);
        }

        if (companyLeadses.size() != 0)
            blanklayout.setVisibility(View.GONE);
        leadsCompanyAdapter = new LeadsCompanyAdapter(getContext(), companyLeadses);
        memberlist.setAdapter(leadsCompanyAdapter);
        swipelist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                leadsCompanyAdapter.notifyDataSetChanged();
               // leadsCompanyListPresentationInt.loadComapnyList(pageNumber);
                swipelist.setRefreshing(false);

            }
        });

        memberlist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                companyLeadses.add(null);
                leadsCompanyAdapter.notifyItemInserted(companyLeadses.size() - 1);
                companyLeadses.remove(companyLeadses.size() - 1);
                leadsCompanyAdapter.notifyItemRemoved(companyLeadses.size());
                if (isLoading && linearLayoutManager.getItemCount() <= (linearLayoutManager.findLastVisibleItemPosition() + visibleThreshold)) {
                    pageNumber = sharedDatabase.getNextPage();
                    Log.d("fjakfhahfhpage", String.valueOf(pageNumber));
                    searchProgressBar.setVisibility(View.VISIBLE);
                    searchProgressBar.start();
                    visible = 0;
                    leadsCompanyListPresentationInt.loadComapnyList(pageNumber);
                    isLoading = false;


                }
            }
        });
        memberlist.addOnItemTouchListener(new OnclickListenerRecycle(getActivity(), memberlist, new OnclickListenerRecycle.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(view.getContext(), LeadsYourConnectionAtActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("userid", companyLeadses.get(position).getId());
                intent.putExtra("title", companyLeadses.get(position).getTitle());
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
             //   getActivity().finish();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void getCompanyList(List<MemberResult> resultLeadses) {

        searchProgressBar.setVisibility(View.GONE);
        searchProgressBar.stop();
        wishlistProgressDialog.dismissDialog();
        companyLeadses.addAll(resultLeadses);
        sharedDatabase.saveLeadsCompany(null);
        sharedDatabase.saveLeadsCompany(companyLeadses);
        if (companyLeadses.size() != 0)
            blanklayout.setVisibility(View.GONE);
        leadsCompanyAdapter.notifyItemInserted(companyLeadses.size());
        isLoading = true;


    }

    @Override
    public void getnext(int next) {
        // pageNumber = next;
    }

    @Override
    public void networkMessage(String connection) {

        if (visible == 0)
            searchProgressBar.setVisibility(View.GONE);
        else
            wishlistProgressDialog.dismissDialog();
        //new AlertDialogBox(getContext()).networkMessage(connection);
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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.invite_friends) {
            InviteFriendMessage.sendSms(getContext());
        }
    }

    public int getScreenSize() {
        Point point = new Point();
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(getContext().WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        int height = point.y;
        int width = point.x;
        return width;
    }


}