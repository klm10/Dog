package ru.bk.klim9.dog.screen.repositories;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.content.Repository;
import ru.bk.klim9.dog.widget.BaseAdapter;

/**
 * @author Ivan
 */
public class RepositoriesAdapter extends BaseAdapter<RepositoryHolder, Repository> {

    public RepositoriesAdapter(@NonNull List<Repository> items) {
        super(items);
    }

    @Override
    public RepositoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(RepositoryHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Repository repository = getItem(position);
        holder.bind(repository);
    }

}
