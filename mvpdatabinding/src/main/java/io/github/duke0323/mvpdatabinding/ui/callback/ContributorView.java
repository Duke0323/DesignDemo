package io.github.duke0323.mvpdatabinding.ui.callback;

import com.hannesdorfmann.mosby.mvp.MvpView;

import io.github.duke0323.mvpdatabinding.viewmodel.Contributor;

/**
 * Created by ${Duke} on 2016/8/4.
 */
public interface ContributorView extends MvpView {
    void onLoadContributorStart();

    void onLoadContributorComplete(Contributor topContributor);
}
