package br.com.transescolar.transescolar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.transescolar.transescolar.Model.Teste;
import br.com.transescolar.transescolar.R;

public class TesteAdapter extends ArrayAdapter<Teste> {

    private final Context context;
    private final List<Teste> elementos;

    public TesteAdapter(Context context, List<Teste> elementos) {
        super(context, R.layout.linha_teste, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linha_teste, parent, false);

        TextView user = (TextView) rowView.findViewById(R.id.testUser);
        TextView titulo = (TextView) rowView.findViewById(R.id.testTitulo);
        TextView des = (TextView) rowView.findViewById(R.id.testeDes);

        user.setText(Integer.toString(elementos.get(position).getUserId()));
        titulo.setText(elementos.get(position).getTitle());
        des.setText(elementos.get(position).getBody());

        return rowView;
    }
}
