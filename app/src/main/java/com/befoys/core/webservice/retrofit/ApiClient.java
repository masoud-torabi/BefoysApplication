package com.befoys.core.webservice.retrofit;

import com.befoys.core.webservice.base.ApiBase;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){

        if(retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                  @Override
                  public Response intercept(Chain chain) throws IOException {
                      Request original = chain.request();
                      Request request = original.newBuilder()
                              //.header("WEB_TOKEN", ApiBase.WEBSERVICE_TOKEN)
                              .method(original.method(), original.body())
                              .build();

                      return chain.proceed(request);
                  }
              });

            OkHttpClient client = httpClient.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiBase.WEBSERVICE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return  retrofit;
    }
}
