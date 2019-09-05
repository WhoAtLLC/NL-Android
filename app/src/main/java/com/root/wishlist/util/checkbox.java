package com.root.wishlist.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.root.wishlist.R;


public class checkbox extends CheckBox {



    public checkbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setButtonDrawable(new StateListDrawable());
    }
    @Override
    public void setChecked(boolean t){
        if(t)
        {
            this.setBackgroundResource(R.drawable.select_check);
        }
        else
        {
            this.setBackgroundResource(R.drawable.select_check);
        }
        super.setChecked(t);
    }
}