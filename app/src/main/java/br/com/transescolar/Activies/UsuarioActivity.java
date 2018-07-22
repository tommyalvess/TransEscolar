package br.com.transescolar.Activies;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import br.com.transescolar.Conexao.SharedPrefManager;
import br.com.transescolar.Model.Tios;
import br.com.transescolar.R;


public class UsuarioActivity extends AppCompatActivity {

    TextView textNomeU, textEmailU, textCpfU, textApelidoU, texPlacaU, textTellU;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        //if the user is not logged in
        //starting the login activity
//        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, LoginActivity.class));
//        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Usuários");     //Titulo para ser exibido na sua Action Bar em frente à seta

        textNomeU = (TextView) findViewById(R.id.textNomeU);
        textEmailU = (TextView) findViewById(R.id.textEmailU);
        textCpfU = (TextView) findViewById(R.id.textCpfU);
        textApelidoU = (TextView) findViewById(R.id.textApelidoU);
        texPlacaU = (TextView) findViewById(R.id.texPlacaU);
        textTellU = (TextView) findViewById(R.id.textTellU);

        Tios user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textNomeU.setText("Nome: " + user.getNome());
        textEmailU.setText("Email: " + user.getEmail());
        textCpfU.setText("CPF: " + user.getCpf());
        textApelidoU.setText("Apelido: " + user.getApelido());
        texPlacaU.setText("Placa: " + user.getPlaca());
        textTellU.setText("Telefone: " + user.getTell());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
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
}
