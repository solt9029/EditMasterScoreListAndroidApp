package com.example.shiode.editmasterscorelistapp;

import android.support.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ScoreService {
    String BASE_URL = "http://editmasterapi.solt9029.com";

    @GET("/scores")
    Call<ScoreList> getScoreList(@Query("page") Integer page, @Query("keyword") String keyword);

    @GET("scores/{id}")
    Call<Score> getScore(@Path("id") Integer id);

    @GET("scores/timeline")
    Call<List<Score>>  getScoreTimeline(@Query("count") Integer count, @Query("max_id") Integer maxId, @Query("since_id") Integer sinceId);

}