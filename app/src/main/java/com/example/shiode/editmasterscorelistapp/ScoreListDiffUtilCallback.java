package com.example.shiode.editmasterscorelistapp;

import android.support.v7.util.DiffUtil;

import java.util.List;

class ScoreListDiffUtilCallback extends DiffUtil.Callback {
    public List<Score> oldList;
    public List<Score> newList;

    public ScoreListDiffUtilCallback(List<Score> oldList, List<Score> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
