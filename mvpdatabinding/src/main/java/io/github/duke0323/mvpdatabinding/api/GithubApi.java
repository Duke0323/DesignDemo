package io.github.duke0323.mvpdatabinding.api;

import java.util.List;

import io.github.duke0323.mvpdatabinding.service.GithubService;
import io.github.duke0323.mvpdatabinding.viewmodel.Contributor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ${Duke} on 2016/8/4.
 */
public class GithubApi {
    public static Observable<List<Contributor>> getContributors(String owner,String repo){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubService service = retrofit.create(GithubService.class);
        return service.repoContributors(owner, repo);
    }
}
