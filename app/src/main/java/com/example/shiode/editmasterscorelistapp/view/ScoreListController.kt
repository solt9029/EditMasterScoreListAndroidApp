package com.example.shiode.editmasterscorelistapp.view

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.Typed3EpoxyController
import com.example.shiode.editmasterscorelistapp.ProgressBarItemBindingModel_
import com.example.shiode.editmasterscorelistapp.ScoreItemBindingModel_
import com.example.shiode.editmasterscorelistapp.model.Score

class ScoreListController : Typed3EpoxyController<List<Score>, Boolean, Boolean>() {
    @AutoModel
    lateinit var progressBarItemBindingModel: ProgressBarItemBindingModel_

    override fun buildModels(list: List<Score>?, isLoading: Boolean?, isRefreshing: Boolean?) {
        if (list != null) {
            for (score in list) {
                ScoreItemBindingModel_().score(score).id(score.id.toLong()).addTo(this)
            }
        }

        if (isLoading != null && isRefreshing != null) {
            progressBarItemBindingModel.addIf(isLoading && !isRefreshing, this)
        }
    }
}
