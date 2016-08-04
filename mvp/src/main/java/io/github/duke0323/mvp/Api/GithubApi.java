package io.github.duke0323.mvp.Api;

import java.util.List;

import io.github.duke0323.mvp.model.Contributor;
import io.github.duke0323.mvp.service.GithubService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 *
 * Created by ${Duke} on 2016/7/27.
 */
public class GithubApi {
    public static Observable<List<Contributor>> getContributors(String owner, String repo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GithubService service = retrofit.create(GithubService.class);
        return service.repoContributors(owner, repo);
    }
}
