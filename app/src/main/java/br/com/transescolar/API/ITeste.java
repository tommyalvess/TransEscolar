package br.com.transescolar.API;

import java.util.List;

import br.com.transescolar.Model.Teste;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITeste {


    @GET("/posts")
    Call<List<Teste>> getTeste();

    @GET("/posts")
    Call<List<Teste>> getTestePorId(@Query("userId") int userId);

    @GET("posts")
    Call<List<Teste>> getTesteId(@Query("id") int userId);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
