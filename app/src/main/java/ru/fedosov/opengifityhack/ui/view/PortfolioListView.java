package ru.fedosov.opengifityhack.ui.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.fedosov.opengifityhack.client.model.Portfolio;

public interface PortfolioListView {
    public void onGetPortfoioResult(List<Portfolio> portfolios);

    void showError(String message);
}
