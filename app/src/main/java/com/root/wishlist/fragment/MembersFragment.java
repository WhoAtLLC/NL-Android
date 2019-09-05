package com.root.wishlist.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.root.wishlist.activities.leads.SMSActivity;
import com.root.wishlist.activities.registration.YourBusinessInterest;
import com.root.wishlist.adapters.MemberLeadAdopter;
import com.root.wishlist.pojo.leads.ResultLeads;
import com.root.wishlist.presentation.leads.LeadsMemberPresentarInt;
import com.root.wishlist.presentation.leads.LeadsMembersPresentation;
import com.root.wishlist.R;
import com.root.wishlist.view.LeadsMembersView;

import java.util.List;

public class MembersFragment extends Fragment implements LeadsMembersView, View.OnClickListener {


    private static final int PICK_SMS_REQUEST = 1245;
    public MemberLeadAdopter membersLeadsAdapter;
    public List<ResultLeads> companyLeadses;
    LeadsMemberPresentarInt leadsCompanyListPresentationInt;
    static int pageNumber = 1;
    private android.widget.LinearLayout blanklayout;
    private android.support.v7.widget.RecyclerView memberlist;
    private android.support.v4.widget.SwipeRefreshLayout swipelist;
    LinearLayoutManager linearLayoutManager;
    Typeface fontFace;
    private android.widget.TextView usernametxt;
    private android.widget.TextView leadstxt;
    private TextView invitefriends;
    private TextView mywishlisttxt;

    public MembersFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        leadsCompanyListPresentationInt = new LeadsMembersPresentation(getActivity(), MembersFragment.this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leadsCompanyListPresentationInt.loadMembersList(pageNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_members, container, false);
        this.mywishlisttxt = (TextView) view.findViewById(R.id.my_wishlist_txt);
        this.invitefriends = (TextView) view.findViewById(R.id.invite_friends);
        fontFace = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        this.leadstxt = (TextView) view.findViewById(R.id.leads_txt);
        this.usernametxt = (TextView) view.findViewById(R.id.username_txt);
        leadstxt.setTypeface(fontFace);
        usernametxt.setTypeface(fontFace);
        invitefriends.setTypeface(fontFace);
        invitefriends.setOnClickListener(this);
        mywishlisttxt.setOnClickListener(this);
        this.swipelist = (SwipeRefreshLayout) view.findViewById(R.id.swipe_list);
        this.memberlist = (RecyclerView) view.findViewById(R.id.memberlist);
        this.blanklayout = (LinearLayout) view.findViewById(R.id.blank_layout);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setHasFixedSize(true);

        memberlist.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if ((linearLayoutManager.getChildCount() + linearLayoutManager.findFirstVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {

                        leadsCompanyListPresentationInt.loadMembersList(pageNumber);
                    }
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
    public void getMembersList(List<ResultLeads> resultLeadses) {

        companyLeadses = resultLeadses;
        if (companyLeadses.size() == 0)
            blanklayout.setVisibility(View.VISIBLE);
        else
            blanklayout.setVisibility(View.GONE);
        membersLeadsAdapter = new MemberLeadAdopter(getContext(), resultLeadses);
        memberlist.setAdapter(membersLeadsAdapter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.invite_friends) {
            // InviteFriendMessage.sendSms(getContext());
            Intent intent = new Intent(getActivity(), SMSActivity.class);
            startActivityForResult(intent, PICK_SMS_REQUEST);
        }
        if (v.getId() == R.id.my_wishlist_txt) {
            startActivity(new Intent(getContext(), YourBusinessInterest.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION).putExtra("flagvalue", 2));
            getActivity().finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_SMS_REQUEST) {

            if (resultCode == 1) {
                Log.d("dnjadnjka", "dusadghuaud");
            }
            if (resultCode == 2) {
                leadsCompanyListPresentationInt.loadMembersList(pageNumber);
            }
        }
    }
}
