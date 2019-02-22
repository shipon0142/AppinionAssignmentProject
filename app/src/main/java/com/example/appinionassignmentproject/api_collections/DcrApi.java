package com.example.appinionassignmentproject.api_collections;

import com.example.appinionassignmentproject.models.Dcrclass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DcrApi {
    @GET("data.json")
    Call<Dcrclass> getDcr();
}
