package br.com.transescolar.transescolar.Activies;

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

import br.com.transescolar.transescolar.Model.Tios;
import br.com.transescolar.transescolar.R;

import static br.com.transescolar.transescolar.R.id.btnSaveCadastro;
import static com.google.android.gms.stats.internal.G.netStats.enabled;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome, editCpf, editApelido, editPlaca, editTell, editSenha;
    Button btnSaveCadastro;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private static final String TAG = "CadastroActivity";
    private static final String REQUIRED = "Required";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Cadastro");     //Titulo para ser exibido na sua Action Bar em frente à seta

        inicializarFirebase();

        editNome = findViewById(R.id.editNome);
        editCpf = findViewById(R.id.editCpf2);
        editApelido = findViewById(R.id.editApelido2);
        editPlaca = findViewById(R.id.editPlaca);
        editTell = findViewById(R.id.editTell);
        editSenha = findViewById(R.id.editSenha);

        btnSaveCadastro = findViewById(R.id.btnSaveCadastro);

        btnSaveCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsuario();
            }
        });

    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(CadastroActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private void  addUsuario(){

        final String nome = editNome.getText().toString();
        final String cpf = editCpf.getText().toString();
        final String apelido = editApelido.getText().toString();
        final String placa = editPlaca.getText().toString();
        final String tell = editTell.getText().toString();
        final String senha = editTell.getText().toString();

        // Validar campo vazio
        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(cpf) || TextUtils.isEmpty(apelido) || TextUtils.isEmpty(placa) || TextUtils.isEmpty(tell) || TextUtils.isEmpty(senha)) {
            editNome.setError(REQUIRED);
            editCpf.setError(REQUIRED);
            editApelido.setError(REQUIRED);
            editPlaca.setError(REQUIRED);
            editTell.setError(REQUIRED);
            editSenha.setError(REQUIRED);
            return;
        }else {
            Tios objTios = new Tios();

            objTios.setUid(UUID.randomUUID().toString());
            objTios.setNomeT(editNome.getText().toString());
            objTios.setCpfT(editCpf.getText().toString());
            objTios.setApelido(editApelido.getText().toString());
            objTios.setPlaca(editPlaca.getText().toString());
            objTios.setTellT(editTell.getText().toString());
            objTios.setSenhaT(editSenha.getText().toString());

            databaseReference.child("Tios").child(objTios.getUid()).setValue(objTios);

            limparCampos();
            //displaying a success toast
            Toast.makeText(this, "Usuario added", Toast.LENGTH_LONG).show();
        }


         //Evitar multiplos dados
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

    }


    private void setEditingEnabled(boolean enabled) {
        editNome.setEnabled(enabled);
        if (enabled) {
            btnSaveCadastro.setVisibility(View.VISIBLE);
        } else {
            btnSaveCadastro.setVisibility(View.GONE);
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

    private void limparCampos() {
        editNome.setText("");
    }


}
