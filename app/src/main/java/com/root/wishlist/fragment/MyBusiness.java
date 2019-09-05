package com.root.wishlist.fragment;

import android.content.Context;
import android.graphics.Color;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.root.wishlist.activities.registration.YourBusinessInterest;
import com.root.wishlist.database.SharedDatabase;
import com.root.wishlist.R;
import com.root.wishlist.util.MybusinessEdittext;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class MyBusiness extends Fragment {


    private android.widget.TextView mybusinesstitle1;
    private android.widget.TextView textcounter1txt;
    private android.widget.TextView mybusinesstitle2;
    private android.widget.TextView textcounter2txt;

    int counter = 0;
    SharedDatabase sharedDatabase;
    public static com.root.wishlist.util.MybusinessEdittext message2edt;
    public static com.root.wishlist.util.MybusinessEdittext message1ext;
    private InputMethodManager imm;
    private View currentFocusedView;



    public MyBusiness() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sharedDatabase = new SharedDatabase(context);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_business, container, false);

        intial(view);

       /* currentFocusedView = getActivity().getCurrentFocus();
        imm.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);*/

        message1ext.setText(sharedDatabase.getMyBusinessMessage());

        message1ext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                message1ext.setFocusableInTouchMode(true);
                YourBusinessInterest.companyArrow.setVisibility(View.GONE);
                return false;
            }
        });
        message1ext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                counter = charSequence.length();
                if (counter <= 200) {
                    sharedDatabase.setMyBusinessMessage(charSequence);
                    textcounter1txt.setText("" + (200 - counter));
                    message1ext.setBackgroundResource(R.drawable.unselect_edittext_border_validation);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        message2edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                counter = charSequence.length();
                if (counter <= 200) {
                    sharedDatabase.myBusinessAdditional(charSequence);
                    textcounter2txt.setText("" + (200 - counter));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        message2edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YourBusinessInterest.companyArrow.setVisibility(View.GONE);
            }
        });
        message2edt.setKeyImeChangeListener(new MybusinessEdittext.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    YourBusinessInterest.companyArrow.setVisibility(View.VISIBLE);

                }
            }
        });
        message1ext.setKeyImeChangeListener(new MybusinessEdittext.KeyImeChange() {
            @Override
            public void onKeyIme(int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
                    YourBusinessInterest.companyArrow.setVisibility(View.VISIBLE);

                }
            }
        });
        message2edt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                message2edt.setFocusableInTouchMode(true);
                YourBusinessInterest.companyArrow.setVisibility(View.GONE);
                return false;
            }
        });


        return view;
    }

    private void intial(View view) {

        message2edt = (MybusinessEdittext) view.findViewById(R.id.message2_edt);
        message1ext = (MybusinessEdittext) view.findViewById(R.id.message1_ext);
        this.textcounter2txt = (TextView) view.findViewById(R.id.textcounter2_txt);
        this.mybusinesstitle2 = (TextView) view.findViewById(R.id.my_business_title2);
        this.textcounter1txt = (TextView) view.findViewById(R.id.textcounter1_txt);
        this.mybusinesstitle1 = (TextView) view.findViewById(R.id.mybusiness_title1);
        Typeface normalFont = Typeface.createFromAsset(getActivity().getAssets(), "Font/Gotham-Book.otf");
        textcounter1txt.setTypeface(normalFont);
        mybusinesstitle2.setTypeface(normalFont);
        textcounter1txt.setTypeface(normalFont);
        mybusinesstitle1.setTypeface(normalFont);
        message1ext.setTypeface(normalFont);
        message2edt.setTypeface(normalFont);
        message1ext.setTextColor(Color.parseColor("#464B4B"));
        message2edt.setTextColor(Color.parseColor("#464B4B"));

    }


}
