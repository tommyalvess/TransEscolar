package br.com.transescolar.Activies;

import android.content.Intent;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


import br.com.transescolar.API.IKids;
import br.com.transescolar.Adapter.KidAdapter;
import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.Model.Kids;
import br.com.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassageirosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Kids> kids;
    private KidAdapter kidAdapter;
    private IKids iKids;
    ProgressBar progressBar;
    SessionManager sessionManager;
    String getId;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passageiros);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Passageiros");     //Titulo para ser exibido na sua Action Bar em frente à seta

        progressBar = findViewById(R.id.progess);
        recyclerView = findViewById(R.id.kidsList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchKids();


    }

    private void fetchKids() {

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        id = Integer.parseInt(getId);

        IKids iKids = IKids.retrofit.create(IKids.class);
        final  Call<List<Kids>> call = iKids.getKidPorId(id);

        call.enqueue(new Callback<List<Kids>>() {
            @Override
            public void onResponse(Call<List<Kids>> call, Response<List<Kids>> response) {
                progressBar.setVisibility(View.GONE);
                kids = response.body();
                kidAdapter = new KidAdapter(kids, PassageirosActivity.this);
                recyclerView.setAdapter(kidAdapter);
                kidAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Kids>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PassageirosActivity.this, "Opss! Tente novamente mais tarde!", Toast.LENGTH_SHORT).show();
                Log.e("Call", "carregar dados", t);
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//
//        return true;
//
//    };
//
//    public void addPass(MenuItem item) {
//        Intent intent = new Intent(PassageirosActivity.this, AddPassActivity.class);
//        startActivity(intent);
//    }




}
