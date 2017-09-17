package ru.fedosov.opengifityhack.ui.view;

public interface CreatePortfolioView {
    void onCreateResult(boolean success);

    void showError(String message);
}
