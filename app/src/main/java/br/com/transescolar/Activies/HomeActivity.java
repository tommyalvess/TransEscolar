package br.com.transescolar.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.Conexao.SharedPrefManager;
import br.com.transescolar.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {


    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        sessionManager.checkLogin();

        // pegar infs da session
        HashMap<String, String> user = sessionManager.getUserDetail();

        CircleImageView imgPass = (CircleImageView) findViewById(R.id.passageiros);
        CircleImageView imgEscola = (CircleImageView) findViewById(R.id.escolas);
        CircleImageView imgIntinerario = (CircleImageView) findViewById(R.id.intinerario);
        CircleImageView imgMap = (CircleImageView) findViewById(R.id.map);
        CircleImageView imgUsuario = (CircleImageView) findViewById(R.id.user);
        CircleImageView imgPais = (CircleImageView) findViewById(R.id.pais);


        imgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PassageirosActivity","passageiro");
                Intent intent = new Intent(HomeActivity.this, PassageirosActivity.class);
                startActivity(intent);
            }
        }); //Fim do imgPass

        imgEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EscolasActivity","escola");
                Intent intent = new Intent(HomeActivity.this, EscolasActivity.class);
                startActivity(intent);

            }
        });// Fim imgEscola

        imgIntinerario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("IntinerarioActivity","intinerario");
                Intent intent = new Intent(HomeActivity.this, IntinerarioActivity.class);
                startActivity(intent);
            }
        }); // Fim do imgIntinerario

        imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MapActivity","MapActivity");
                Intent intent = new Intent(HomeActivity.this, MapsExActivity.class);
                startActivity(intent);
            }
        }); // FIm do imgMap

        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UsuarioActivity","UsuarioActivity");
                                Intent intent = new Intent(HomeActivity.this, UsuarioActivity.class);
                startActivity(intent);
            }
        }); // Fim do imgUsuario

        imgPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PaisActivity","PaisActivity");
                Intent intent = new Intent(HomeActivity.this, PaisActivity.class);
                startActivity(intent);
            }
        }); // Fim do imgPais

    }//OnCreate

}//Class
