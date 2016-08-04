package io.github.duke0323.mvp.ui.callback;

import com.hannesdorfmann.mosby.mvp.MvpView;

import io.github.duke0323.mvp.model.Contributor;

/**
 * (activity实现它)
 * Created by ${Duke} on 2016/7/27.
 */
public interface ContributorView extends MvpView {
    //开始获取数据
    void onLoadContributorStart();
    //获取数据成功
    void onLoadContributorComplete(Contributor contributor);
    //改变名称,功能代码
    void onChangeContributorName(String name);
}
