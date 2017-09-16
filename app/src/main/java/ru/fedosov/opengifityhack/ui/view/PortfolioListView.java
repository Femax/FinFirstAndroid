package ru.fedosov.opengifityhack.ui.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.fedosov.opengifityhack.client.model.Portfolio;

/**
 * Created by Максим on 16.09.2017.
 */
public interface PortfolioListView extends MvpView {
    public void onGetPortfoioResult(List<Portfolio> portfolios);
}
