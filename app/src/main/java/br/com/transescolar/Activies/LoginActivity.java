package br.com.transescolar.Activies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    Button btnLogin;
    EditText editCpf, editSenha;

    ProgressBar loginProgress;
    private static String URL_LOGIN = "http://192.168.1.33/apiapptransescolar/login6.php?apicall=login";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        editCpf = findViewById(R.id.login_main);
        editSenha = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
        loginProgress = findViewById(R.id.login_progress);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = editCpf.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                //validating inputs
                if (TextUtils.isEmpty(cpf) ) {
                    editCpf.setError("Please enter your CPF");
                    editCpf.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(senha)) {
                    editSenha.setError("Please enter your password");
                    editSenha.requestFocus();
                    return;
                }

                //userLogin();
                login(cpf,senha);
            }
        });

    }

    private void login(final String cpf, final String senha) {
        loginProgress.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                              JSONObject json = new JSONObject(response);
                              JSONArray nameArray = json.names();
                              JSONArray valArray = json.toJSONArray( nameArray );


                            if (!json.optBoolean("1")){

                              for ( int i = 0; i < valArray.length(); i++) {
                                  JSONObject object = valArray.getJSONObject(i);
                                  String id = object.getString("idTios").trim();
                                  String nome = object.getString("nome").trim();
                                  String email = object.getString("email").trim();
                                  String cpf = object.getString("cpf").trim();
                                  String apelido = object.getString("apelido").trim();
                                  String placa = object.getString("placa").trim();
                                  String tell = object.getString("tell").trim();

                                  sessionManager.createSession(id, nome, email, cpf, apelido, placa, tell);

                                  Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                  intent.putExtra("idTios", id);
                                  intent.putExtra("nome", nome);
                                  intent.putExtra("email", email);
                                  intent.putExtra("cpf", cpf);
                                  intent.putExtra("apelido", apelido);
                                  intent.putExtra("placa", placa);
                                  intent.putExtra("tell", tell);
                                  startActivity(intent);

                                  loginProgress.setVisibility(View.GONE);
                                  btnLogin.setVisibility(View.VISIBLE);
                                  finish();

                              }
                          }else {
                                Toast.makeText(LoginActivity.this,json.getString("message"),Toast.LENGTH_LONG).show();
                                loginProgress.setVisibility(View.GONE);
                                btnLogin.setVisibility(View.VISIBLE);

                          }
                        }catch ( JSONException e ) {
                            loginProgress.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            Log.e("JSON", "Error parsing JSON", e);
                        }

                        Log.e(TAG, "response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Opss!! Sem Conexão a internet", Toast.LENGTH_SHORT).show();

                        Log.e(TAG, "response: " + error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();
                param.put("cpf", cpf);
                param.put("senha", senha);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void Cadastro(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}
