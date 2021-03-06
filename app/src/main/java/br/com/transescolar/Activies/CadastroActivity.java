package br.com.transescolar.Activies;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.transescolar.R;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome, editCpf, editApelido, editPlaca, editTell, editSenha, editEmail, editLogin;
    Button btnCadastro;
    ProgressBar progressBar;
    private static String URL_REGIST = "http://192.168.1.33/apiapptransescolar/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Cadastro");     //Titulo para ser exibido na sua Action Bar em frente à seta

        progressBar = findViewById(R.id.progressBarCadastro);
        editLogin = findViewById(R.id.ediLogiT);
        editSenha = findViewById(R.id.editSenhaT);
        editNome = findViewById(R.id.editNomeT);
        editCpf = findViewById(R.id.editCpfT);
        editApelido = findViewById(R.id.editApelido2);
        editPlaca = findViewById(R.id.editPlaca);
        editTell = findViewById(R.id.editTellT);
        editTell.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s)
            {
                String text = editTell.getText().toString();
                int  textLength = editTell.getText().length();
                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return;
                if (textLength == 1) {
                    if (!text.contains("("))
                    {
                        editTell.setText(new StringBuilder(text).insert(text.length() - 1, "(").toString());
                        editTell.setSelection(editTell.getText().length());
                    }
                }
                else if (textLength == 4)
                {
                    if (!text.contains(")"))
                    {
                        editTell.setText(new StringBuilder(text).insert(text.length() - 1, ")").toString());
                        editTell.setSelection(editTell.getText().length());
                    }
                }
                else if (textLength == 5)
                {
                    editTell.setText(new StringBuilder(text).insert(text.length() - 1, " ").toString());
                    editTell.setSelection(editTell.getText().length());
                }
                else if (textLength == 11)
                {
                    if (!text.contains("-"))
                    {
                        editTell.setText(new StringBuilder(text).insert(text.length() - 1, "-").toString());
                        editTell.setSelection(editTell.getText().length());
                    }
                }


            }
        });
        editEmail = findViewById(R.id.editEmailT);
        btnCadastro = findViewById(R.id.btnSaveCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = editSenha.getText().toString().trim();
                String nome = editNome.getText().toString().trim();
                String cpf = editCpf.getText().toString().trim();
                String apelido = editApelido.getText().toString().trim();
                String placa = editPlaca.getText().toString().trim();
                String tell = editTell.getText().toString().trim();
                String email = editEmail.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editEmail.setError("Entre com um email valido!");
                    editSenha.requestFocus();
                    return;
                }
                if (senha.length() < 6){
                    editSenha.setError("Senha deve ter no minimo 6 caracteres");
                    editSenha.requestFocus();
                    return;
                }

                if (!nome.isEmpty() || !email.isEmpty()|| !cpf.isEmpty() || !apelido.isEmpty() || !placa.isEmpty() || !tell.isEmpty() || !senha.isEmpty()){
                    Regist();
                    progressBar.setVisibility(View.VISIBLE);
                    btnCadastro.setVisibility(View.GONE);
                }else {
                    editNome.setError("Insera seu Nome!");
                    editCpf.setError("Insera seu CPF!");
                    editApelido.setError("Insera seu Apelido!");
                    editPlaca.setError("Insera sua Placa!");
                    editTell.setError("Insera seu Telefone!");
                    editSenha.setError("Insera sua senha!");
                    editEmail.setError("Insera seu Email!");
                    editSenha.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    btnCadastro.setVisibility(View.VISIBLE);
                    return;
                }
            }
        });

    }

    public void Regist() {
        final String senha = this.editSenha.getText().toString().trim();
        final String nome = this.editNome.getText().toString().trim();
        final String email = this.editEmail.getText().toString().trim();
        final String cpf = this.editCpf.getText().toString().trim();
        final String apelido = this.editApelido.getText().toString().trim();
        final String placa = this.editPlaca.getText().toString().trim();
        final String tell = this.editTell.getText().toString().trim();

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
                            }else {
                                Toast.makeText(CadastroActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                btnCadastro.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                            Toast.makeText(CadastroActivity.this, "CPF já cadastrodo!", Toast.LENGTH_SHORT).show();
                            Log.e("JSON", "Error parsing JSON", e1);
                            progressBar.setVisibility(View.GONE);
                            btnCadastro.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroActivity.this, "Erro ao resgistrar o usuario!", Toast.LENGTH_SHORT).show();
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








}
