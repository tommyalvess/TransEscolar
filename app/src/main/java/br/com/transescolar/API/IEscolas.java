package br.com.transescolar.API;


import java.util.List;

import br.com.transescolar.Model.Escolas;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IEscolas {

    @GET("escolas")
    Call< List<Escolas> > getEscolas ( @Query("key") String keyword );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.33/apiapptransescolar/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}




