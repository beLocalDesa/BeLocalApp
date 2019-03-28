package service;

import java.util.List;

import model.MStore;
import model.MUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @GET("/v1/getUsers/")
    Call<List<MUser>> getUsers();

    @POST("/v1/insertUser/")
    Call<MUser> insertUser(@Body MUser mUser);

    @POST("/v1/updateUser/{id}")
    Call<MUser> updateUser(@Path("id") long id, @Body MUser mUser);

    @POST("/v1/deleteUser/{id}")
    Call<MUser> deleteUser(@Path("id") long id);

    @GET("/v1/getUserByUuid/{uuid}")
    Call<MUser> getUserByUuid(@Path("uuid") String uuid);

}
