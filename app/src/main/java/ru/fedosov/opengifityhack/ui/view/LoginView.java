package ru.fedosov.opengifityhack.ui.view;

import com.arellomobile.mvp.MvpView;

public interface LoginView extends MvpView {
    public void onLoginResult(boolean success);
}
