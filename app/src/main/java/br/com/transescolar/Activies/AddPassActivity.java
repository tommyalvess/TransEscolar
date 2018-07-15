package br.com.transescolar.Activies;


import android.support.v4.app.NavUtils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;


import br.com.transescolar.R;


public class AddPassActivity extends AppCompatActivity {

    private static final String REQUIRED = "Required";


    EditText editNomeP, editIdadeP, editEndP,editEscolaP, editPeriodoP;
    Button btnSalvarP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pass);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Add Passageiros");     //Titulo para ser exibido na sua Action Bar em frente à seta




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
