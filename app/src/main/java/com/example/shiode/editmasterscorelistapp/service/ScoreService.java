package com.example.shiode.editmasterscorelistapp.service;

import com.example.shiode.editmasterscorelistapp.model.Score;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ScoreService {
    String BASE_URL = "http://editmasterapi.solt9029.com";

    @GET("scores/{id}")
    Single<Score> getScore(@Path("id") Integer id);

    @GET("scores/timeline")
    Single<List<Score>> getScoreTimeline(@Query("count") Integer count, @Query("max_id") Integer maxId, @Query("since_id") Integer sinceId);
}