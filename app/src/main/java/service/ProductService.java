package service;

import java.util.List;

import model.MProduct;
import model.MStore;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {

    @GET("/v1/getProducts/")
    Call<List<MProduct>> getProducts();

    @POST("/v1/insertProduct/")
    Call<MStore> insertProduct(@Body MProduct mProduct);

    @POST("/v1/updateProduct/{id}")
    Call<MStore> updateProduct(@Path("id") long id, @Body MProduct mProduct);

    @POST("/v1/deleteProduct/{id}")
    Call<MStore> deleteProduct(@Path("id") long id);

    @GET("/v1/getProductById/{id}")
    Call<MStore> getProductById(@Path("id") long id);



}
