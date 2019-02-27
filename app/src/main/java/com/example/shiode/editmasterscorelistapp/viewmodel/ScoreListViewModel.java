package com.example.shiode.editmasterscorelistapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.shiode.editmasterscorelistapp.di.AppApplication;
import com.example.shiode.editmasterscorelistapp.model.Score;
import com.example.shiode.editmasterscorelistapp.service.ScoreService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ScoreListViewModel extends ViewModel {
    public MutableLiveData<List<Score>> scoreList = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Inject
    ScoreService service;

    public ScoreListViewModel() {
        AppApplication.getApplication().getComponent().inject(this);
        isLoading.setValue(false);
        loadScoreTimeline(null);
    }

    public void onRefresh() {
        scoreList.setValue(null);
        loadScoreTimeline(null);
    }

    public void loadMoreScoreTimeline() {
        Integer maxId = null;
        List<Score> list = scoreList.getValue();
        if (list != null) {
            maxId = list.get(list.size() - 1).getId() - 1;
        }
        loadScoreTimeline(maxId);
    }

    private void loadScoreTimeline(Integer maxId) {
        if (isLoading.getValue() != null && isLoading.getValue()) {
            return;
        }
        isLoading.setValue(true);

        Disposable disposable = service.getScoreTimeline(null, maxId, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onSuccess,
                        throwable -> isLoading.setValue(false)
                );
        compositeDisposable.add(disposable);
    }

    private void onSuccess(List<Score> result) {
        isLoading.setValue(false);
        List<Score> newList = new ArrayList<>();
        if (scoreList.getValue() != null) {
            newList.addAll(scoreList.getValue());
        }
        newList.addAll(result);
        scoreList.setValue(newList);
    }

    @Override
    public void onCleared() {
        compositeDisposable.clear();
    }
}
