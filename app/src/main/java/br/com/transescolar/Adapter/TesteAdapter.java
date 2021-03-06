package br.com.transescolar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;

import br.com.transescolar.Model.Teste;
import br.com.transescolar.R;


public class TesteAdapter extends RecyclerView.Adapter<TesteAdapter.MyViewHolder> {

    private final Context context;
    private final List<Teste> nData;

    public TesteAdapter(List<Teste> testes, Context context) {
        this.context = context;
        this.nData = testes;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.linha_passageiro_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nome.setText(Integer.toString(nData.get(position).getIdT()));
        holder.escola.setText(nData.get(position).getTitleT());
        holder.periodo.setText(nData.get(position).getBodyT());
    }

    @Override
    public int getItemCount() {
        return nData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, escola, periodo;

        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.textView14);
            escola = itemView.findViewById(R.id.textView15);
            periodo = itemView.findViewById(R.id.textView16);

        }
    }
}












