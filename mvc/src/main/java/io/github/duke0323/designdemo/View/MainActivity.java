package io.github.duke0323.designdemo.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.github.duke0323.designdemo.Api.GithubApi;
import io.github.duke0323.designdemo.Model.Contributor;
import io.github.duke0323.designdemo.R;
import io.github.duke0323.designdemo.custom.ProcessDialog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button get;
    private Button change;
    private android.widget.TextView tv;
    private Contributor contributor = new Contributor();
    private Subscriber<Contributor> mContributorSubscriber = new Subscriber<Contributor>() {
        @Override
        public void onStart() {
            super.onStart();
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
            MainActivity.this.contributor = contributor;
            tv.setText(contributor.login);
            dismissProgress();
        }
    };
    private ProcessDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contributor.login = "duke0323";
                tv.setText(contributor.login);
            }
        });
        this.get = (Button) findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTopContributor("square", "retrofit");
            }
        });
        this.tv = (TextView) findViewById(R.id.tv);
    }

    public void getTopContributor(String owner, String repo) {
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

    public void showProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.showMessage("正在加载....");
        mDialog.show();
    }

    public void dismissProgress() {
        if (mDialog == null) {
            mDialog = new ProcessDialog(this);
        }
        mDialog.dismiss();
    }
}
