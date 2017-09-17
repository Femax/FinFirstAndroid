package ru.fedosov.opengifityhack.client;

import java.util.HashMap;
import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.fedosov.opengifityhack.client.model.GuestLogin;
import ru.fedosov.opengifityhack.client.model.Portfolio;
import rx.Observable;

public interface FinService {

    @POST("/reg")
    rx.Observable<Response<String>> reg(@Body HashMap<String, Object> body);

    @GET("/im")
    rx.Observable<Response<User>> getUsers(@Query("imei") String imei);

    @POST("/api/setUser")
    Observable<Response<User>> guestLogin(@Body GuestLogin body);

    Observable<Response<List<Portfolio>>> getPorfolios(@Query("") String userId);

    Observable<Response<String>> createPortfolio();
}