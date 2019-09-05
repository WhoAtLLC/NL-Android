package com.root.wishlist.fragment.members;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.root.wishlist.adapters.MyWishListMembersAdopter;
import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.presentation.members.MyWishListFragmentInterface;
import com.root.wishlist.presentation.members.MyWishListFragmentPresentation;
import com.root.wishlist.R;
import com.root.wishlist.util.DividerItemDecoration;
import com.root.wishlist.view.membersview.MywishListFragmentView;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.ArrayList;
import java.util.List;


public class MyWishlistFragment extends Fragment implements MywishListFragmentView {


    static String userName = "";

    public static ArrayList<Result> myWishlistArrayList = new ArrayList<>();
    MyWishListFragmentInterface myWishListFragmentInterface;
    private android.widget.TextView username;
    private String company;
    MyWishListMembersAdopter myWishListMembersAdopter;
    private com.github.clans.fab.FloatingActionButton editfloat;
    private com.github.clans.fab.FloatingActionMenu materialdesignandroidfloatingactionmenu;
    private RecyclerView memberlist;
    private Typeface normalFont;

    public MyWishlistFragment(String title, String company) {
        userName = title;
        this.company = company;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myWishListFragmentInterface = new MyWishListFragmentPresentation(MyWishlistFragment.this, getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myWishListFragmentInterface.loadMyWishListFragment(userName);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);
        this.memberlist = (RecyclerView) view.findViewById(R.id.memberlist);

        this.materialdesignandroidfloatingactionmenu = (FloatingActionMenu) view.findViewById(R.id.material_design_android_floating_action_menu);
        this.username = (TextView) view.findViewById(R.id.username);
        username.setText(userName + " has " + company + " contacts at these companies");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        memberlist.addItemDecoration(itemDecoration);
        normalFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");
        username.setTypeface(normalFont);
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
    public void getMyWishList(List<Result> myWishlistFragmentBeans) {
        myWishlistArrayList = new ArrayList<>();
        for (int i = 0; i < myWishlistFragmentBeans.size(); i++) {
            String title = myWishlistFragmentBeans.get(i).getTitle();
            int id = myWishlistFragmentBeans.get(i).getId();
            int leads = myWishlistFragmentBeans.get(i).getLeads();
            int mutual = myWishlistFragmentBeans.get(i).getMutual();
            myWishlistArrayList.add(new Result(title, id, leads, mutual));
        }

        myWishListMembersAdopter = new MyWishListMembersAdopter(getActivity(), myWishlistArrayList);
        memberlist.setAdapter(myWishListMembersAdopter);

    }

    @Override
    public void networError(String coonection) {
        NetworkInterface networkInterface = (NetworkInterface) getContext();
        try {
            networkInterface.connectionMessage(coonection);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}