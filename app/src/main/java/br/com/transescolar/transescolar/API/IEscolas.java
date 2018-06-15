package br.com.transescolar.transescolar.API;

import java.util.List;

import br.com.transescolar.transescolar.Model.Escolas;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IEscolas {

    @POST("/api/escola/add")
    Call<Void> insereEscola(@Body Escolas escolas);

    @GET("/api/escolas")
    Call<List<Escolas>> getLivros();

    @GET("/api/escola/{id}")
    Call<Escolas> getLivroPorId(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
