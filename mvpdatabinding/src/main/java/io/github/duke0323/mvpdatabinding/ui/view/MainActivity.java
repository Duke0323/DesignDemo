package io.github.duke0323.mvpdatabinding.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import io.github.duke0323.mvpdatabinding.R;
import io.github.duke0323.mvpdatabinding.databinding.ActivityMainBinding;
import io.github.duke0323.mvpdatabinding.ui.callback.ContributorView;
import io.github.duke0323.mvpdatabinding.ui.custom.ProcessDialog;
import io.github.duke0323.mvpdatabinding.ui.presenter.ContributorPresenter;
import io.github.duke0323.mvpdatabinding.viewmodel.Contributor;

public class MainActivity extends MvpActivity<ContributorView, ContributorPresenter> implements ContributorView {

    private ActivityMainBinding mBinding;
    private ProcessDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @NonNull
    @Override
    public ContributorPresenter createPresenter() {
        return new ContributorPresenter();
    }

    public void get(View view) {
        getPresenter().get("square", "retrofit");
    }

    public void change(View view) {
        if(mBinding.getContributor()!=null) {
            mBinding.getContributor().setLogin("duke0323");
        }
    }


    @Override
    public void onLoadContributorStart() {
        showProgress();
    }

    @Override
    public void onLoadContributorComplete(Contributor topContributor) {
        mBinding.setContributor(topContributor);
        dismissProgress();
    }

    public void showProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.showMessage("loading");
    }

    public void dismissProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.dismiss();
    }
}
