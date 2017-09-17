package ru.fedosov.opengifityhack.ui.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;

public interface GuestLoginView  {
    public void onGuestLoginResult(boolean success);

    void showError(String message);
}
