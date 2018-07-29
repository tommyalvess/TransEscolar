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

import java.util.HashMap;

import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.Conexao.SharedPrefManager;
import br.com.transescolar.Model.Tios;
import br.com.transescolar.R;


public class UsuarioActivity extends AppCompatActivity {

    TextView textNomeU, textEmailU, textCpfU, textApelidoU, texPlacaU, textTellU;

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
        String nNome = user.get(sessionManager.NAME);
        String nEmail = user.get(sessionManager.EMAIL);
        String nCPF = user.get(sessionManager.CPF);
        String nApelido = user.get(sessionManager.APELIDO);
        String nPlaca = user.get(sessionManager.PLACA);
        String nTell = user.get(sessionManager.TELL);

        textNomeU.setText(nNome);
        textEmailU.setText(nEmail);
        textCpfU.setText(nCPF);
        textApelidoU.setText(nApelido);
        texPlacaU.setText(nPlaca);
        textTellU.setText(nTell);

        getSupportActionBar().setTitle(nApelido);     //Titulo para ser exibido na sua Action Bar em frente à seta

        //when the user presses logout button
        //calling the logout method
//        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                SharedPrefManager.getInstance(getApplicationContext()).logout();
//            }
//        });

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

    public void sair(View view) {

        SharedPrefManager.getInstance(getApplicationContext()).sair();
        Intent intent = new Intent(UsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
