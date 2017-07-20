package ru.bk.klim9.dog.screen.commits;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.bk.klim9.dog.R;
import ru.bk.klim9.dog.repository.RepositoryProvider;
import ru.bk.klim9.dog.screen.repositories.RepositoriesView;
import rx.Observable;

/**
 * @author Ivan
 */

public class CommitPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final CommitView mView;

    public CommitPresenter(LifecycleHandler mLifecycleHandler, CommitView mView) {
        this.mLifecycleHandler = mLifecycleHandler;
        this.mView = mView;
    }

    public void init(String user, String repoName) {
        RepositoryProvider.provideGithubRepository()
                .commits(user, repoName)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showCommits, throwable -> mView.showError());
    }
}
