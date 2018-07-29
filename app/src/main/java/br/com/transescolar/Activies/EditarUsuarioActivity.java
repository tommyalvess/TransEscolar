package br.com.transescolar.Activies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import br.com.transescolar.R;

public class EditarUsuarioActivity extends AppCompatActivity {

    private static final String TAG = EditarUsuarioActivity.class.getSimpleName();
    EditText editNomeU, editEmailU, editCpfU, editApelidoU, editPlacaU, editTellU;
    SessionManager sessionManager;
    String getId;
    private static String URL_READ = "http://192.168.1.33/Teste1Php/read_tios.php?apicall=findAll";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Editar Cadastro");

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        editNomeU =  findViewById(R.id.editNomeT);
        editEmailU =  findViewById(R.id.editEmailT);
        editCpfU =  findViewById(R.id.editCpfT);
        editApelidoU =  findViewById(R.id.editApelido2);
        editPlacaU =  findViewById(R.id.editPlaca);
        editTellU =  findViewById(R.id.editTellT);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Alterado com Sucesso!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void getUserDetail(){
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setMessage("Espere...");
        progressBar.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject json = new JSONObject( response );
                            JSONArray nameArray = json.names();
                            JSONArray valArray = json.toJSONArray( nameArray );


                            if (!json.optBoolean("1")){
                                for ( int i = 0; i < valArray.length(); i++) {

                                    Toast.makeText(getApplicationContext(), json.getString("message"), Toast.LENGTH_SHORT).show();

                                    JSONObject object = valArray.getJSONObject(i);
                                    String id = object.getString("idTios").trim();
                                    String nome = object.getString("nome").trim();
                                    String email = object.getString("email").trim();
                                    String cpf = object.getString("cpf").trim();
                                    String apelido = object.getString("apelido").trim();
                                    String placa = object.getString("placa").trim();
                                    String tell = object.getString("tell").trim();

                                    editNomeU.setText(nome);
                                    editEmailU.setText(email);
                                    editCpfU.setText(cpf);
                                    editApelidoU.setText(apelido);
                                    editPlacaU.setText(placa);
                                    editTellU.setText(tell);
                                }
                            }else {
                                Toast.makeText(EditarUsuarioActivity.this,json.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }catch ( JSONException e ) {
                            Log.e("JSONException", "Error parsing JSON", e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }
}
