package br.com.transescolar.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientTest {

    public static final String BASE_URL = "http://192.168.1.33/ApiRestTeste/";
    public static Retrofit retrofit = null;

    public static Retrofit getApi(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
