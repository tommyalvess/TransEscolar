package br.com.transescolar.Conexao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import br.com.transescolar.Activies.LoginActivity;
import br.com.transescolar.Model.Tios;

public class SharedPrefManager {


    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_CPF = "keycpf";
    private static final String KEY_APELIDO = "keyapelido";
    private static final String KEY_PLACA = "keyplaca";
    private static final String KEY_TELL = "keytell";
    private static final String KEY_SENHA = "keysenha";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //metodo que fez o user logar
    //esse metedo guarda as inf
    public void userLogin(Tios user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getNome());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_CPF, user.getCpf());
        editor.putString(KEY_APELIDO, user.getApelido());
        editor.putString(KEY_PLACA, user.getPlaca());
        editor.putString(KEY_TELL, user.getTell());
        editor.putString(KEY_SENHA, user.getSenha());
        editor.apply();
    }

    //vai checar se estão ou nao logado
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CPF, null) != null;
    }

    //metodo vai dar o user logado
    public Tios getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Tios(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_CPF, null),
                sharedPreferences.getString(KEY_APELIDO, null),
                sharedPreferences.getString(KEY_PLACA, null),
                sharedPreferences.getString(KEY_SENHA, null),
                sharedPreferences.getString(KEY_TELL, null)
        );
    }

    //metodo vai deslogar o user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
