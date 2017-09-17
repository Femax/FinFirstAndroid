package ru.fedosov.opengifityhack.client;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.fedosov.opengifityhack.client.model.AttrCreatePortfolio;
import ru.fedosov.opengifityhack.client.model.GuestLogin;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import rx.Observable;

public class RestClient {

    private static FinService apiService;
    private static RestClient instance;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.136:8081")
                .client(getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(FinService.class);
    }

    public OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }


    public Observable<Response<User>> getUsers() {
        return apiService.getUser("asdsad");
    }

    public Observable<Response<User>> guestLogin(String name, String age, String imei) {
        return apiService.guestLogin(new GuestLogin(imei, name, age));
    }

    public Observable<Response<List<Portfolio>>> getPortfolios(String userId) {
        return apiService.getPorfolios(userId);
    }

    public Observable<Response<String>> createPortfolio(AttrCreatePortfolio portfolio) {
        return apiService.createPortfolio(portfolio);
    }

    public Observable<Response<User>> getUser(String imei) {
        return apiService.getUser(imei);
    }
}