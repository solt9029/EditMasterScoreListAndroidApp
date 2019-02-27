package com.example.shiode.editmasterscorelistapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
@Singleton
public class AppModule {
    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
