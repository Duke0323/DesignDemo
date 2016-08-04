package io.github.duke0323.mvp.ui.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import io.github.duke0323.mvp.R;
import io.github.duke0323.mvp.model.Contributor;
import io.github.duke0323.mvp.ui.callback.ContributorView;
import io.github.duke0323.mvp.ui.custom.ProcessDialog;
import io.github.duke0323.mvp.ui.presenter.ContributorPresenter;

public class MainActivity extends MvpActivity<ContributorView, ContributorPresenter> implements ContributorView {

    private android.widget.Button get;
    private android.widget.Button change;
    private android.widget.TextView tv;
    private ProcessDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tv = (TextView) findViewById(R.id.tv);
        this.change = (Button) findViewById(R.id.change);
        this.get = (Button) findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().get("square", "retrofit");
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().change();
            }
        });
    }

    @NonNull
    @Override
    public ContributorPresenter createPresenter() {
        return new ContributorPresenter();
    }

    public void showProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.showMessage("正在加载...");
    }

    public void dismissProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.dismiss();
    }

    @Override
    public void onLoadContributorStart() {
        showProgress();
    }

    @Override
    public void onLoadContributorComplete(Contributor contributor) {
        tv.setText(contributor.toString());
        dismissProgress();
    }

    @Override
    public void onChangeContributorName(String name) {
        tv.setText(name);
    }


}
