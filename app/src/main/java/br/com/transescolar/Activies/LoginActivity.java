package br.com.transescolar.Activies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import br.com.transescolar.API.ApiAuthenticationClient;
import br.com.transescolar.Conexao.RequestHandler;
import br.com.transescolar.Conexao.SharedPrefManager;
import br.com.transescolar.Conexao.URLs;
import br.com.transescolar.Model.Tios;
import br.com.transescolar.R;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText editCpf, editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
            return;
        }

        editCpf = findViewById(R.id.login_main);
        editSenha = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    private void userLogin() {
        //first getting the values
        final String username = editCpf.getText().toString();
        final String password = editSenha.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editCpf.setError("Please enter your CPF");
            editCpf.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editSenha.setError("Please enter your password");
            editSenha.requestFocus();
            return;
        }

        //if everything is fine

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = findViewById(R.id.login_progress);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                        //creating a new user object
                        Tios user = new Tios(
                                userJson.getInt("id"),
                                userJson.getString("nome"),
                                userJson.getString("email"),
                                userJson.getString("cpf"),
                                userJson.getString("apelido"),
                                userJson.getString("placa"),
                                userJson.getString("tell")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("cpf", username);
                params.put("senha", password);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        } // final class UserLogin
        UserLogin ul = new UserLogin();
        ul.execute();
    }// final da userLogin

//    private void login(final String cpf, final String senha) {
//        loginProgress.setVisibility(View.VISIBLE);
//        btnLogin.setVisibility(View.GONE);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            //boolean success = jsonResponse.getBoolean("success");
//                            String success = jsonResponse.getString("success");
//                            JSONArray jsonArray = jsonResponse.getJSONArray("login");
//
//                            if (success.equals("1")){
//                                for (int i = 0; i < jsonArray.length(); i++){
//                                    JSONObject object = jsonArray.getJSONObject(i);
//                                    String cpf = object.getString("cpf").trim();
//                                    String senha = object.getString("senha").trim();
//                                    loginProgress.setVisibility(View.GONE);
//                                }
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                startActivity(intent);
//                            }else {
//                                loginProgress.setVisibility(View.GONE);
//                                btnLogin.setVisibility(View.VISIBLE);
//                                Toast.makeText(LoginActivity.this,"Usuario não localizado!!",Toast.LENGTH_LONG).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            loginProgress.setVisibility(View.GONE);
//                            btnLogin.setVisibility(View.VISIBLE);
//                            Toast.makeText(LoginActivity.this, "Tente novamente mais tarde!!" + e.toString(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        loginProgress.setVisibility(View.GONE);
//                        btnLogin.setVisibility(View.VISIBLE);
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
