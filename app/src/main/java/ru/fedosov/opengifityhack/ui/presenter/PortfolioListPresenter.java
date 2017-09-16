package ru.fedosov.opengifityhack.ui.presenter;

import java.util.List;

import retrofit2.Response;
import ru.fedosov.opengifityhack.R;
import ru.fedosov.opengifityhack.client.RestClient;
import ru.fedosov.opengifityhack.client.User;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.view.GuestLoginView;
import ru.fedosov.opengifityhack.ui.view.PortfolioListView;
import ru.fedosov.opengifityhack.utils.PrefUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Максим on 16.09.2017.
 */
public class PortfolioListPresenter {
    private PortfolioListView mPortfolioListView;

    public PortfolioListPresenter(PortfolioListView portfolioListView) {
        this.mPortfolioListView = portfolioListView;
    }

    public void getPortfolios() {
        RestClient.getInstance().getPortfolios(PrefUtils.getString(R.string.pref_user_id))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<Portfolio>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mPortfolioListView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<List<Portfolio>> listResponse) {
                        mPortfolioListView.onGetPortfoioResult(listResponse.body());
                    }
                });
    }
}
