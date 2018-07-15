package br.com.transescolar.API;

import java.util.List;

import br.com.transescolar.Model.Kids;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IKids {

    @POST("/api/kids/add")
    Call<Void> insereKids(@Body Kids kids);

    @GET("/api/kids")
    Call<List<Kids>> getKids();

    @GET("/api/kids/{id}")
    Call<Kids> getKidPorId(@Path("id") String id);

    @PUT("/api/kids/update/{id}")
    Call<Void> alteraKid(@Path("id") String id, @Body Kids kids);

    @DELETE("/api/kids/delete/{id}")
    Call<Void> removeKid(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
