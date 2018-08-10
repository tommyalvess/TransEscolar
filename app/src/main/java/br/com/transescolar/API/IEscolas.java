package br.com.transescolar.API;


import java.util.List;

import br.com.transescolar.Model.Escolas;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IEscolas {

    @GET("escolas")
    Call< List<Escolas> > getEscolas ( @Query("key") String keyword );

}




