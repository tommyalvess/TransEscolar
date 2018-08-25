package br.com.transescolar.Activies;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import br.com.transescolar.API.ITeste;
import br.com.transescolar.Adapter.TesteAdapter;
import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.Model.Teste;
import br.com.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TesteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Teste> kids;
    private TesteAdapter kidAdapter;
    private ITeste iTeste;
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
        getSupportActionBar().setTitle("Teste");     //Titulo para ser exibido na sua Action Bar em frente à seta

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
        //id = 111;
        iTeste = ITeste.retrofit.create(ITeste.class);

        final Call<List<Teste>> call = iTeste.getTesteId(id);

        call.enqueue(new Callback<List<Teste>>() {
            @Override
            public void onResponse(Call<List<Teste>> call, Response<List<Teste>> response) {
                    progressBar.setVisibility(View.GONE);
                    kids = response.body();
                    kidAdapter = new TesteAdapter(kids, TesteActivity.this);
                    recyclerView.setAdapter(kidAdapter);
                    kidAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Teste>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(TesteActivity.this, "Opss! Não foi encontrado!", Toast.LENGTH_SHORT).show();
                Log.e("Call", "carregar dados", t);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                //Se essa atividade faz parte da tarefa do app
                //navegamos para seu parente logico.
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    //Se a atividade não faz parte do aplicativo, criamos uma nova tarefa
                    // para navegação com a pilha de volta sintetizada.
                    TaskStackBuilder.create(this)
                            // Adiciona todas atividades parentes na pilha de volta
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
