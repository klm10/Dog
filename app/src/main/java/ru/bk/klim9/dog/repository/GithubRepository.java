package ru.bk.klim9.dog.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.bk.klim9.dog.content.Authorization;
import ru.bk.klim9.dog.content.CommitResponse;
import ru.bk.klim9.dog.content.Repository;
import rx.Observable;

/**
 * @author Ivan
 */
public interface GithubRepository {

    @NonNull
    Observable<List<CommitResponse>> commits(String user, String repo);

    @NonNull
    Observable<List<Repository>> repositories();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);
}
