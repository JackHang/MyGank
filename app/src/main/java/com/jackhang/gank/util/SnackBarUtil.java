package com.jackhang.gank.util;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * snackBar替代toast
 */

public class SnackBarUtil {

    public static void show(View parent, String message) {
        Snackbar make = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout view = (Snackbar.SnackbarLayout) make.getView();
        view.setBackgroundResource(android.R.color.white);
        TextView mMessageView =
                (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        mMessageView.setTextColor(Color.BLACK);
        make.show();
    }

}
