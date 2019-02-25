package com.example.shiode.editmasterscorelistapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.shiode.editmasterscorelistapp.di.AppApplication;
import com.example.shiode.editmasterscorelistapp.model.Score;
import com.example.shiode.editmasterscorelistapp.service.ScoreService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreListViewModel extends ViewModel {
    public MutableLiveData<List<Score>> scoreList = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    @Inject
    public ScoreService service;

    public ScoreListViewModel() {
        AppApplication.getApplication().getComponent().inject(this);
        isLoading.setValue(false);
        loadScoreTimeline();
    }

    public void onRefresh() {
        scoreList.setValue(null);
        loadScoreTimeline();
    }

    public void loadMoreScoreTimeline() {
        Integer maxId = null;
        List<Score> list = scoreList.getValue();
        if (list != null) {
            maxId = list.get(list.size() - 1).getId() - 1;
        }
        loadScoreTimeline(maxId);
    }

    public void loadScoreTimeline() {
        loadScoreTimeline(null);
    }

    public void loadScoreTimeline(Integer maxId) {
        if (isLoading.getValue() != null && isLoading.getValue()) {
            return;
        }

        isLoading.setValue(true);

        service.getScoreTimeline(null, maxId, null).enqueue(new Callback<List<Score>>() {
            @Override
            public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                isLoading.setValue(false);
                if (!response.isSuccessful()) {
                    return;
                }

                List<Score> result = response.body();
                List<Score> newList = new ArrayList<>();
                if (scoreList.getValue() != null) {
                    newList.addAll(scoreList.getValue());
                }
                if (result != null) {
                    newList.addAll(result);
                }
                scoreList.setValue(newList);
            }

            @Override
            public void onFailure(Call<List<Score>> call, Throwable throwable) {
                isLoading.setValue(false);
            }
        });
    }
}
