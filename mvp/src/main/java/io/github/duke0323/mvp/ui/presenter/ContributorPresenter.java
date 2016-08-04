package io.github.duke0323.mvp.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import io.github.duke0323.mvp.Api.GithubApi;
import io.github.duke0323.mvp.model.Contributor;
import io.github.duke0323.mvp.ui.callback.ContributorView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Duke} on 2016/7/27.
 */
public class ContributorPresenter extends MvpBasePresenter<ContributorView> {
    private Subscriber<Contributor> mContributorSubscriber = new Subscriber<Contributor>() {
        @Override
        public void onStart() {
            super.onStart();
            ContributorView view = getView();
            if(view!=null) {
                view.onLoadContributorStart();
            }
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Contributor contributor) {
            ContributorView view = getView();
            if(view!=null) {
                view.onLoadContributorComplete(contributor);
            }
        }
    };
    public void get(String owner,String repo){
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
    public void change(){
        ContributorView view = getView();
        if(view!=null) {
            view.onChangeContributorName("duke0323");

        }
    }
}
