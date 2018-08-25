package br.com.transescolar.Teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import br.com.transescolar.API.IKids;
import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.Model.Kids;
import br.com.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesteEscola extends AppCompatActivity {

    ProgressBar loginProgress;
    String getId;
    private int id;

    SessionManager sessionManager;

    private final String URL = "http://192.168.1.33/apiapptransescolar/crianca";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Filhos> lisKids;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passageiros);

        loginProgress = findViewById(R.id.progess);
        recyclerView = findViewById(R.id.kidsList);
        lisKids = new ArrayList<>();
        jsonrequest();


    }

    private void jsonrequest() {

        request = new JsonArrayRequest(URL, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                loginProgress.setVisibility(View.GONE);

                JSONObject jsonObject = null;

                for (int i = 0; i < response. length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Filhos kids = new Filhos();
                        kids.setNome(jsonObject.getString("nome"));
                        kids.setPeriodo(jsonObject.getString("periodo"));
                        kids.setImg(jsonObject.getString("img"));
                        kids.setNm_escola(jsonObject.getString("nm_escola"));
                        lisKids.add(kids);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                setRvadapter(lisKids);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loginProgress.setVisibility(View.GONE);
            }
        });

        requestQueue = Volley.newRequestQueue(TesteEscola.this);
        requestQueue.add(request);
    }

    private void setRvadapter(List<Filhos> lisKids) {
        TestAdapterKids myAdapter = new TestAdapterKids(this, lisKids);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }


    //@Override
//    protected void onStart() {
//        super.onStart();
//
//        final ListView list = findViewById(R.id.lstTeste);
//        final TextView textAviso = findViewById(R.id.textAviso);
//
//        IKids iKids = IKids.retrofit.create(IKids.class);
//
//        sessionManager = new SessionManager(this);
//        //loginProgress = findViewById(R.id.login_progress);
//
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        getId = user.get(sessionManager.ID);
//
//        //id = Integer.parseInt(getId);
//        id = 1;
//        final Call<List<Kids>> call = iKids.getKidPorId(id);
//
//        call.enqueue(new Callback<List<Kids>>() {
//            @Override
//            public void onResponse(Call<List<Kids>> call, Response<List<Kids>> response) {
//
//                List<Kids> listTeste = response.body();
//
//                if (listTeste != null) {
//                    TestAdapterKids adapter = new TestAdapterKids(getBaseContext(), listTeste);
//                    list.setAdapter(adapter);
//                    textAviso.setVisibility(View.GONE);
//                }else {
//                    textAviso.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Kids>> call, Throwable t) {
//                Toast.makeText(TesteEscola.this, "Opss Algo deu errado!!!", Toast.LENGTH_SHORT).show();
//                Log.e("Call", "falhou:", t);
//            }
//        });
//
//
//
//
//    }
}
