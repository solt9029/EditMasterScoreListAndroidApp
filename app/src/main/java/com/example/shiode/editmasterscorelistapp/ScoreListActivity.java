package com.example.shiode.editmasterscorelistapp;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shiode.editmasterscorelistapp.databinding.ActivityScoreListBinding;

public class ScoreListActivity extends AppCompatActivity {
    private ActivityScoreListBinding binding;
    private ScoreListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);
        viewModel = ViewModelProviders.of(this).get(ScoreListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score_list);
        binding.setViewModel(viewModel);
    }
}
