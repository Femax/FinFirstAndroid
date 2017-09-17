package ru.fedosov.opengifityhack.ui.view;

public interface GuestLoginView  {
    public void onGuestLoginResult(String imei);

    void showError(String message);
}
