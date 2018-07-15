package br.com.transescolar.API;

import java.util.List;

import br.com.transescolar.Model.Tios;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APILogin {

    @GET("AddTio.php")
    Call<Tios> getTio(@Query("nome") String nome, @Query("email")String email, @Query("cpf") String cpf, @Query("apelido") String apelido, @Query("placa") String placa, @Query("tell") String tell, @Query("locali") String locali, @Query("senha") String senha, @Query("img") String img);

    @GET("login.php")
    Call<Tios> getLogin(@Query("cpf") String cpf, @Query("senha") String senha);
}
