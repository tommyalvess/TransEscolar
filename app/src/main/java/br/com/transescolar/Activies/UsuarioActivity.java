package br.com.transescolar.Activies;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.Conexao.SharedPrefManager;
import br.com.transescolar.Model.Tios;
import br.com.transescolar.R;


public class UsuarioActivity extends AppCompatActivity {

    TextView textNomeU, textEmailU, textCpfU, textApelidoU, texPlacaU, textTellU;
    private static String URL_READ = "http://192.168.1.33/Teste1Php/read_tios.php?apicall=findAll";
    String getId;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        textNomeU =  findViewById(R.id.textNomeU);
        textEmailU =  findViewById(R.id.textEmailU);
        textCpfU =  findViewById(R.id.textCpfU);
        textApelidoU =  findViewById(R.id.textApelidoU);
        texPlacaU =  findViewById(R.id.texPlacaU);
        textTellU =  findViewById(R.id.textTellU);


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        String nNome = user.get(sessionManager.NAME);
        String nEmail = user.get(sessionManager.EMAIL);
        String nCPF = user.get(sessionManager.CPF);
        String nApelido = user.get(sessionManager.APELIDO);
        String nPlaca = user.get(sessionManager.PLACA);
        String nTell = user.get(sessionManager.TELL);


        getSupportActionBar().setTitle(nApelido);     //Titulo para ser exibido na sua Action Bar em frente à seta

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Volte em breve!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                sessionManager.logout();
                finish();
                Intent intent = new Intent(UsuarioActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    //Pegar as infs do user
    private void getUserDetail(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Mensagem", response.toString());

                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray nameArray = json.names();
                            JSONArray valArray = json.toJSONArray( nameArray );

                            if (!json.optBoolean("1")){
                                for (int i = 0; i < valArray.length(); i++) {
                                    JSONObject object = valArray.getJSONObject(i);
                                    String id = object.getString("idTios").trim();
                                    String nome = object.getString("nome").trim();
                                    String email = object.getString("email").trim();
                                    String cpf = object.getString("cpf").trim();
                                    String apelido = object.getString("apelido").trim();
                                    String placa = object.getString("placa").trim();
                                    String tell = object.getString("tell").trim();

                                    textNomeU.setText(nome);
                                    textEmailU.setText(email);
                                    textCpfU.setText(cpf);
                                    textApelidoU.setText(apelido);
                                    texPlacaU.setText(placa);
                                    textTellU.setText(tell);

                                }
                            }else {
                                Toast.makeText(UsuarioActivity.this,json.getString("message"),Toast.LENGTH_LONG).show();

                            }
                        }catch ( JSONException e ) {
                            Log.e("JSON", "Error parsing JSON", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UsuarioActivity.this, "Opss!! Algo deu errado VolleyError", Toast.LENGTH_SHORT).show();
                        Log.e("VolleyError", "Error", error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("idTios", getId);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_usuario, menu);
        return true;

    };

    public void editar(MenuItem item) {
        Log.d("EditActivity","MapActivity");
        Intent intent = new Intent(UsuarioActivity.this, EditarUsuarioActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

}
