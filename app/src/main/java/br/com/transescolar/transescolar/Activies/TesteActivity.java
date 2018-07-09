package br.com.transescolar.transescolar.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.transescolar.transescolar.API.ITeste;
import br.com.transescolar.transescolar.Adapter.TesteAdapter;
import br.com.transescolar.transescolar.Model.Teste;
import br.com.transescolar.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TesteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);


//        final ListView list = (ListView)findViewById(R.id.lstTeste);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ITeste iTeste = ITeste.retrofit.create(ITeste.class);
//
//        final Call<List<Teste>> call = iTeste.getTeste();
//
//        call.enqueue(new Callback<List<Teste>>() {
//            @Override
//            public void onResponse(Call<List<Teste>> call, Response<List<Teste>> response) {
//                final List<Teste> listTeste = response.body();
//
//                if (listTeste != null) {
//                    TesteAdapter adapter = new TesteAdapter(getBaseContext(), listTeste);
//                    list.setAdapter(adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Teste>> call, Throwable t) {
//                Toast.makeText(TesteActivity.this, "Problemas!!!", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final ListView list = (ListView)findViewById(R.id.lstTeste);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        ITeste iTeste = ITeste.retrofit.create(ITeste.class);

        final Call<List<Teste>> call = iTeste.getTeste();

        call.enqueue(new Callback<List<Teste>>() {
            @Override
            public void onResponse(Call<List<Teste>> call, Response<List<Teste>> response) {
                final List<Teste> listTeste = response.body();

                if (listTeste != null) {
                    TesteAdapter adapter = new TesteAdapter(getBaseContext(), listTeste);
                    list.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Teste>> call, Throwable t) {
                Toast.makeText(TesteActivity.this, "Problemas!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
