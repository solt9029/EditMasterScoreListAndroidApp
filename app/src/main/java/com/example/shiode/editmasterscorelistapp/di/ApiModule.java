package com.example.shiode.editmasterscorelistapp.di;

import com.example.shiode.editmasterscorelistapp.service.ScoreService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@Singleton
public class ApiModule {
    private final String BASE_URL = "http://editmasterapi.solt9029.com";

    @Provides
    public ScoreService provideScoreService() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(ScoreService.class);
    }
}
