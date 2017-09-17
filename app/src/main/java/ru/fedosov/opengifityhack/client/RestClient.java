package ru.fedosov.opengifityhack.client;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.fedosov.opengifityhack.client.model.GuestLoginJson;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import rx.Observable;

public class RestClient {

    private static FinService apiService;
    private static RestClient instance;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.136:8081/")
                .client(getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(FinService.class);
    }

    public OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    public Observable<User> getModelsObservable(String email, String password) {
//        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),"shit");
//        return  apiService.loginToken(new LoginJson(email,password));
        return Observable.just(new User());
    }

    public Observable<Response<User>> getUsers() {
        return apiService.getUsers("asdsad");
    }

    public Observable<Response<User>> guestLogin(String name, String age, String imei) {
        return apiService.guestLogin(new GuestLoginJson(imei, name, age));
    }

    public Observable<Response<List<Portfolio>>> getPortfolios(String userId) {
        return apiService.getPorfolios(userId);
    }

    public Observable<Response<String>> createPortfolio() {
        return apiService.createPortfolio();
    }
}