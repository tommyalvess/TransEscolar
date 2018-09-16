package br.com.transescolar.API;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.transescolar.Model.Kids;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IKids {


    @GET("kids")
    Call<List<Kids>> getKidId(@Query("idTios") int idTios);

    @GET("kids")
    Call<List<Kids>> getKidPorId(@Query("idTios") int idTios);

    @GET("/kids/[{id}]")
    Call<List<Kids>> getKidsPorId(@Query("id") int id);

//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apsconsigpromotora.com.br/ApiRestFull/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
