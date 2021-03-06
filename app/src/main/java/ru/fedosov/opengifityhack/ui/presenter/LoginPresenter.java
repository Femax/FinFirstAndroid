package ru.fedosov.opengifityhack.ui.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import ru.fedosov.opengifityhack.ui.activity.LoginActivity;
import ru.fedosov.opengifityhack.ui.view.LoginView;

public class LoginPresenter extends MvpPresenter<LoginView> {

    public LoginPresenter() {
    }

    public void runLogin(String email, String password) {
        getViewState().onLoginResult(true);
    }
}
