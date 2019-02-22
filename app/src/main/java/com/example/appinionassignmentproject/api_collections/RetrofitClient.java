package com.example.appinionassignmentproject.api_collections;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit getRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/appinion-dev/intern-dcr-data/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
