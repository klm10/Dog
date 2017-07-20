package ru.bk.klim9.dog.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * @author Ivan
 */
public final class RepositoryProvider {

    private static GithubRepository sGithubRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static GithubRepository provideGithubRepository() {
        if (sGithubRepository == null) {
            sGithubRepository = new DefaultGithubRepository();
        }
        return sGithubRepository;
    }

    public static void setGithubRepository(@NonNull GithubRepository githubRepository) {
        sGithubRepository = githubRepository;
    }

    @MainThread
    public static void init() {
        sGithubRepository = new DefaultGithubRepository();
    }
}
