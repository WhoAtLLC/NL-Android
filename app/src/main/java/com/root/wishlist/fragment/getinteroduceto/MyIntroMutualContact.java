package com.root.wishlist.fragment.getinteroduceto;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.root.wishlist.adapters.GetMatualContactsAdapter;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
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


public class MyIntroMutualContact extends Fragment implements IntroduceMutualContactsView {

    public static List<Integer> hideContact = new ArrayList<>();
    static int pageNumber = 1;
    IntroduceMutualContacts introduceMutualContacts;
    List<IntroResult> introResults = new ArrayList<>();
    GetMatualContactsAdapter getMatualContactsAdapter;
    String handle;
    Bundle bundle;
    WishlistProgressDialog wishlistProgressDialog;
    LinearLayoutManager linearLayoutManager;
    int displayedposition = 5;
    Typeface normalFont;
    private android.widget.TextView requestTo;
    private TextView message1txt;
    private TextView yes;
    private TextView no;
    private String firstName, lastName;
    private android.support.v7.widget.RecyclerView getmutualcontact;
    private me.wangyuwei.loadingview.LoadingView searchProgressBar;
    private boolean isloading = true;

    public MyIntroMutualContact() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bundle = getArguments();
        wishlistProgressDialog = new WishlistProgressDialog(context);
        handle = bundle.getString("handle");
        introduceMutualContacts = new IntroduceMatualContactsPresentation(getContext(), MyIntroMutualContact.this);
        wishlistProgressDialog.dialogShow();
        introduceMutualContacts.getIntroMutualContacts(handle, 1);

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
                if (dy > 0) {
                    if ((displayedposition + linearLayoutManager.findLastVisibleItemPosition()) >= linearLayoutManager.getItemCount()) {
                        if (isloading) {
                            searchProgressBar.setVisibility(View.VISIBLE);
                            searchProgressBar.start();
                            introduceMutualContacts.getIntroMutualContacts(bundle.getString("handle"), pageNumber);
                            isloading = false;
                        }
                    }
                }

            }
        });

        getmutualcontact.addOnItemTouchListener(new GetMatualContactsAdapter(getActivity(), getmutualcontact, new GetMatualContactsAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                String fullName = introResults.get(position).getFirstName() + " " + introResults.get(position).getLastName();
                archiveDelete(fullName, introResults.get(position).getId(), position);
            }
        }));


        return view;
    }

    @Override
    public void setMutualContact(List<IntroResult> mutualContact) {
        pageNumber++;
        wishlistProgressDialog.dismissDialog();
        if (mutualContact != null)
            introResults.addAll(mutualContact);
        searchProgressBar.setVisibility(View.GONE);
        getMatualContactsAdapter.notifyDataSetChanged();
        getMatualContactsAdapter.notifyItemInserted(introResults.size());
        isloading = true;
    }

    @Override
    public void getNotificationMutualContact(List<WnatoResult> mutualContact) {

    }

    @Override
    public void getPageNo(String pNumber) {
        /*if (pNumber.equals(""))
            page = pNumber;
        else {
            page = pNumber;
            pageNumber++;
        }*/

    }

    @Override
    public void networkError(String connection) {

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

    public void archiveDelete(String message, final int id, final int position) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.hide_mutualcontact);
        this.no = (TextView) dialog.findViewById(R.id.no);
        this.yes = (TextView) dialog.findViewById(R.id.yes);
        this.message1txt = (TextView) dialog.findViewById(R.id.message1_txt);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.main_layout_corner));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.75);
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.25);
        dialog.getWindow().setLayout(width, height);
        normalFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        message1txt.setText(message);
        message1txt.setTypeface(normalFont);
        yes.setTypeface(normalFont);
        no.setTypeface(normalFont);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideContact.add(id);
                introResults.remove(position);
                getMatualContactsAdapter.notifyDataSetChanged();
                Log.d("dada", String.valueOf(hideContact.size()));
                dialog.dismiss();
            }
        });
        dialog.show();


    }


}
