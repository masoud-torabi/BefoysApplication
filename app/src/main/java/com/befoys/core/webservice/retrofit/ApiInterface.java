package com.befoys.core.webservice.retrofit;

import com.befoys.core.webservice.base.ApiResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET
    Call<ApiResult> get(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers);

    @POST
    Call<ApiResult> post(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers);

    @POST
    Call<ApiResult> post(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers, @Body Object obj);

    @PUT
    Call<ApiResult> put(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers);

    @PUT
    Call<ApiResult> put(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers, @Body Object obj);

    @DELETE
    Call<ApiResult> delete(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers);

    @DELETE
    Call<ApiResult> delete(@Url String url, @QueryMap Map<String, String> options, @HeaderMap Map<String, String> headers, @Body Object obj);
}
