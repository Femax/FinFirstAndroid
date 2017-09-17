package ru.fedosov.opengifityhack.ui.presenter;

import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;

import java.util.concurrent.TimeUnit;

import retrofit2.Response;
import ru.fedosov.opengifityhack.client.RestClient;
import ru.fedosov.opengifityhack.client.User;
import ru.fedosov.opengifityhack.ui.view.GuestLoginView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GuestLoginPresenter {
    private GuestLoginView mGuestLoginView;

    public GuestLoginPresenter(GuestLoginView loginView) {
        this.mGuestLoginView = loginView;
    }

    public void guestLogin(String name, String age, String imei) {
        RestClient.getInstance().guestLogin(name, age, imei)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mGuestLoginView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<User> userResponse) {
                        mGuestLoginView.onGuestLoginResult(userResponse.isSuccessful());
                    }
                });
    }
}
