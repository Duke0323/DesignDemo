package io.github.duke0323.mvpdatabinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import io.github.duke0323.mvpdatabinding.BR;

/**
 * Created by ${Duke} on 2016/8/3.
 */
public class Contributor extends BaseObservable {
    private String login;
    private int contributors;

    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public int getContributors() {
        return contributors;
    }

    public void setContributors(int contributors) {
        this.contributors = contributors;
        notifyPropertyChanged(BR.contributors);
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributors=" + contributors +
                '}';
    }
}
