package com.example.bookie.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Neelabh Anand on 08/01/21.
 */
public class ApiClient {
    private static final String BASE_URL = "https://www.googleapis.com/books/";
    private static ApiClient instance;

    public static synchronized ApiClient getInstance() {
        if (instance == null) instance = new ApiClient();
        return instance;
    }

    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
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