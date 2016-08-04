package io.github.duke0323.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import io.github.duke0323.mvvm.api.GithubApi;
import io.github.duke0323.mvvm.custom.ProcessDialog;
import io.github.duke0323.mvvm.databinding.ActivityMainBinding;
import io.github.duke0323.mvvm.viewmodel.Contributor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Subscriber<Contributor> mContributorSubscriber = new Subscriber<Contributor>() {
        @Override
        public void onStart() {
            showProgress();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Contributor contributor) {
            //viewModel层注入
            mBinding.setContributor(contributor);
            dismissProgress();
        }
    };
    private ProcessDialog mDialog;
    private ActivityMainBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将xml设定view
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void get(View view) {
        getContributors("square", "retrofit");
    }

    public void change(View view) {
        if (mBinding.getContributor() != null) {
            mBinding.getContributor().setLogin("duke0323");
        }
    }

    public void showProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.showMessage("Loading");
    }

    public void dismissProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.dismiss();
    }

    public void getContributors(String owner, String repo) {
        GithubApi.getContributors(owner, repo)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<List<Contributor>, Contributor>() {
                    @Override
                    public Contributor call(List<Contributor> contributors) {
                        return contributors.get(0);
                    }
                })
                .subscribe(mContributorSubscriber);
    }
}
