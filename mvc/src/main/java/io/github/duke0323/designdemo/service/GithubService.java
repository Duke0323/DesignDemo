package io.github.duke0323.designdemo.service;

import java.util.List;

import io.github.duke0323.designdemo.Model.Contributor;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 网络地址Get请求
 * Created by ${Duke} on 2016/7/27.
 */
public interface GithubService {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
