package br.com.transescolar.transescolar.Activies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.transescolar.transescolar.Model.Tios;
import br.com.transescolar.transescolar.R;

public class CadastroActivity extends AppCompatActivity {

    EditText editNome, editCpf2, editApelido2, editPlaca, editTell;
    Button btnSaveCadastro;
    List<Tios> users;

    private static String userId;

    DatabaseReference databaseReference;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        editNome = (EditText) findViewById(R.id.editNome);
        editCpf2 = (EditText) findViewById(R.id.editCpf2);
        editApelido2 = (EditText) findViewById(R.id.editApelido2);
        editPlaca = (EditText) findViewById(R.id.editPlaca);
        editTell = (EditText) findViewById(R.id.editTell);


    }













}
