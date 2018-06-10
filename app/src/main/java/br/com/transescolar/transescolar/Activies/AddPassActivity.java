package br.com.transescolar.transescolar.Activies;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.transescolar.transescolar.Model.Kids;
import br.com.transescolar.transescolar.R;

public class AddPassActivity extends AppCompatActivity {

    private static final String REQUIRED = "Required";


    EditText editNomeP, editIdadeP, editEndP,editEscolaP, editPeriodoP;
    Button btnSalvarP;

    DatabaseReference databasePassageiros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pass);

        databasePassageiros = FirebaseDatabase.getInstance().getReference("Kids");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Add Passageiros");     //Titulo para ser exibido na sua Action Bar em frente à seta


        btnSalvarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPassageiros();
            }
        });


    }

    private void addPassageiros() {
        String nome = editNomeP.getText().toString().trim();

        // Validar campo vazio
        if (TextUtils.isEmpty(nome)) {
            editNomeP.setError(REQUIRED);
            return;
        }


        // Evitar multiplos dados
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        if(!TextUtils.isEmpty(nome)){

            String id = databasePassageiros.push().getKey();

            Kids objKids = new Kids(id, nome);

            databasePassageiros.child(id).setValue(objKids);

            Toast.makeText(this, "Adicionado!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setEditingEnabled(boolean enabled) {
        editNomeP.setEnabled(enabled);
        if (enabled) {
            btnSalvarP.setVisibility(View.VISIBLE);
        } else {
            btnSalvarP.setVisibility(View.GONE);
        }
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
