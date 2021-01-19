package com.example.bookie.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://www.googleapis.com/books/";
    private Retrofit mRetrofit;
    private static ApiClient sInstance;

    private ApiClient(){}
    public static synchronized ApiClient getInstance() {
        if (sInstance == null) sInstance = new ApiClient();
        return sInstance;
    }

    public <S> S createService(Class<S> serviceClass) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return mRetrofit.create(serviceClass);
    }

    private okhttp3.OkHttpClient getOkHttpClient() {
        //create a builder
        okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //create HttpLogging interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        //add interceptor to builder
        builder.addInterceptor(interceptor);
        return builder.build();
    }
}