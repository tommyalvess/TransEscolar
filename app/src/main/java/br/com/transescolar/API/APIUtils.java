package br.com.transescolar.API;

import retrofit2.Retrofit;

public class APIUtils {

    private APIUtils() {
    };

    public static final String API_URL = "http://192.168.1.33/TransEscolar/public/";

    public static ITios getTiosService(){
        return ApiClient.getApi(API_URL).create(ITios.class);
    }
}
