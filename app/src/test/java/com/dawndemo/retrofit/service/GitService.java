package com.dawndemo.retrofit.service;

import com.dawndemo.retrofit.bean.ContributorBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 定义一个接口，接口中的方法用注解的方式声明了Http 请求的相关参数，包括使用get方法，相关参数等
 * Created by zc on 2017/5/8.
 */

public interface GitService {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<ContributorBean>> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
