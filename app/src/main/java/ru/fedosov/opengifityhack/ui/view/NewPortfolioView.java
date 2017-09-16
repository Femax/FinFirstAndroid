package ru.fedosov.opengifityhack.ui.view;

/**
 * Created by Максим on 16.09.2017.
 */
public interface NewPortfolioView {
    void onCreateResult(boolean success);

    void showError(String message);
}
