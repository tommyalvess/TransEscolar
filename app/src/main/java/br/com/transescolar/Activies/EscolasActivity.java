package br.com.transescolar.Activies;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.util.List;

import br.com.transescolar.API.ApiClient;
import br.com.transescolar.API.IEscolas;
import br.com.transescolar.Adapter.EscolaAdapter;
import br.com.transescolar.Model.Escolas;
import br.com.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EscolasActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private List<Escolas> escolas;
        private EscolaAdapter escolaAdapter;
        private IEscolas iEscolas;
        ProgressBar progressBar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_escolas);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
            getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
            getSupportActionBar().setTitle("Escolas");     //Titulo para ser exibido na sua Action Bar em frente à seta

            progressBar = findViewById(R.id.progess);
            recyclerView = findViewById(R.id.escolaList);
            //layoutManager = new LinearLayoutManager(this);
            GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
            //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            fetchSchool("");

        }

        public void fetchSchool (String key){
            IEscolas iEscolas = IEscolas.retrofit.create(IEscolas.class);
            Call<List<Escolas>> call = iEscolas.getEscolas(key);

            call.enqueue(new Callback<List<Escolas>>() {
                @Override
                public void onResponse(Call<List<Escolas>> call, Response<List<Escolas>> response) {
                    progressBar.setVisibility(View.GONE);
                    escolas = response.body();
                    escolaAdapter = new EscolaAdapter(escolas, EscolasActivity.this);
                    recyclerView.setAdapter(escolaAdapter);
                    escolaAdapter.notifyDataSetChanged();
                }


                @Override
                public void onFailure(Call<List<Escolas>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(EscolasActivity.this, "Opss! Tente novamente mais tarde!", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_escola, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchSchool(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchSchool(newText);
                return false;
            }
        });

        return true;
    }

    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(escolaAdapter);
    }

    }// fim da Activity
