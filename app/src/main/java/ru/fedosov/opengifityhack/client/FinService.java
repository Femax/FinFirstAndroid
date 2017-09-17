package ru.fedosov.opengifityhack.client;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.fedosov.opengifityhack.client.model.AttrCreatePortfolio;
import ru.fedosov.opengifityhack.client.model.GuestLogin;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import ru.fedosov.opengifityhack.client.model.UsdCurrency;
import rx.Observable;

public interface FinService {

    @POST("/reg")
    rx.Observable<Response<String>> reg(@Body HashMap<String, Object> body);

    @GET("/api/demo/getUser/{imei}")
    rx.Observable<Response<User>> getUser(@Path("imei") String imei);

    @POST("/api/setUser")
    Observable<Response<User>> guestLogin(@Body GuestLogin body);

    @GET("/api/getAllPortfels/{userId}")
    Observable<Response<List<Portfolio>>> getPorfolios(@Path("userId") String userId);

    @POST("/api/setPortfel/")
    Observable<Response<String>> createPortfolio(@Body AttrCreatePortfolio portfolio);

    @GET("/api/getCurrency")
    Observable<UsdCurrency> getCurrency();
}