package com.root.wishlist.fragment.members;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.root.wishlist.adapters.MyWishListMembersAdopter;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.pojo.members.Result;
import com.root.wishlist.presentation.members.TheirMyWishListTabFragmentPresentation;
import com.root.wishlist.presentation.members.TheirWishListFragmentInterface;
import com.root.wishlist.R;
import com.root.wishlist.util.DividerItemDecoration;
import com.root.wishlist.view.membersview.MywishListFragmentView;
import com.root.wishlist.interfaces.NetworkInterface;

import java.util.ArrayList;
import java.util.List;


public class TheirWishlistFragment extends Fragment implements MywishListFragmentView {


    private android.widget.TextView title;
    private android.widget.LinearLayout theirwishlistlinear;

    static String userName = "";
    TheirWishListFragmentInterface theirWishListFragmentInterface;
    MyWishListMembersAdopter myWishListMembersAdopter;
    public static ArrayList<Result> theirWishlistArrayList = new ArrayList<>();
    private com.github.clans.fab.FloatingActionButton editfloat;
    private com.github.clans.fab.FloatingActionMenu materialdesignandroidfloatingactionmenu;
    private RecyclerView memberlist;
    WishlistProgressDialog wishlistProgressDialog;
    private boolean isloading = true;
    int displayNum = 5;

    public TheirWishlistFragment(String title) {
        userName = title;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        wishlistProgressDialog = new WishlistProgressDialog(activity);
        theirWishListFragmentInterface = new TheirMyWishListTabFragmentPresentation(TheirWishlistFragment.this, getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wishlistProgressDialog.dialogShow();
        theirWishListFragmentInterface.loadTheirWishListFragment(userName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_their_wishlist, container, false);
        this.memberlist = (RecyclerView) view.findViewById(R.id.memberlist);
        this.materialdesignandroidfloatingactionmenu = (FloatingActionMenu) view.findViewById(R.id.material_design_android_floating_action_menu);
        this.theirwishlistlinear = (LinearLayout) view.findViewById(R.id.theirwishlist_linear);
        this.title = (TextView) view.findViewById(R.id.title);
        title.setText("Companies you can help " + userName + " with.");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        memberlist.addItemDecoration(itemDecoration);
        myWishListMembersAdopter = new MyWishListMembersAdopter(getActivity(), theirWishlistArrayList);
        memberlist.setAdapter(myWishListMembersAdopter);
        memberlist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                theirWishlistArrayList.add(null);
                myWishListMembersAdopter.notifyItemInserted(theirWishlistArrayList.size() - 1);
                theirWishlistArrayList.remove(theirWishlistArrayList.size() - 1);
                myWishListMembersAdopter.notifyItemRemoved(theirWishlistArrayList.size());
                if (isloading && linearLayoutManager.getItemCount() <= (linearLayoutManager.findLastVisibleItemPosition() + displayNum)) {

                    /// progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#5B9EEC"), PorterDuff.Mode.MULTIPLY);
                    theirWishListFragmentInterface.loadTheirWishListFragment(userName);
                    isloading = false;


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
    public void getMyWishList(List<Result> myWishlistFragmentBeans) {
        wishlistProgressDialog.dismissDialog();
        for (int i = 0; i < myWishlistFragmentBeans.size(); i++) {
            String title = myWishlistFragmentBeans.get(i).getTitle();
            int id = myWishlistFragmentBeans.get(i).getId();
            int leads = myWishlistFragmentBeans.get(i).getLeads();
            int mutual = myWishlistFragmentBeans.get(i).getMutual();
            theirWishlistArrayList.add(new Result(title, id, leads, mutual));
        }
        myWishListMembersAdopter.notifyItemInserted(theirWishlistArrayList.size());
    }

    @Override
    public void networError(String coonection) {
        wishlistProgressDialog.dismissDialog();
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