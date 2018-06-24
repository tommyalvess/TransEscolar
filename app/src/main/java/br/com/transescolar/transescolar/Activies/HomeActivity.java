package br.com.transescolar.transescolar.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import br.com.transescolar.transescolar.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast.makeText(v.getContext(), // <- Line changed
                        "PASSAGEIROS",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, PassageirosActivity.class);
                startActivity(intent);
            }
        }); //Fim do imgPass

        imgEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EscolasActivity","escola");
                Toast.makeText(v.getContext(), // <- Line changed
                        "ESCOLAS",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, EscolasActivity.class);
                startActivity(intent);

            }
        });// Fim imgEscola

        imgIntinerario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("IntinerarioActivity","intinerario");
                Toast.makeText(v.getContext(), // <- Line changed
                        "INTINERARIO",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, IntinerarioActivity.class);
                startActivity(intent);
            }
        }); // Fim do imgIntinerario

        imgMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MapActivity","mapa");
                Toast.makeText(v.getContext(), // <- Line changed
                        "MAPA",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, MapsExActivity.class);
                startActivity(intent);
            }
        }); // FIm do imgMap

        imgUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("UsuarioActivity","mapa");
                Toast.makeText(v.getContext(), // <- Line changed
                        "USUÁRIO",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, UsuarioActivity.class);
                startActivity(intent);
            }
        }); // Fim do imgUsuario

        imgPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PaisActivity","mapa");
                Toast.makeText(v.getContext(), // <- Line changed
                        "PAIS",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(HomeActivity.this, PaisActivity.class);
                startActivity(intent);
            }
        }); // Fim do imgPais

    }//OnCreate

}//Class
