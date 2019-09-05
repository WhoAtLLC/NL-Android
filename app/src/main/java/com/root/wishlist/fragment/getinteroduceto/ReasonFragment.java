package com.root.wishlist.fragment.getinteroduceto;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.root.wishlist.activities.leads.MyIntroRequestActivity;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.util.globalValues.WishlistProgressDialog;
import com.root.wishlist.util.globalValues.coachmark.YourConnectionCoachmark;
import com.root.wishlist.presentation.leads.ReasonComapnyPresentation;
import com.root.wishlist.presentation.leads.ReasonCompanyInterface;
import com.root.wishlist.R;
import com.root.wishlist.util.MybusinessEdittext;
import com.root.wishlist.view.Reasonview;
import com.root.wishlist.interfaces.NetworkInterface;


public class ReasonFragment extends Fragment implements Reasonview, View.OnClickListener {

    ReasonCompanyInterface reasonCompanyInterface;
    SharedDatabase sharedDatabase;
    int counter = 0;
    boolean fill = false;
    private TextView whydoyouwant;
    private TextView cleartxt;
    private TextView countertxt;
    private TextView howyoucanhelp;
    private TextView counter2txt;
    public static MybusinessEdittext reasonmessage;
    public static MybusinessEdittext mybusinessdiscuss;
    WishlistProgressDialog wishlistProgressDialog;
    Typeface normalFont;
    String handle, connectionName;

    public ReasonFragment(boolean fill, String handle, String connectionName) {
        this.fill = fill;
        this.handle = handle;
        this.connectionName = connectionName;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        sharedDatabase = new SharedDatabase(context);
        wishlistProgressDialog = new WishlistProgressDialog(context);
        reasonCompanyInterface = new ReasonComapnyPresentation(ReasonFragment.this, getContext());
        wishlistProgressDialog.dialogShow();
        sharedDatabase = new SharedDatabase(getContext());
        if (!sharedDatabase.getMyintro()) {
            new YourConnectionCoachmark(getActivity()).validationdialog("myinterorequest", true);
        }
        reasonCompanyInterface.setReason();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reason, container, false);
        mybusinessdiscuss = (MybusinessEdittext) view.findViewById(R.id.my_business_discuss);
        reasonmessage = (MybusinessEdittext) view.findViewById(R.id.reason_message);
        this.counter2txt = (TextView) view.findViewById(R.id.counter2_txt);
        this.howyoucanhelp = (TextView) view.findViewById(R.id.how_you_can_help);
        this.countertxt = (TextView) view.findViewById(R.id.counter_txt);
        this.cleartxt = (TextView) view.findViewById(R.id.clear_txt);
        this.whydoyouwant = (TextView) view.findViewById(R.id.why_do_you_want);
        normalFont = Typeface.createFromAsset(getContext().getAssets(), "Font/Gotham-Book.otf");


        mybusinessdiscuss.setTypeface(normalFont);
        reasonmessage.setTypeface(normalFont);
        counter2txt.setTypeface(normalFont);
        howyoucanhelp.setTypeface(normalFont);
        countertxt.setTypeface(normalFont);
        cleartxt.setTypeface(normalFont);
        whydoyouwant.setTypeface(normalFont);

        cleartxt.setOnClickListener(this);
        if (connectionName.contains("")) {
            connectionName = connectionName.substring(0, connectionName.lastIndexOf(""));
        }
        mybusinessdiscuss.setHint("First, describe reason for the intro and\nwhat you're looking to " + connectionName + " accomplish");

        //
        reasonmessage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyIntroRequestActivity.requesttobtn.setVisibility(View.GONE);
                return false;
            }
        });
        mybusinessdiscuss.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MyIntroRequestActivity.requesttobtn.setVisibility(View.GONE);
                return false;
            }
        });
        reasonmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reasonmessage.setBackgroundResource(R.drawable.unselect_edittext_border_validation);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mybusinessdiscuss.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                counter = charSequence.length();
                if (count <= 0) {
                    cleartxt.setVisibility(View.INVISIBLE);
                } else cleartxt.setVisibility(View.VISIBLE);
                if (counter <= 500) {
                    countertxt.setText("" + (500 - counter));
                    mybusinessdiscuss.setBackgroundResource(R.drawable.unselect_edittext_border_validation);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reasonmessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                counter = charSequence.length();
                if (counter <= 500) {
                    counter2txt.setText("" + (500 - counter));
                    reasonmessage.setBackgroundResource(R.drawable.unselect_edittext_border_validation);
                    ReasonMessage reasonMessage = (ReasonMessage) getActivity();
                    reasonMessage.resonMessage(String.valueOf(charSequence), mybusinessdiscuss.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reasonmessage.setKeyImeChangeListener(new MybusinessEdittext.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    MyIntroRequestActivity.requesttobtn.setVisibility(View.VISIBLE);
                }
            }
        });
        mybusinessdiscuss.setKeyImeChangeListener(new MybusinessEdittext.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    MyIntroRequestActivity.requesttobtn.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @Override
    public void networkMessage(String message) {
        wishlistProgressDialog.dismissDialog();
        NetworkInterface networkInterface = (NetworkInterface) getContext();
        try {
            networkInterface.connectionMessage(message);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setReasonMessage(String username, String businessDiscuss, String additionalBusiness) {
        wishlistProgressDialog.dismissDialog();
        reasonmessage.setHint("Second, after reviewing the interest of " + handle + ", suggest how you can help them return for helping you.");
        mybusinessdiscuss.setText("" + businessDiscuss + "\n\n\n" + additionalBusiness);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.clear_txt) {
            mybusinessdiscuss.setText("");
        }
    }

    public interface ReasonMessage {
        void resonMessage(String reason, String myBusinessDiscuss);
    }


}
