package io.github.duke0323.mvvm.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import io.github.duke0323.mvvm.BR;

/**
 *
 * Created by ${Duke} on 2016/8/3.
 */
public class Contributor extends BaseObservable {
    private String login;
    private int contributions;

    @Bindable
    public String getLogin() {
        return login;
    }

    @Bindable
    public int getContributions() {
        return contributions;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
        notifyPropertyChanged(BR.contributions);
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
