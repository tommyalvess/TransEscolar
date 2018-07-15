package br.com.transescolar.Activies;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.transescolar.API.APIUtils;
import br.com.transescolar.API.ITios;
import br.com.transescolar.Model.Tios;
import br.com.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome, editCpf, editApelido, editPlaca, editTell, editSenha, editEmail;
    Button btnCadastro;
    ProgressBar progressBar;
    private static String URL_REGIST = "http://192.168.1.33/Teste1Php/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Cadastro");     //Titulo para ser exibido na sua Action Bar em frente à seta

        progressBar = (ProgressBar)findViewById(R.id.progressBarCadastro);
        editNome = (EditText)findViewById(R.id.editNome);
        editCpf = (EditText)findViewById(R.id.editCpf2);
        editApelido = (EditText)findViewById(R.id.editApelido2);
        editPlaca = (EditText)findViewById(R.id.editPlaca);
        editTell = (EditText)findViewById(R.id.editTell);
        editSenha = (EditText)findViewById(R.id.editSenha);
        editEmail = (EditText)findViewById(R.id.editEmail);


        btnCadastro = (Button)findViewById(R.id.btnSaveCadastro);
        progressBar.setVisibility(View.VISIBLE);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });

    }

    public void Regist() {
        progressBar.setVisibility(View.VISIBLE);
        btnCadastro.setVisibility(View.GONE);

        final String nome = this.editNome.getText().toString().trim();
        final String email = this.editEmail.getText().toString().trim();
        final String cpf = this.editCpf.getText().toString().trim();
        final String apelido = this.editApelido.getText().toString().trim();
        final String placa = this.editPlaca.getText().toString().trim();
        final String tell = this.editTell.getText().toString().trim();
        final String senha = this.editSenha.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            //boolean success = jsonObject.getBoolean("success");
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(CadastroActivity.this, "Resgistrado com Sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            Toast.makeText(CadastroActivity.this, "Resgistrado com Sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                            btnCadastro.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroActivity.this, "Resgistrado com Sucesso!" , Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btnCadastro.setVisibility(View.VISIBLE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nome", nome);
                params.put("email", email);
                params.put("cpf", cpf);
                params.put("apelido", apelido);
                params.put("placa", placa);
                params.put("tell", tell);
                params.put("senha", senha);
                return params;
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



}