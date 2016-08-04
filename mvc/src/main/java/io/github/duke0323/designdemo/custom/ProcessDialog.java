package io.github.duke0323.designdemo.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.duke0323.designdemo.R;

/**
 * Created by ${Duke} on 2016/7/27.
 */
public class ProcessDialog extends Dialog {

    private android.widget.ImageView loadingiv;
    private android.widget.TextView messagetv;
    private android.widget.LinearLayout processll;

    public ProcessDialog(Context context) {
        super(context,R.style.transparent_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_dialog);
        this.processll = (LinearLayout) findViewById(R.id.process_ll);
        this.messagetv = (TextView) findViewById(R.id.message_tv);
        this.loadingiv = (ImageView) findViewById(R.id.loading_iv);
    }

    public void showMessage(String message) {
        try {
            super.show();
            if (!TextUtils.isEmpty(message)) {
                messagetv.setText(message);
                messagetv.setVisibility(View.VISIBLE);
            } else {
                messagetv.setText("");
                messagetv.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        loadingiv.startAnimation(animation);
    }
}
