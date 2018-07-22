package br.com.transescolar.Activies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;

import br.com.transescolar.Conexao.RequestHandler;
import br.com.transescolar.Conexao.SharedPrefManager;
import br.com.transescolar.Conexao.URLs;
import br.com.transescolar.Model.Tios;
import br.com.transescolar.R;

public class Cadastro2Activity extends AppCompatActivity {

    EditText editNome, editCpf, editApelido, editPlaca, editTell, editSenha, editEmail;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Cadastro");     //Titulo para ser exibido na sua Action Bar em frente à seta

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
            return;
        }

        editNome = (EditText) findViewById(R.id.editNomeT);
        editCpf = (EditText) findViewById(R.id.editCpfT);
        editApelido = (EditText) findViewById(R.id.editApelido2);
        editPlaca = (EditText) findViewById(R.id.editPlaca);
        editTell = (EditText) findViewById(R.id.editTellT);
        editSenha = (EditText) findViewById(R.id.editSenhaT);
        editEmail = (EditText) findViewById(R.id.editEmailT);

        findViewById(R.id.btnSaveCadastro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });
    }

    private void registerUser() {

        final String nome = editNome.getText().toString().trim();
        final String cpf = editCpf.getText().toString().trim();
        final String apelido = editApelido.getText().toString().trim();
        final String placa = editPlaca.getText().toString().trim();
        final String tell = editTell.getText().toString().trim();
        final String senha = editSenha.getText().toString().trim();
        final String email = editEmail.getText().toString().trim();

        //first we will do the validations

        if (TextUtils.isEmpty(nome)) {
            editNome.setError("Peencher o campo de Nome!");
            editNome.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(cpf)) {
            editCpf.setError("Peencher o campo de Email!");
            editCpf.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Entre com um email valido");
            editEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(apelido)) {
            editApelido.setError("Peencher o campo de Apelido!");
            editApelido.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(placa)) {
            editPlaca.setError("Peencher o campo de Placa!");
            editPlaca.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(tell)) {
            editTell.setError("Peencher o campo de Tellefone!");
            editTell.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(senha)) {
            editSenha.setError("Peencher o campo de Senha!");
            editSenha.requestFocus();
            return;
        }

//        if ((isValidPassword(senha))) {
//            editPlaca.setError("Entre com uma senha valida!");
//            editPlaca.requestFocus();
//            return;
//        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("nome", nome);
                params.put("email", email);
                params.put("cpf", cpf);
                params.put("apelido", apelido);
                params.put("placa", placa);
                params.put("tell", tell);
                params.put("senha", senha);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBarCadastro);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");

                        //creating a new user object
                        Tios user = new Tios(
                                userJson.getInt("id"),
                                userJson.getString("nome"),
                                userJson.getString("email"),
                                userJson.getString("cpf"),
                                userJson.getString("apelido"),
                                userJson.getString("placa"),
                                userJson.getString("tell"),
                                userJson.getString("senha")
                        );

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }// final registerUser()

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}"
        );
        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
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
