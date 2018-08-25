package br.com.transescolar.Adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.transescolar.Model.Escolas;
import br.com.transescolar.R;


public class EscolaAdapter extends RecyclerView.Adapter<EscolaAdapter.MyViewHolder>{

    private List<Escolas> escola;
    private Context context;

    public EscolaAdapter(List<Escolas> escola, Context context) {
        this.escola = escola;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_escola_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nome.setText(escola.get(position).getNome());
        holder.endereco.setText(escola.get(position).getEndereco());
        holder.tell.setText(escola.get(position).getTell());
        //holder.imgEscolaL.setImageResource(escola.get(position).);
    }

    @Override
    public int getItemCount() {
        return escola.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, endereco, tell;
        //ImageView imgEscolaL;

        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.nomeText);
            endereco = itemView.findViewById(R.id.endeText);
            tell = itemView.findViewById(R.id.tellText);
            //imgEscolaL = itemView.findViewById(R.id.imgEscolaL);
        }


    }
}
