package br.com.transescolar.transescolar.API;

import java.util.List;

import br.com.transescolar.transescolar.Model.Teste;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ITeste {


    @GET("/posts")
    Call<List<Teste>> getTeste();

    @GET("/posts/{id}")
    Call<Teste> getTestePorId(@Path("id") int id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
