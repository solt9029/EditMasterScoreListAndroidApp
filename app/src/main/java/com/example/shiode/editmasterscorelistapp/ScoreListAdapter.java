package com.example.shiode.editmasterscorelistapp;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.shiode.editmasterscorelistapp.databinding.ScoreItemBinding;

import java.util.List;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreItemViewHolder> {
    public List<Score> list;

    public void setList(List<Score> list) {
        if (this.list == null) {
            this.list = list;
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ScoreListDiffUtilCallback(this.list, list));
        this.list = list;
        result.dispatchUpdatesTo(this);
    }

    @Override
    public ScoreItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ScoreItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.score_item, parent, false);
        ScoreItemViewHolder holder = new ScoreItemViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ScoreItemViewHolder holder, int position) {
        Score score = list.get(position);
        holder.binding.setScore(score);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
