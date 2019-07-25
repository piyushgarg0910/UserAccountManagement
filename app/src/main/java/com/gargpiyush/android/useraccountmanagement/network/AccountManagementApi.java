package com.gargpiyush.android.useraccountmanagement.network;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Header;

/**
 * Created by Piyush Garg
 * on 7/24/2019
 * at 14:51.
 */
public class AccountManagementApi {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://dev.refinemirror.com/api/v1/";

    public static Retrofit getRetrofitInstance(Context context){
        if(retrofit == null){

            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                    .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request= chain.request();
                    if(request.method().equals("POST")){
                        Log.e("Post: ","request");
                    }
                    return chain.proceed(request);
                }
            }).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
