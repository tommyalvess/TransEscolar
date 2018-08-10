package br.com.transescolar.Activies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class UsuarioActivity extends AppCompatActivity {

    private static final String TAG = UsuarioActivity.class.getSimpleName();
    TextView textNomeU, textEmailU, textCpfU, textApelidoU, texPlacaU, textTellU;
    CircleImageView imgPerfilT;
    private static String URL_READ = "http://192.168.1.33/apiapptransescolar/read_tios.php?apicall=findAll";
    private static String URL_UPLOAD = "http://192.168.1.33/apiapptransescolar/upload.php";
    String getId;
    String getCpf;
    private Bitmap bitmap;

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
        imgPerfilT = findViewById(R.id.imgPerfilT);


        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        String nNome = user.get(sessionManager.NAME);
        String nEmail = user.get(sessionManager.EMAIL);
        getCpf = user.get(sessionManager.CPF);
        String nApelido = user.get(sessionManager.APELIDO);
        String nPlaca = user.get(sessionManager.PLACA);
        String nTell = user.get(sessionManager.TELL);


        getSupportActionBar().setTitle(nApelido);     //Titulo para ser exibido na sua Action Bar em frente à seta

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

        imgPerfilT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosseFile();
            }
        });
    }

    //Pegar as infs do user
    private void getUserDetail(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Mensagem GetUserDetail", response.toString());
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray nameArray = json.names();
                            JSONArray valArray = json.toJSONArray( nameArray );
                            if (!json.optBoolean("1")){
                                for (int i = 0; i < valArray.length(); i++) {
                                    JSONObject object = valArray.getJSONObject(i);
                                    String id = object.getString("idTios").trim();
                                    String nome = object.getString("nome").trim();
                                    String email = object.getString("email").trim();
                                    String cpf = object.getString("cpf").trim();
                                    String apelido = object.getString("apelido").trim();
                                    String placa = object.getString("placa").trim();
                                    String tell = object.getString("tell").trim();
                                    String strImage = object.getString("img").trim();

                                    textNomeU.setText(nome);
                                    textEmailU.setText(email);
                                    textCpfU.setText(cpf);
                                    textApelidoU.setText(apelido);
                                    texPlacaU.setText(placa);
                                    textTellU.setText(tell);
                                    Picasso.get().load(strImage).into(imgPerfilT);

                                }
                            }else {
                                Toast.makeText(UsuarioActivity.this,json.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }catch ( JSONException e ) {
                            Log.e("JSON", "Error parsing JSON", e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UsuarioActivity.this, "Opss!! Sem Conexão a internet", Toast.LENGTH_SHORT).show();
                        Log.e("VolleyError", "Error", error);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("idTios", getId);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    //Fazer upload da foto
    private void chosseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione a imagem"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgPerfilT.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("Erro", "Upload", e);
            }

            UploadPicture(getId, getCpf, getStringImage(bitmap));

        }
    }

    //Fazer o Upload da foto
    private void UploadPicture(final String id, final String cpf, final String photo) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Mensagem Upload", response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(UsuarioActivity.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Erro", "Upload", e);
                            Toast.makeText(UsuarioActivity.this,"Opss! Tente Novamente!",Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UsuarioActivity.this,"Opss! Algo deu errado!",Toast.LENGTH_LONG).show();


                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idTios", id);
                params.put("cpf", cpf);
                params.put("img", photo);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }
}
