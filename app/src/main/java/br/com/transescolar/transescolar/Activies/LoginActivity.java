package br.com.transescolar.transescolar.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import br.com.transescolar.transescolar.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    ProgressBar loginProgress;
    EditText editCpf, editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_login);
//        loginProgress = findViewById(R.id.login_progress);
//        loginProgress.setVisibility(View.VISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginProgress.setVisibility(View.VISIBLE);
//                btnLogin.setVisibility(View.INVISIBLE);



                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void Entrar(View view) {
//
//
//
//
//        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//        startActivity(intent);
//    }

    public void Cadastro(View view) {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}
