package com.example.shiode.editmasterscorelistapp.view;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.Typed3EpoxyController;
import com.example.shiode.editmasterscorelistapp.ProgressBarItemBindingModel_;
import com.example.shiode.editmasterscorelistapp.ScoreItemBindingModel_;
import com.example.shiode.editmasterscorelistapp.model.Score;

import java.util.List;

public class ScoreListController extends Typed3EpoxyController<List<Score>, Boolean, Boolean> {
    @AutoModel
    ProgressBarItemBindingModel_ progressBarItemBindingModel;

    @Override
    public void buildModels(List<Score> list, Boolean isLoading, Boolean isRefreshing) {
        if (list != null) {
            for (Score score : list) {
                new ScoreItemBindingModel_().score(score).id(score.getId()).addTo(this);
            }
        }

        if (isLoading != null && isRefreshing != null) {
            progressBarItemBindingModel.addIf(isLoading && !isRefreshing, this);
        }
    }
}
