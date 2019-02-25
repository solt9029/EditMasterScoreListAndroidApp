package com.example.shiode.editmasterscorelistapp.di;

import com.example.shiode.editmasterscorelistapp.viewmodel.ScoreListViewModel;

import dagger.Component;

@Component(modules = ApiModule.class)
public interface AppComponent {
    void inject(ScoreListViewModel target);
}