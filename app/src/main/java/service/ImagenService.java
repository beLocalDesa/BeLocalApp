package service;

import java.util.List;

import model.MImagen;
import model.MStore;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImagenService {

    @GET("/v1/getImagen/")
    Call<List<MImagen>> getImagen();

    @POST("/v1/insertImagen/")
    Call<MImagen> insertImagen(@Body MImagen mImagen);

    @POST("/v1/updateImagen/{id}")
    Call<MImagen> updateImagen(@Path("id") long id, @Body MImagen mImagen);

    @POST("/v1/deleteImagen/{id}")
    Call<MImagen> deleteImagen(@Path("id") long id);

    @GET("/v1/getImagenById/{id}")
    Call<MImagen> getImagenById(@Path("id") long id);



}
