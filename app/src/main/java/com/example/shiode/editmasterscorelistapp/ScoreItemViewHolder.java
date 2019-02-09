package com.example.shiode.editmasterscorelistapp;

import android.support.v7.widget.RecyclerView;

import com.example.shiode.editmasterscorelistapp.databinding.ScoreItemBinding;

public class ScoreItemViewHolder extends RecyclerView.ViewHolder {
    public ScoreItemBinding binding;

    public ScoreItemViewHolder(ScoreItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
