package com.root.wishlist.activities.leads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.activities.LeadsActivity;
import com.root.wishlist.adapters.MembersAdapters;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.pojo.leads.YourConnectionAtResult;
import com.root.wishlist.pojo.leads.YourConnectionpossibleto;
import com.root.wishlist.presentation.leads.LeadsYourConnectionAtInterface;
import com.root.wishlist.presentation.leads.LeasdYourConnectionAtPresentation;
import com.root.wishlist.util.SearchEditText;
import com.root.wishlist.util.globalValues.NoNetworkConnection;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.util.globalValues.coachmark.YourConnectionCoachmark;
import com.root.wishlist.view.LeadsYourConnectionView;

import java.util.ArrayList;
import java.util.List;

import me.wangyuwei.loadingview.LoadingView;

public class LeadsYourConnectionAtActivity extends AppCompatActivity implements LeadsYourConnectionView {


    private android.widget.TextView title;
    LeadsYourConnectionAtInterface leadsYourConnectionAtInterface;
    MembersAdapters membersAdapters;
    Intent intent;
    String connectiontitle = null;
    List<YourConnectionAtResult> yourConnectionAtResults = new ArrayList<>();
    List<YourConnectionpossibleto> yourConnectionpossibletos = new ArrayList<>();
    WishlistProgressDialog wishlistProgressDialog;
    SharedDatabase sharedDatabase;
    private SearchEditText searchdata;
    private LinearLayoutManager linearLayoutManager;
    private android.support.v7.widget.RecyclerView memberlist;
    private boolean isLoading = true;
    private int displayedposition = 5;
    int pageNumber = 1;
    int count = 1;
    private me.wangyuwei.loadingview.LoadingView searchProgressBar;
    private ImageView yourConnectionAtbackLeadPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_your_connection_at);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StatusBarTransparent.colorStatusbar(LeadsYourConnectionAtActivity.this);
        this.searchProgressBar = (LoadingView) findViewById(R.id.searchProgressBar);
        this.searchdata = (SearchEditText) findViewById(R.id.searchdata);
        this.title = (TextView) findViewById(R.id.title);
        this.memberlist = (RecyclerView) findViewById(R.id.memberlist);
        this.yourConnectionAtbackLeadPage= (ImageView) findViewById(R.id.yourconnectionatbackleadpage) ;

        sharedDatabase = new SharedDatabase(getApplicationContext());
        wishlistProgressDialog = new WishlistProgressDialog(this);
        wishlistProgressDialog.dialogShow();
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        memberlist.setLayoutManager(linearLayoutManager);
        memberlist.setHasFixedSize(true);
        membersAdapters = new MembersAdapters(getApplicationContext(), yourConnectionAtResults);
        memberlist.setAdapter(membersAdapters);
        if (!sharedDatabase.getYourConnectionAt()) {
            new YourConnectionCoachmark(this).validationdialog("yourconnectionat", true);
        }
        title.setText("" + getIntent().getStringExtra("title"));

        leadsYourConnectionAtInterface = new LeasdYourConnectionAtPresentation(getApplicationContext(), LeadsYourConnectionAtActivity.this);

        Log.d("usetID", String.valueOf(getIntent().getIntExtra("userid", 0)));
        leadsYourConnectionAtInterface.getLeadsYourConnectionAt(getIntent().getIntExtra("userid", 0), pageNumber);


        searchdata.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                membersAdapters.getFilter().filter(charSequence.toString());
                membersAdapters.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchdata.setCursorVisible(true);
            }
        });
        searchdata.setKeyImeChangeListener(new SearchEditText.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                searchdata.setCursorVisible(false);

            }
        });
        memberlist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                yourConnectionAtResults.add(null);
                membersAdapters.notifyItemInserted(yourConnectionAtResults.size() - 1);
                yourConnectionAtResults.remove(yourConnectionAtResults.size() - 1);
                membersAdapters.notifyItemRemoved(yourConnectionAtResults.size());
                if (isLoading && linearLayoutManager.getItemCount() <= (linearLayoutManager.findLastVisibleItemPosition() + displayedposition)) {
                    pageNumber = pageNumber + 1;
                    searchProgressBar.setVisibility(View.VISIBLE);
                    searchProgressBar.start();
                    leadsYourConnectionAtInterface.getLeadsYourConnectionAt(getIntent().getIntExtra("userid", 0), pageNumber);
                    isLoading = false;
                }
            }
        });

        yourConnectionAtbackLeadPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void getYourConnection(List<YourConnectionAtResult> resultLeadses, int totalCount) {
        yourConnectionAtResults.clear();
        wishlistProgressDialog.dismissDialog();
        searchProgressBar.stop();
        searchProgressBar.setVisibility(View.GONE);

        count = totalCount;
        yourConnectionAtResults.addAll(resultLeadses);
        membersAdapters.notifyItemInserted(yourConnectionAtResults.size());
        isLoading = true;

    }

    @Override
    public void newtworkConnection(String message) {
        wishlistProgressDialog.dismissDialog();
        //new AlertDialogBox(getApplicationContext()).networkMessage(message);
        try {
            NoNetworkConnection.networkMessage(this, "com.root.wishlist.activities.leads.LeadsYourConnectionAtActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
     /*   startActivity(new Intent(LeadsYourConnectionAtActivity.this, LeadsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        overridePendingTransition(0, 0);*/
        finish();
    }
}
