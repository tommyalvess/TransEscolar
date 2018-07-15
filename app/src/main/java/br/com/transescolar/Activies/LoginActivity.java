package br.com.transescolar.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import br.com.transescolar.R;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnCadastro;
    ProgressBar loginProgress;
    EditText editCpf, editSenha;
    private static String URL_LOGIN = "http://192.168.1.33/Teste1Php/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editCpf = findViewById(R.id.login_main);
        editSenha = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);
//        btnCadastro.findViewById(R.id.btn_cadastro);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cpf = editCpf.getText().toString().trim();
//                String senha = editSenha.getText().toString().trim();
//
//                if (!cpf.isEmpty() || senha.isEmpty()){
//                    login(cpf, senha);
//                }else {
//                    editCpf.setError("Insera seu CPF!");
//                    editSenha.setError("Insera sua senha!!");
//                }
//            }
//        });

    }

//    private void login(final String cpf, final String senha) {
//        //loginProgress.setVisibility(View.VISIBLE);
//        //btnLogin.setVisibility(View.GONE);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String success = jsonObject.getString("success");
//                            JSONArray jsonArray = jsonObject.getJSONArray("login");
//
//                            if (success.equals("1")){
//                                for (int i = 0; i < jsonArray.length(); i++){
//                                    JSONObject object = jsonArray.getJSONObject(i);
//                                    String cpf = object.getString("cpf").trim();
//                                    String senha = object.getString("senha").trim();
//                                    loginProgress.setVisibility(View.GONE);
//                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                    startActivity(intent);
//                                }
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            loginProgress.setVisibility(View.GONE);
//                            Toast.makeText(LoginActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        loginProgress.setVisibility(View.GONE);
//                        Toast.makeText(LoginActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = new HashMap<>();
//                param.put("cpf", cpf);
//                param.put("senha", senha);
//                return param;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }


    public void Cadastro(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}
