package com.root.wishlist.fragment.getinteroduceto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.adapters.GetMatualContactsAdapter;
import com.root.wishlist.interfaces.NetworkInterface;
import com.root.wishlist.pojo.leads.getintroduce.IntroResult;
import com.root.wishlist.pojo.wantto.WnatoResult;
import com.root.wishlist.presentation.leads.IntroduceMatualContactsPresentation;
import com.root.wishlist.presentation.leads.IntroduceMutualContacts;
import com.root.wishlist.view.IntroduceMutualContactsView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.wangyuwei.loadingview.LoadingView;


public class GetMatualContacts extends Fragment implements IntroduceMutualContactsView {

    private android.widget.TextView requestTo;
    IntroduceMutualContacts introduceMutualContacts;
    List<IntroResult> introResults = new ArrayList<>();
    GetMatualContactsAdapter getMatualContactsAdapter;
    String handle;
    Bundle bundle;
    int pageNumber = 1;
    int flag = 0;
    private TextView message1txt;
    private TextView yes;
    private TextView no;
    private android.support.v7.widget.RecyclerView getmutualcontact;
    LinearLayoutManager linearLayoutManager;
    Set<IntroResult> hsStrings = new HashSet<>();
    int displayedposition = 5;
    private me.wangyuwei.loadingview.LoadingView searchProgressBar;
    private boolean isLoading = true;

    @SuppressLint("ValidFragment")
    public GetMatualContacts(int flag) {
        this.flag = flag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        handle = bundle.getString("handle");
        introduceMutualContacts = new IntroduceMatualContactsPresentation(getContext(), GetMatualContacts.this);
        introduceMutualContacts.getIntroMutualContacts(handle, pageNumber);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_matual_contacts, container, false);
        this.searchProgressBar = (LoadingView) view.findViewById(R.id.searchProgressBar);
        this.getmutualcontact = (RecyclerView) view.findViewById(R.id.get_mutual_contact);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        getmutualcontact.setLayoutManager(linearLayoutManager);
        getmutualcontact.setHasFixedSize(true);

        getMatualContactsAdapter = new GetMatualContactsAdapter(getContext(), introResults);
        getmutualcontact.setAdapter(getMatualContactsAdapter);

        getmutualcontact.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                introResults.add(null);
                getMatualContactsAdapter.notifyItemInserted(introResults.size() - 1);
                introResults.remove(introResults.size() - 1);
                getMatualContactsAdapter.notifyItemRemoved(introResults.size());

                if (dy > 0) {
                    if ((displayedposition + linearLayoutManager.findLastVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {
                        if (isLoading) {
                            ++pageNumber;
                            searchProgressBar.setVisibility(View.VISIBLE);
                            searchProgressBar.start();
                            introduceMutualContacts.getIntroMutualContacts(bundle.getString("handle"), pageNumber);
                            isLoading = false;
                        }
                    }
                }

            }
        });
        return view;
    }

    @Override
    public void setMutualContact(List<IntroResult> mutualContact) {
        searchProgressBar.stop();
        searchProgressBar.setVisibility(View.GONE);
        if(mutualContact!=null)
            introResults.addAll(mutualContact);
        getMatualContactsAdapter.notifyDataSetChanged();
        getMatualContactsAdapter.notifyItemInserted(introResults.size());
        isLoading = true;
    }

    @Override
    public void getNotificationMutualContact(List<WnatoResult> mutualContact) {

    }

    @Override
    public void getPageNo(String pNumber) {
        /*if (pNumber.equals(""));
           // pageNumber = 1;
        else*/

    }

    @Override
    public void networkError(String connection) {
        searchProgressBar.stop();
        searchProgressBar.setVisibility(View.GONE);
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
