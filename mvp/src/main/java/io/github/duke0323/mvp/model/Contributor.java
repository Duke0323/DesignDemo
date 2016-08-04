package io.github.duke0323.mvp.model;

/**
 * Created by ${Duke} on 2016/7/27.
 */
public class Contributor {
    public String login;
    public String contributors;

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributors='" + contributors + '\'' +
                '}';
    }
}
