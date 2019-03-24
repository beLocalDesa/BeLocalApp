package service;

import java.util.List;

import model.MStore;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StoreService {

    @GET("/v1/getStores/")
    Call<List<MStore>> getStores();

    @POST("/v1/insertStore/")
    Call<MStore> insertStore(@Body MStore mStore);

    @POST("/v1/updateStore/{id}")
    Call<MStore> updateStore(@Path("id") long id, @Body MStore mStore);

    @POST("/v1/deleteStore/{id}")
    Call<MStore> deleteStore(@Path("id") long id);

    @GET("/v1/getStoreById/{id}")
    Call<MStore> getStoreById(@Path("id") long id);


}
