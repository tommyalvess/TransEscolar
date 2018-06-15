package br.com.transescolar.transescolar.API;

import java.util.List;

import br.com.transescolar.transescolar.Model.Tios;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ITios {

    @POST("/api/tios/add")
    Call<Void> inseriTios(@Body Tios tios);

    @GET("/api/tios")
    Call<List<Tios>> getTios();

    @GET("/api/tios/{id}")
    Call<Tios> getTiosPorId(@Path("id") String id);

    @PUT("/api/tios/update/{id}")
    Call<Void> alteraTios(@Path("id") String id, @Body Tios tios);

    @DELETE("/api/tios/delete/{id}")
    Call<Void> removeTios(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
