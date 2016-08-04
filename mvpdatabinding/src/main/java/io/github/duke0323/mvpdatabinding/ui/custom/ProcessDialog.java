package io.github.duke0323.mvpdatabinding.ui.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.duke0323.mvpdatabinding.R;

/**
 * Created by ${Duke} on 2016/8/4.
 */
public class ProcessDialog extends Dialog {
    private android.widget.ImageView loadingiv;
    private android.widget.TextView loadingtv;

    public ProcessDialog(Context context) {
        super(context, R.style.transparent_Dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_dialog);
        this.loadingtv = (TextView) findViewById(R.id.messagetv);
        this.loadingiv = (ImageView) findViewById(R.id.loadingimg);
    }
    public void showMessage(String message){
        try {
            super.show();
            if(!TextUtils.isEmpty(message)) {
                loadingtv.setText(message);
                loadingtv.setVisibility(View.VISIBLE);
            }else{
                loadingtv.setText("");
                loadingtv.setVisibility(View.INVISIBLE);
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
