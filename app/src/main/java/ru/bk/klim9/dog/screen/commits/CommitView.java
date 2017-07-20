package ru.bk.klim9.dog.screen.commits;

import android.support.annotation.NonNull;

import java.util.List;

import ru.bk.klim9.dog.content.CommitResponse;
import ru.bk.klim9.dog.screen.general.LoadingView;

/**
 * @author Ivan
 */

public interface CommitView extends LoadingView{

    void showCommits(@NonNull List<CommitResponse> commitResponses);

    void showError();
}
