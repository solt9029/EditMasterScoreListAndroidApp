package com.example.shiode.editmasterscorelistapp;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
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
    public ObservableField<ScoreListAdapter> adapter = new ObservableField<>(new ScoreListAdapter());
    public ObservableField<Boolean> isLoading = new ObservableField<>(true);
    public ObservableField<Boolean> isError = new ObservableField<>(false);

    public ScoreListViewModel() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ScoreService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ScoreService service = retrofit.create(ScoreService.class);
        service.getScoreList().enqueue(new Callback<ScoreList>() {
            @Override
            public void onResponse(Call<ScoreList> call, Response<ScoreList> response) {
                isLoading.set(false);
                if (!response.isSuccessful()) {
                    isError.set(true);
                    return;
                }
                List<Score> scoreList = response.body().getData();
                adapter.get().setList(scoreList);
            }

            @Override
            public void onFailure(Call<ScoreList> call, Throwable throwable) {
                isLoading.set(false);
                isError.set(true);
            }

        });
    }

    @BindingAdapter("bind:youtube_image")
    public static void setYoutubeImage(ImageView view, String videoId) {
        String url = "http://i.ytimg.com/vi/" + videoId + "/mqdefault.jpg";
        Glide.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter("bind:created_at")
    public static void setCreatedAt(TextView view, String date) {
        view.setText("created at " + date);
    }

}
