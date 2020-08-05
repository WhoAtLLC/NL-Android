package com.root.wishlist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.root.wishlist.R;
import com.root.wishlist.adapters.ChangeServerRecyclerViewAdapter;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.interfaces.RecyclerItemPosition;
import com.root.wishlist.util.globalValues.AlertDialogBox;
import com.root.wishlist.util.globalValues.GlobalClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeServerActivity extends AppCompatActivity implements RecyclerItemPosition {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addServerTv)
    TextView addServerTv;
    @BindView(R.id.serverListRecyclerView)
    RecyclerView serverListRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ChangeServerRecyclerViewAdapter changeServerRecyclerViewAdapter;
    private static List<String> serverInfoList = new ArrayList<>();
    private static List<String> serverUrlList = new ArrayList<>();
    private AlertDialogBox alertDialogBox;
    private SharedDatabase sharedDatabase;
    int selectedServer = 1;
    private int selectedServerPosition = 1;
    private String returnActionAppInfo;
    private int serverAddListSize = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_server);
        ButterKnife.bind(this);
        new GlobalClass().statusBar(this);
        alertDialogBox = new AlertDialogBox(this);
        //   getSupportActionBar().hide();

        sharedDatabase = new SharedDatabase(this);

        if (sharedDatabase.getTargetServer() != "")
        {
            selectedServerPosition = sharedDatabase.getTargetServerPosition();
        }
            //selectedServer = serverUrlList.indexOf(sharedDatabase.getTargetServer());


        serverInfoList.clear();
        serverUrlList.clear();
        for (int i = 1; i <=serverAddListSize ; i++) {
            if(i==1)
            {
                serverInfoList.add("Development");
                serverUrlList.add("http://wishlist.operislabs.com/");
            }
            else if(i==2)
            {
                serverInfoList.add("Production");
                serverUrlList.add("https://wishlist.whoat.net/");
            }
            else {
                serverInfoList.add("Dev "+i);
                serverUrlList.add("https://niceleads-staging-pr-"+i+".herokuapp.com");
            }


        }
        serverListRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        serverListRecyclerView.setLayoutManager(linearLayoutManager);
        changeServerRecyclerViewAdapter = new ChangeServerRecyclerViewAdapter(serverInfoList, serverUrlList, serverListRecyclerView, this, this, selectedServerPosition);
        serverListRecyclerView.setAdapter(changeServerRecyclerViewAdapter);
        changeServerRecyclerViewAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.addServerTv)
    public void onViewClicked() {

        showAppInfo();
    }
    private void showAppInfo() {
      /*  if (sharedDatabase.getTargetServer().equals(""))
            sharedDatabase.setTargetServer("http://wishlist.whoat.net/");*/
      //  String msg = "App Version: " + sharedDatabase.getVersion() + "\n\n" + "Server: " + sharedDatabase.getTargetServer() + "\n\n" + "If you yant to change the target server, you can write it below.";

        returnActionAppInfo=alertDialogBox.appInfoDialog();

          /*  returnActionAppInfo= alertDialogBox.appInfoDialog(msg);
            if(returnActionAppInfo!=null)
            {
                if(!returnActionAppInfo.equals("server not changed"))
                    sharedDatabase.setTargetServer(returnActionAppInfo);
                else
                    sharedDatabase.setTargetServer(Constants.BASE_URL);
            }*/


    }

    @Override
    public void getItemPosition(int position) {
        sharedDatabase.setTargetServer(serverUrlList.get(position));
        sharedDatabase.setTargetServerPosition(position);

    }


    public void getCallBackDialog(String returnActionAppInfo) {
        if(!returnActionAppInfo.equals(""))
        {
           /* serverAddListSize = serverAddListSize+1;
            serverInfoList.clear();
            serverUrlList.clear();
            for (int i = 1; i <=serverAddListSize ; i++) {
                if(i==1)
                {
                    serverInfoList.add("Development");
                    serverUrlList.add("http://wishlist.operislabs.com/");
                }
                else if(i==2)
                {
                    serverInfoList.add("Production");
                    serverUrlList.add("http://wishlist.whoat.net/");
                }
                else if(i>2&&i<=99){
                    serverInfoList.add("Dev "+i);
                    serverUrlList.add("https://niceleads-staging-pr-"+i+".herokuapp.com");
                }
                else
                {
                    serverUrlList.add(returnActionAppInfo);
                    serverInfoList.add("Dev");
                }*/

           serverUrlList.add(returnActionAppInfo);
           serverInfoList.add("Dev");


           // }

            serverListRecyclerView.setHasFixedSize(true);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            serverListRecyclerView.setLayoutManager(linearLayoutManager);
            changeServerRecyclerViewAdapter = new ChangeServerRecyclerViewAdapter(serverInfoList, serverUrlList, serverListRecyclerView, this, this, selectedServerPosition);
            serverListRecyclerView.setAdapter(changeServerRecyclerViewAdapter);
            changeServerRecyclerViewAdapter.notifyDataSetChanged();
        }
    }


}
