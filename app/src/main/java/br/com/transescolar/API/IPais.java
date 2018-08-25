package br.com.transescolar.API;

import java.util.List;

import br.com.transescolar.Model.Pais;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IPais {

    @GET("pais")
    Call<List<Pais>> getPais();

    @GET("pais/[{id}]")
    Call<List<Pais>> getPaisPorId(@Query("id") int idTios);

    @GET("pais/search/[{query}]")
    Call<List<Pais>> getPaisId(@Path("query") int idTios);

   Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.33/ApiRestFull/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
