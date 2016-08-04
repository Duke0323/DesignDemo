package io.github.duke0323.mvvm.service;

import java.util.List;

import io.github.duke0323.mvvm.viewmodel.Contributor;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * retrofit
 * Created by ${Duke} on 2016/8/3.
 */
public interface GithubService {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
      @Path("owner") String owner,
      @Path("repo") String repo
    );
}
