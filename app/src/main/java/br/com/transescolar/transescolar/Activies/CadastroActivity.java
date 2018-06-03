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

import br.com.transescolar.transescolar.Model.Tios;
import br.com.transescolar.transescolar.R;

import static br.com.transescolar.transescolar.R.id.btnSaveCadastro;
import static com.google.android.gms.stats.internal.G.netStats.enabled;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome;
    Button btnSaveCadastro;
    private DatabaseReference databaseReference;

    private static final String TAG = "CadastroActivity";
    private static final String REQUIRED = "Required";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editNome = findViewById(R.id.editNome);
        btnSaveCadastro = findViewById(R.id.btnSaveCadastro);

        btnSaveCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUsuario();
            }
        });

    }

    private void  addUsuario(){

        final String title = editNome.getText().toString();

        // Validar campo vazio
        if (TextUtils.isEmpty(title)) {
            editNome.setError(REQUIRED);
            return;
        }


        // Evitar multiplos dados
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





}
