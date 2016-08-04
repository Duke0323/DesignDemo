package io.github.duke0323.designdemo.Model;

/**
 * Created by ${Duke} on 2016/7/27.
 */
public class Contributor {
   public String login;
   public int contributions;

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
