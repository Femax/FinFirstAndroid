package ru.fedosov.opengifityhack.ui.presenter;

import org.w3c.dom.Attr;

import retrofit2.Response;
import ru.fedosov.opengifityhack.client.RestClient;
import ru.fedosov.opengifityhack.client.model.AttrCreatePortfolio;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.ui.view.CreatePortfolioView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreatePortfolioPresenter {
    CreatePortfolioView mNewPortfolioPresenter;

    public CreatePortfolioPresenter(CreatePortfolioView mNewPortfolioPresenter) {
        this.mNewPortfolioPresenter = mNewPortfolioPresenter;
    }


    public void createPortfolio(AttrCreatePortfolio portfolio) {
        RestClient.getInstance().createPortfolio(portfolio)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mNewPortfolioPresenter.onCreateResult(true);
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {
                        mNewPortfolioPresenter.onCreateResult(stringResponse.isSuccessful());
                    }
                });
    }
}
