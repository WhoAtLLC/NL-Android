package com.root.wishlist.fragment.notification;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.root.wishlist.adapters.WanttoMutualContactAdapter;
import com.root.wishlist.pojo.leads.getintroduce.IntroResult;
import com.root.wishlist.pojo.wantto.WnatoResult;
import com.root.wishlist.presentation.leads.IntroduceMatualContactsPresentation;
import com.root.wishlist.presentation.leads.IntroduceMutualContacts;
import com.root.wishlist.R;
import com.root.wishlist.view.IntroduceMutualContactsView;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.ArrayList;
import java.util.List;

import me.wangyuwei.loadingview.LoadingView;

public class NotificationMutualContact extends Fragment implements IntroduceMutualContactsView {

    private android.widget.TextView requestTo;
    IntroduceMutualContacts introduceMutualContacts;
    WanttoMutualContactAdapter getMatualContactsAdapter;
    int pageNumber = 1;

    int requestCode;
    int displayedposition = 5;
    List<WnatoResult> allmutual = new ArrayList<>();
    private me.wangyuwei.loadingview.LoadingView loadingview;
    LinearLayoutManager linearLayoutManager;
    private RecyclerView getmutualcontact;
    private boolean isloading = true;

    public NotificationMutualContact(int requestCode) {
        this.requestCode = requestCode;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        introduceMutualContacts = new IntroduceMatualContactsPresentation(getContext(), NotificationMutualContact.this);
        introduceMutualContacts.notificationMutualContacts(requestCode, pageNumber);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_mutual_contact, container, false);
        this.getmutualcontact = (RecyclerView) view.findViewById(R.id.get_mutual_contact);
        this.loadingview = (LoadingView) view.findViewById(R.id.loading_view);


        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getmutualcontact.setHasFixedSize(true);
        getmutualcontact.setLayoutManager(linearLayoutManager);
        getMatualContactsAdapter = new WanttoMutualContactAdapter(getContext(), allmutual);
        getmutualcontact.setAdapter(getMatualContactsAdapter);
        getmutualcontact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if ((displayedposition + linearLayoutManager.findLastVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {
                        if (isloading) {
                            loadingview.setVisibility(View.VISIBLE);
                            loadingview.start();
                            getMatualContactsAdapter.notifyDataSetChanged();
                            introduceMutualContacts.notificationMutualContacts(requestCode, pageNumber);
                            isloading = false;
                        }
                    }
                }

            }
        });

        return view;
    }

    @Override
    public void setMutualContact(List<IntroResult> mutualContact) {


    }

    @Override
    public void getNotificationMutualContact(List<WnatoResult> mutualContact) {
        pageNumber++;
        allmutual.addAll(mutualContact);
        loadingview.setVisibility(View.GONE);
        loadingview.stop();
        getMatualContactsAdapter.notifyDataSetChanged();
        getMatualContactsAdapter.notifyItemInserted(allmutual.size());
        isloading = true;
    }

    @Override
    public void getPageNo(String pNumber) {

    }

    @Override
    public void networkError(String connection) {
        loadingview.setVisibility(View.GONE);
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


}
