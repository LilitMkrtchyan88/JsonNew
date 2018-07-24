package com.example.lilit.jsonnew;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    //@POST("surveys")
    @POST("/users/login")
    Call<Void> setSurveys(@Body RequestBody json);
}
