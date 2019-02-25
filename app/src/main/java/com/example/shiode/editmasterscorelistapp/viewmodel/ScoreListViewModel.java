package com.example.shiode.editmasterscorelistapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.shiode.editmasterscorelistapp.model.Score;
import com.example.shiode.editmasterscorelistapp.service.ScoreService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScoreListViewModel extends ViewModel {
    public MutableLiveData<List<Score>> scoreList = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private ScoreService service;

    public ScoreListViewModel() {
        isLoading.setValue(false);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ScoreService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(ScoreService.class);
        fetchScoreTimeline();
    }

    public void onRefresh() {
        scoreList.setValue(null);
        fetchScoreTimeline();
    }

    public void fetchScoreTimeline() {
        if (isLoading.getValue()) {
            return;
        }

        isLoading.setValue(true);

        Integer maxId = null;
        if (scoreList.getValue() != null) {
            List<Score> list = scoreList.getValue();
            maxId = list.get(list.size() - 1).getId() - 1;
        }

        service.getScoreTimeline(null, maxId, null).enqueue(new Callback<List<Score>>() {
            @Override
            public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                isLoading.setValue(false);
                if (!response.isSuccessful()) {
                    return;
                }

                List<Score> result = response.body();
                if (scoreList.getValue() == null) {
                    scoreList.postValue(result);
                    return;
                }

                // deep copy!
                List<Score> newList = new ArrayList<>();
                newList.addAll(scoreList.getValue());
                newList.addAll(result);
                scoreList.postValue(newList);
            }

            @Override
            public void onFailure(Call<List<Score>> call, Throwable throwable) {
                isLoading.setValue(false);
            }
        });
    }
}
