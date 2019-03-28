package service;

import java.util.List;

import model.MStore;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UrlFriendlyService {

    @GET("/v1/getUrlFriendly/")
    Call<List<MStore>> getUrlFriendly();

    @POST("/v1/insertUrlFriendly/")
    Call<MStore> insertUrlFriendly(@Body MStore mStore);

    @POST("/v1/updateUrlFriendly/{id}")
    Call<MStore> updateUrlFriendly(@Path("id") long id, @Body MStore mStore);

    @POST("/v1/deleteUrlFriendly/{id}")
    Call<MStore> deleteUrlFriendly(@Path("id") long id);

    @GET("/v1/getUrlFriendlyById/{id}")
    Call<MStore> getUrlFriendlyById(@Path("id") long id);


}
