package io.github.duke0323.mvp.service;

import java.util.List;

import io.github.duke0323.mvp.model.Contributor;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${Duke} on 2016/7/27.
 */
public interface GithubService {
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
