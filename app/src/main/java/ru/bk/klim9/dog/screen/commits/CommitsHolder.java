package ru.bk.klim9.dog.screen.commits;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.content.CommitResponse;
import ru.bk.klim9.dog.content.Repository;

/**
 * @author Ivan
 */

public class CommitsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.author)
    TextView mAuthor;

    @BindView(R.id.commitText)
    TextView mComment;

    public CommitsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull CommitResponse response) {
        mAuthor.setText(response.getCommit().getAuthor().getAuthorName());
        mComment.setText(response.getCommit().getMessage());
    }
}
