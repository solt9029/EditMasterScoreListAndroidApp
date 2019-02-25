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
    @Provides
    public ScoreService provideScoreService() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(ScoreService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(ScoreService.class);
    }
}
