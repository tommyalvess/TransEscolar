package br.com.transescolar.transescolar.ListView;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import br.com.transescolar.transescolar.Model.Tios;
import br.com.transescolar.transescolar.R;

public class TiosList extends ArrayAdapter<Tios>{

    private List<Tios> users;
    private Activity context;
    DatabaseReference databaseReference;
    EditText editNome, editCpf2, editApelido2, editPlaca, editTell;


    public TiosList(@NonNull Activity context,
                    List<Tios> users,
                    DatabaseReference databaseReference,
                    EditText editNome) {
        super(context, R.layout.layout_user_list, users);
        this.context = context;
        this.users = users;
        this.databaseReference = databaseReference;
        this.editNome = editNome;
    }

//    public View getView(int pos, View view, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);
//
//        EditText editNome = (EditText) listViewItem.findViewById(R.id.editNome);
//        EditText editCpf2  = (EditText) listViewItem.findViewById(R.id.editCpf2);
//        EditText editApelido2  = (EditText) listViewItem.findViewById(R.id.editApelido2);
//        EditText editPlaca  = (EditText) listViewItem.findViewById(R.id.editPlaca);
//        EditText editTell  = (EditText) listViewItem.findViewById(R.id.editTell);
//
//        final  Tios user = users.get(pos);
//        editNome.setText(user.getNomeT());
//        editCpf2.setText(user.getCpfT());
//        editApelido2.setText(user.getApelido());
//        editPlaca.setText(user.getPlaca());
//        editTell.setText(user.getTellT());
//
//        return listViewItem;
//
//    }
}
