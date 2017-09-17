package ru.fedosov.opengifityhack.ui.view;

public interface NewPortfolioView {
    void onCreateResult(boolean success);

    void showError(String message);
}
