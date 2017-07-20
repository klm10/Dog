package ru.bk.klim9.dog.screen.repositories;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.content.Repository;
import ru.bk.klim9.dog.repository.RepositoryProvider;

/**
 * @author Ivan
 */
public class RepositoriesPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final RepositoriesView mView;

    public RepositoriesPresenter(@NonNull LifecycleHandler lifecycleHandler,
                                 @NonNull RepositoriesView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideGithubRepository()
                .repositories()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showRepositories, throwable -> mView.showError());
    }

    public void onItemClick(@NonNull Repository repository) {
        mView.showCommits(repository);
    }
}
