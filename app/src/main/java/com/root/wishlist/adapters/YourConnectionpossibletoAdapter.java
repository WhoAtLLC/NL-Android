package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.pojo.leads.YourConnectionpossibleto;
import com.root.wishlist.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class YourConnectionpossibletoAdapter extends BaseAdapter {

    List<YourConnectionpossibleto> yourConnectionpossibletos;
    Context context;
    private android.widget.TextView connectionto;
    private android.widget.TextView count;
    private android.widget.ImageView forwardarror;
    private de.hdodenhof.circleimageview.CircleImageView userprofile;
    private TextView shortbio;
    Typeface normalFont;

    public YourConnectionpossibletoAdapter(Context context, List<YourConnectionpossibleto> yourConnectionpossibletos) {
        this.context = context;
        this.yourConnectionpossibletos = yourConnectionpossibletos;
    }

    @Override
    public int getCount() {
        return yourConnectionpossibletos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yourconnectionpossibletos, viewGroup, false));
        this.shortbio = (TextView) view.findViewById(R.id.shortbio);
        this.userprofile = (CircleImageView) view.findViewById(R.id.user_profile);
        this.forwardarror = (ImageView) view.findViewById(R.id.forward_arror);
        this.count = (TextView) view.findViewById(R.id.count);
        this.connectionto = (TextView) view.findViewById(R.id.connectionto);
        String sortbio = yourConnectionpossibletos.get(i).getShortBio();
        if (sortbio == null)
            shortbio.setVisibility(View.GONE);
        else shortbio.setText(sortbio);
        if (yourConnectionpossibletos.get(i).getHandle()!= null) {
            Glide.with(context).load("http://wishlist.operislabs.com/api/v1/profile/" + yourConnectionpossibletos.get(i).getHandle().toString() + "/avatar/").error(R.drawable.sigup_profile_icon).placeholder(R.drawable.sigup_profile_icon).dontAnimate().into(userprofile);
            connectionto.setText(yourConnectionpossibletos.get(i).getHandle().toString());
        }
        count.setText("" + yourConnectionpossibletos.get(i).getCount());
        normalFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        shortbio.setTypeface(normalFont);
        count.setTypeface(normalFont);
        connectionto.setTypeface(normalFont);

        return view;
    }
}
