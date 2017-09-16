package ru.fedosov.opengifityhack.ui.presenter;

import java.util.List;

import retrofit2.Response;
import ru.fedosov.opengifityhack.client.RestClient;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.view.NewPortfolioView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Максим on 16.09.2017.
 */

public class NewPortfolioPresenter {
    NewPortfolioView mNewPortfolioPresenter;

    public NewPortfolioPresenter(NewPortfolioView mNewPortfolioPresenter) {
        this.mNewPortfolioPresenter = mNewPortfolioPresenter;
    }


    public void createPortfolio() {
        RestClient.getInstance().createPortfolio()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mNewPortfolioPresenter.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        mNewPortfolioPresenter.onCreateResult(stringResponse.isSuccessful());
                    }
                });
    }
}
