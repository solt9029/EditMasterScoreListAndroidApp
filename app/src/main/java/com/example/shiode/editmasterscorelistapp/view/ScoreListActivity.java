package com.example.shiode.editmasterscorelistapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shiode.editmasterscorelistapp.R;
import com.example.shiode.editmasterscorelistapp.databinding.ActivityScoreListBinding;
import com.example.shiode.editmasterscorelistapp.model.Score;
import com.example.shiode.editmasterscorelistapp.viewmodel.ScoreListViewModel;

import java.util.List;

public class ScoreListActivity extends AppCompatActivity {
    private ActivityScoreListBinding binding;
    private ScoreListViewModel viewModel;
    private ScoreListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_score_list);

        adapter = new ScoreListAdapter();
        binding.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ScoreListViewModel.class);
        binding.setViewModel(viewModel);

        RecyclerView recyclerView = binding.recyclerView;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                viewModel.fetchScoreTimeline();
            }
        });

        viewModel.scoreList.observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scoreList) {
                adapter.setList(scoreList);
            }
        });
    }
}
