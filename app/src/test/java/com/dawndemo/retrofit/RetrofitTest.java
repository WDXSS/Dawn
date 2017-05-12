package com.dawndemo.retrofit;

import com.dawndemo.retrofit.bean.ContributorBean;
import com.dawndemo.retrofit.service.GitService;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zc on 2017/5/8.
 */

public class RetrofitTest {

    @Test
    public void getTest() throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        GitService gitService = retrofit.create(GitService.class);
        Call<List<ContributorBean>> call = gitService.contributors("fs_opensource", "android-boilerplate");
        // Fetch and print a list of the contributors to the library.
        //call对象有excute()和enqueue()方法，分别为同步和异步进行网络请求
        List<ContributorBean> contributors = call.execute().body();

//        for (ContributorBean contributor : contributors) {
//            System.out.println(contributor.login + " (" + contributor.contributions + ")");
//        }
    }
}
