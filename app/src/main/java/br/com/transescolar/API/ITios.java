package br.com.transescolar.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITios {

    @FormUrlEncoded
    @POST("createuser/")
    Call<ResponseBody> createuser (
            @Field("nome") String nome,
            @Field("email") String email,
            @Field("cpf") String cpf,
            @Field("apelido") String apelido,
            @Field("placa") String placa,
            @Field("tell") String tell,
            @Field("senha") String senha
    );
}
