package ru.bk.klim9.dog.screen.repositories;

import android.support.annotation.NonNull;

import java.util.List;

import ru.bk.klim9.dog.content.Repository;
import ru.bk.klim9.dog.screen.general.LoadingView;

/**
 * @author Ivan
 */
public interface RepositoriesView extends LoadingView {

    void showRepositories(@NonNull List<Repository> repositories);

    void showCommits(@NonNull Repository repository);

    void showError();
}
