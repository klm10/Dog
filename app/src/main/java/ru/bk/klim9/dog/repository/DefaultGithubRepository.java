package ru.bk.klim9.dog.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import ru.bk.klim9.dog.api.ApiFactory;
import ru.bk.klim9.dog.content.Authorization;
import ru.bk.klim9.dog.content.CommitResponse;
import ru.bk.klim9.dog.content.Repository;
import ru.bk.klim9.dog.utils.AuthorizationUtils;
import ru.bk.klim9.dog.utils.PreferenceUtils;
import rx.Observable;

/**
 * @author Ivan
 */
public class DefaultGithubRepository implements GithubRepository {

    @NonNull
    @Override
    public Observable<List<CommitResponse>> commits(String user, String repo) {
        return ApiFactory.getGithubService()
                .commits(user, repo)
                .flatMap(commitResponses -> {
                    for (CommitResponse commitResponse: commitResponses){
                        commitResponse.setRepo(repo);
                    }
                    return Observable.just(commitResponses);
                })
                .flatMap(commitResponses -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        RealmResults<CommitResponse> responses = realm.where(CommitResponse.class)
                                .equalTo("repo", repo)
                                .findAll();
                        if (responses.size() > 0){
                            responses.deleteAllFromRealm();
                        }
//                        realm.delete(CommitResponse.class);
                        realm.insert(commitResponses);
                    });
                    return Observable.just(commitResponses);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<CommitResponse> commitResponses = realm.where(CommitResponse.class)
                            .equalTo("repo", repo)
                            .findAll();
                    return Observable.just(realm.copyFromRealm(commitResponses));
                })
                .compose(RxUtils.async());


    }

    @NonNull
    @Override
    public Observable<List<Repository>> repositories() {
        return ApiFactory.getGithubService()
                .repositories()
                .flatMap(repositories -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(Repository.class);
                        realm.insert(repositories);
                    });
                    return Observable.just(repositories);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Repository> repositories = realm.where(Repository.class).findAll();
                    return Observable.just(realm.copyFromRealm(repositories));
                })
                .compose(RxUtils.async());
    }

    @NonNull
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
        String authorizationString = AuthorizationUtils.createAuthorizationString(login, password);
        return ApiFactory.getGithubService()
                .authorize(authorizationString, AuthorizationUtils.createAuthorizationParam())
                .flatMap(authorization -> {
                    PreferenceUtils.saveToken(authorization.getToken());
                    PreferenceUtils.saveUserName(login);
                    ApiFactory.recreate();
                    return Observable.just(authorization);
                })
                .compose(RxUtils.async());
    }
}
