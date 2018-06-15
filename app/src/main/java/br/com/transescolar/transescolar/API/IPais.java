package br.com.transescolar.transescolar.API;

import java.util.List;

import br.com.transescolar.transescolar.Model.Pais;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPais {

    @POST("/api/pais/add")
    Call<Void> inseriPai(@Body Pais pais);

    @GET("/api/pais")
    Call<List<Pais>> getPais();

    @GET("/api/pais/{id}")
    Call<Pais> getPaisPorId(@Path("id") String id);

    @PUT("/api/pais/update/{id}")
    Call<Void> alteraPai(@Path("id") String id, @Body Pais pais);

    @DELETE("/api/pais/delete/{id}")
    Call<Void> removePai(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
