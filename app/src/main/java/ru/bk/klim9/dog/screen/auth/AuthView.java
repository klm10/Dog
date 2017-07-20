package ru.bk.klim9.dog.screen.auth;

import ru.bk.klim9.dog.screen.general.LoadingView;

/**
 * @author Ivan
 */
public interface AuthView extends LoadingView {

    void openRepositoriesScreen();

    void showLoginError();

    void showPasswordError();

}