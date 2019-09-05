package com.root.wishlist.fragment.members;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.root.wishlist.R;
import com.root.wishlist.activities.leads.MemberCompanyDetailsNew;
import com.root.wishlist.adapters.MutualContactAdopter;
import com.root.wishlist.pojo.members.MutualContactResult;
import com.root.wishlist.presentation.members.MutualContactFragmentInterface;
import com.root.wishlist.presentation.members.MutualContactFragmentPresentation;
import com.root.wishlist.util.DividerItemDecoration;
import com.root.wishlist.view.membersview.MutualContactView;

import java.util.ArrayList;
import java.util.List;

import me.wangyuwei.loadingview.LoadingView;

public class MutualContactsFragment extends Fragment implements MutualContactView {

    static int pageNumber = 1;
    static String userName = "";
    MutualContactFragmentInterface mutualContactFragmentInterface;
    MutualContactAdopter mutualContactAdopter;
    public static ArrayList<MutualContactResult> myWishlistArrayList = new ArrayList<>();
    public static ArrayList<MutualContactResult> myWishlistArrayList1 = new ArrayList<>();
    private RecyclerView memberlist;
    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading = true;
    private int displayedposition = 5;
    private me.wangyuwei.loadingview.LoadingView searchProgressBar;

    public MutualContactsFragment(String title) {
        userName = title;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mutualContactFragmentInterface = new MutualContactFragmentPresentation(MutualContactsFragment.this, getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matual_contact, container, false);
        this.searchProgressBar = (LoadingView) view.findViewById(R.id.searchProgressBar);
        this.memberlist = (RecyclerView) view.findViewById(R.id.memberlist);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        memberlist.addItemDecoration(itemDecoration);
        mutualContactFragmentInterface.loadMyMutualContactFragment(pageNumber, userName);
        mutualContactAdopter = new MutualContactAdopter(getActivity(), myWishlistArrayList);

        memberlist.setAdapter(mutualContactAdopter);
        memberlist.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                myWishlistArrayList.add(null);
                mutualContactAdopter.notifyItemInserted(myWishlistArrayList.size() - 1);
                myWishlistArrayList.remove(myWishlistArrayList.size() - 1);
                mutualContactAdopter.notifyItemRemoved(myWishlistArrayList.size());
                if (isLoading && linearLayoutManager.getItemCount() <= (linearLayoutManager.findLastVisibleItemPosition() + displayedposition)) {
                    ++pageNumber;
                    searchProgressBar.setVisibility(View.VISIBLE);
                    searchProgressBar.start();
                    /// progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#5B9EEC"), PorterDuff.Mode.MULTIPLY);
                    mutualContactFragmentInterface.loadMyMutualContactFragment(pageNumber, userName);
                    isLoading = false;


                }
            }
        });
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
    public void getMutualContactWishList(List<MutualContactResult> theirResultList) {
        searchProgressBar.setVisibility(View.GONE);
        searchProgressBar.stop();

        myWishlistArrayList1.clear();
        for (int i = 0; i < theirResultList.size(); i++) {
            String firstName = theirResultList.get(i).getFirstName();
            String lastName = theirResultList.get(i).getLastName();
            String title = theirResultList.get(i).getTitle();
            String company = theirResultList.get(i).getCompany();
            myWishlistArrayList.add(new MutualContactResult(firstName, lastName, title, company));

        }
        myWishlistArrayList1.addAll(myWishlistArrayList);
        mutualContactAdopter.notifyItemInserted(myWishlistArrayList.size());
        isLoading = true;


     /*MemberCompanyDetailsNew.tablayout.addTab(MemberCompanyDetailsNew.tablayout.newTab().setText("Mutual Contacts"+myWishlistArrayList.size()));*/


    }

}