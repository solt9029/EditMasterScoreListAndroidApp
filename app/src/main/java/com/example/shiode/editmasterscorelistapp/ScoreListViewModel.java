package com.example.shiode.editmasterscorelistapp;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScoreListViewModel extends ViewModel {
    public MutableLiveData<List<Score>> scoreList = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>(true);
    public ObservableField<Boolean> isError = new ObservableField<>(false);
    private ScoreService service;

    public ScoreListViewModel() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ScoreService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(ScoreService.class);
        fetchScoreTimeline();
    }

//    public int getScoreListSize() {
//        if (scoreList.getValue() == null) {
//            return 0;
//        }
//        return scoreList.getValue().size();
//    }

    @BindingAdapter("bind:youtube_image")
    public static void setYoutubeImage(ImageView view, String videoId) {
        String url = "http://i.ytimg.com/vi/" + videoId + "/mqdefault.jpg";
        Glide.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter("bind:created_at")
    public static void setCreatedAt(TextView view, String date) {
        view.setText("created at " + date);
    }

    public void fetchScoreTimeline() {
        isLoading.set(true);
        isError.set(false);

        Integer maxId = null;
        if (scoreList.getValue() != null) {
            List<Score> list = scoreList.getValue();
            maxId = list.get(list.size() - 1).getId() - 1;
        }

        service.getScoreTimeline(null, maxId, null).enqueue(new Callback<List<Score>>() {
            @Override
            public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                isLoading.set(false);
                if (!response.isSuccessful()) {
                    isError.set(true);
                    return;
                }

                List<Score> result = response.body();
                if (scoreList.getValue() == null) {
                    scoreList.postValue(result);
                    return;
                }
                List<Score> value = scoreList.getValue();
                value.addAll(result);
                scoreList.postValue(value);
            }

            @Override
            public void onFailure(Call<List<Score>> call, Throwable throwable) {
                isLoading.set(false);
                isError.set(true);
            }
        });
    }

}
