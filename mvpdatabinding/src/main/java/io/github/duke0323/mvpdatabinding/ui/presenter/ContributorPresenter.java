package io.github.duke0323.mvpdatabinding.ui.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import io.github.duke0323.mvpdatabinding.api.GithubApi;
import io.github.duke0323.mvpdatabinding.ui.callback.ContributorView;
import io.github.duke0323.mvpdatabinding.viewmodel.Contributor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${Duke} on 2016/8/4.
 */
public class ContributorPresenter extends MvpBasePresenter<ContributorView> {
    private Subscriber<Contributor> mContributorSubscriber=new Subscriber<Contributor>() {
        @Override
        public void onStart() {
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
}
