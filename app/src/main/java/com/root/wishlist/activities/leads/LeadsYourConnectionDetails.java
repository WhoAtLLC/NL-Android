package com.root.wishlist.activities.leads;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.root.wishlist.adapters.YourConnectionpossibletoAdapter;
import com.root.wishlist.util.globalValues.StatusBarTransparent;
import com.root.wishlist.pojo.leads.YourConnectionpossibleto;
import com.root.wishlist.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LeadsYourConnectionDetails extends AppCompatActivity {

    private android.widget.TextView connectionname;
    private android.widget.TextView title;

    private android.widget.ListView yourconnectionlist;
    List<YourConnectionpossibleto> yourConnectionpossibletos = new ArrayList<>();
    YourConnectionpossibletoAdapter yourConnectionpossibletoAdapter;
    ArrayList<YourConnectionpossibleto> Connectionpossibleto=new ArrayList<>();
    private ArrayList<YourConnectionpossibleto> ConnectionpossibletoArrayList=new ArrayList<>();
    String userTitle = "";
    Typeface normalFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_your_connection_details);
        getSupportActionBar().hide();
        StatusBarTransparent.colorStatusbar(LeadsYourConnectionDetails.this);

        normalFont = Typeface.createFromAsset(getAssets(), "Font/Gotham-Book.otf");
        this.yourconnectionlist = (ListView) findViewById(R.id.your_connection_list);
        this.title = (TextView) findViewById(R.id.title);
        this.connectionname = (TextView) findViewById(R.id.connectionname);
        userTitle = getIntent().getStringExtra("connectiontitle");
        if (userTitle.equals("none")) {
            title.setText("");
        } else
            title.setText(userTitle);
        connectionname.setText(getIntent().getStringExtra("connectionname"));
        title.setTypeface(normalFont);
        connectionname.setTypeface(normalFont);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("yourConnectionpossibleto");
        //noinspection unchecked,unchecked
        Connectionpossibleto = (ArrayList<YourConnectionpossibleto>) args.getSerializable("yourConnectionpossibletos");
        /*for (int i = 0; i < Connectionpossibleto.size(); i++) {
            String shortBio = Connectionpossibleto.get(i).getShortBio().toString();
            String name = Connectionpossibleto.get(i).getHandle().toString();
            int count = Connectionpossibleto.get(i).getCount();

            yourConnectionpossibletos.add(new YourConnectionpossibleto(name, count, shortBio));
        }*/


        // Traverse through the first list
     /*   for (YourConnectionpossibleto element : ConnectionpossibletoArrayList) {

            // If this element is not present in newList
            // then add it
            if (!Connectionpossibleto.contains(element)) {

                Connectionpossibleto.add(element);
            }
        }
*/
      /*  for (int i = 0; i < ConnectionpossibletoArrayList.size(); i++) {
            if(Connectionpossibleto.size()>0)
            {
                if(!Connectionpossibleto.get(i-1).getHandle().equals(ConnectionpossibletoArrayList.get(i).getHandle()))
                    Connectionpossibleto.add(ConnectionpossibletoArrayList.get(i));
            }
            else
                Connectionpossibleto.add(ConnectionpossibletoArrayList.get(i));

        }*/
      /*  Set<YourConnectionpossibleto> s = new HashSet<>(ConnectionpossibletoArrayList);
        Set<YourConnectionpossibleto> items = new HashSet<YourConnectionpossibleto>();
        Set<YourConnectionpossibleto> duplicates = new HashSet<YourConnectionpossibleto>();
        for (YourConnectionpossibleto item : ConnectionpossibletoArrayList)
        {
            if (items.equals(item))
            {
                duplicates.add(item);
            }
            else {
                items.add(item);
            }
        }*/

       /* for (int i = 0; i < Connectionpossibleto.size() ; i++) {
            if(Connectionpossibleto.get(i).getHandle()==null)
                Connectionpossibleto.remove(i);
        }*/


        yourConnectionpossibletoAdapter = new YourConnectionpossibletoAdapter(getApplicationContext(), Connectionpossibleto);
        yourconnectionlist.setAdapter(yourConnectionpossibletoAdapter);

        yourconnectionlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String string = getIntent().getStringExtra("connectionname");
                String string1 = getIntent().getStringExtra("connectionname");
                Intent intent = new Intent(getApplicationContext(), GetInteroduceToActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("connectionname", getIntent().getStringExtra("connectionname"));
                intent.putExtra("handle", Connectionpossibleto.get(i).getHandle());

                startActivity(intent);
            }
        });

    }
}
