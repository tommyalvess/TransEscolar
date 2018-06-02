package br.com.transescolar.transescolar.Activies;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import br.com.transescolar.transescolar.R;

public class PaisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pais);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Pais");     //Titulo para ser exibido na sua Action Bar em frente à seta
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Id correspondente ao botão Up/Home da actionbar
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                //Se essa atividade faz parte da tarefa do app
                //navegamos para seu parente logico.
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    //Se a atividade não faz parte do aplicativo, criamos uma nova tarefa
                    // para navegação com a pilha de volta sintetizada.
                    TaskStackBuilder.create(this)
                            // Adiciona todas atividades parentes na pilha de volta
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
