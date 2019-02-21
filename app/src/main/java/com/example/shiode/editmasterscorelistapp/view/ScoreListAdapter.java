package com.example.shiode.editmasterscorelistapp.view;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.shiode.editmasterscorelistapp.R;
import com.example.shiode.editmasterscorelistapp.databinding.ScoreItemBinding;
import com.example.shiode.editmasterscorelistapp.model.Score;

import java.util.List;

public class ScoreListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ScoreItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.score_item, parent, false);
        ScoreItemViewHolder holder = new ScoreItemViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ScoreItemViewHolder) {
            Score score = list.get(position);
            ((ScoreItemViewHolder) holder).binding.setScore(score);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    private class ScoreItemViewHolder extends RecyclerView.ViewHolder {
        public ScoreItemBinding binding;

        public ScoreItemViewHolder(ScoreItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
