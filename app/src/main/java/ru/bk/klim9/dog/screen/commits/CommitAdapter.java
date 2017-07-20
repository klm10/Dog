package ru.bk.klim9.dog.screen.commits;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.content.CommitResponse;
import ru.bk.klim9.dog.screen.repositories.RepositoryHolder;
import ru.bk.klim9.dog.widget.BaseAdapter;

/**
 * @author Ivan
 */

public class CommitAdapter extends BaseAdapter<CommitsHolder, CommitResponse>{

    public CommitAdapter(@NonNull List<CommitResponse> items){
        super(items);
    }

    @Override
    public CommitsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommitsHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commit, parent, false));
    }

    @Override
    public void onBindViewHolder(CommitsHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        CommitResponse response = getItem(position);
        holder.bind(response);
    }
}
