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
            notifyItemRangeInserted(0, list.size());
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtilCallback(this.list, list));
        this.list = list;
        result.dispatchUpdatesTo(this);
    }

    public class DiffUtilCallback extends DiffUtil.Callback {
        public List<Score> oldList;
        public List<Score> newList;

        public DiffUtilCallback(List<Score> oldList, List<Score> newList) {
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
