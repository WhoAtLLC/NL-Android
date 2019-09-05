package com.root.wishlist.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.root.wishlist.R;


public class SearchEditText extends EditText {

    //The image we are going to use for the Clear button
    private Drawable imgCloseButton = getResources().getDrawable(R.drawable.ic_cancel_black_24dp);
    private Drawable imgSearchButton = getResources().getDrawable(android.R.drawable.ic_menu_search);

    public SearchEditText(Context context) {
        super(context);
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {

        // Set bounds of the Clear button so it will look ok
        imgCloseButton.setBounds(0, 0, imgCloseButton.getIntrinsicWidth(), imgCloseButton.getIntrinsicHeight());
        imgSearchButton.setBounds(0, 0, imgSearchButton.getIntrinsicWidth(), imgSearchButton.getIntrinsicHeight());

        // There may be initial text in the field, so we may need to display the  button
        handleClearButton();

        //if the Close image is displayed and the user remove his finger from the button, clear it. Otherwise do nothing
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                SearchEditText et = SearchEditText.this;

                if (et.getCompoundDrawables()[2] == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (event.getX() > et.getWidth() - et.getPaddingRight() - imgCloseButton.getIntrinsicWidth()) {
                    et.setText("");
                    SearchEditText.this.handleClearButton();
                }
                return false;
            }
        });

        //if text changes, take care of the button
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                SearchEditText.this.handleClearButton();
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    void handleClearButton() {
        if (this.getText().toString().equals("")) {
            // add the clear button
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgSearchButton, this.getCompoundDrawables()[3]);
        } else {
            //remove clear button
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], imgCloseButton, this.getCompoundDrawables()[3]);
        }
    }
    private KeyImeChange keyImeChangeListener;

    public void setKeyImeChangeListener(KeyImeChange listener) {
        keyImeChangeListener = listener;
    }

    public interface KeyImeChange {
        void onKeyIme(int keyCode, KeyEvent event);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyImeChangeListener != null) {
            keyImeChangeListener.onKeyIme(keyCode, event);
        }
        return false;
    }
}
