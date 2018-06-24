package br.com.transescolar.transescolar.Activies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.transescolar.transescolar.API.ITios;
import br.com.transescolar.transescolar.Model.Tios;
import br.com.transescolar.transescolar.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.transescolar.transescolar.R.id.btnSaveCadastro;
import static com.google.android.gms.stats.internal.G.netStats.enabled;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome, editCpf, editApelido, editPlaca, editTell, editSenha;
    Button btnSaveCadastro;
    ProgressBar progressBar;




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
        btnSaveCadastro = (Button)findViewById(R.id.btnSaveCadastro);

        progressBar.setVisibility(View.VISIBLE);

        btnSaveCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                btnSaveCadastro.setVisibility(View.INVISIBLE);

                Tios objTio = new Tios();
                objTio.setNomeT(editNome.getText().toString());
                objTio.setCpfT(editCpf.getText().toString());
                objTio.setApelido(editApelido.getText().toString());
                objTio.setPlaca(editPlaca.getText().toString());
                objTio.setTellT(editTell.getText().toString());
                objTio.setSenhaT(editSenha.getText().toString());

                ITios iTios = ITios.retrofit.create(ITios.class);
                final Call<Void> call = iTios.inseriTios(objTio);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (progressBar.isShown()){
                           progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(CadastroActivity.this, "Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (progressBar.isShown()){
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(CadastroActivity.this, "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

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
