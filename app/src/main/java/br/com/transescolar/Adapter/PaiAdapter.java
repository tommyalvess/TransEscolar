package br.com.transescolar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import br.com.transescolar.Model.Pais;
import br.com.transescolar.Model.Teste;
import br.com.transescolar.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class PaiAdapter extends RecyclerView.Adapter<PaiAdapter.MyViewHolder>{

    private final Context context;
    private final List<Pais> nData;

    public PaiAdapter(List<Pais> testes, Context context) {
        this.context = context;
        this.nData = testes;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.linha_pais_list,parent,false);
        return new PaiAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nome.setText(nData.get(position).getNome());
        holder.email.setText(nData.get(position).getEmail());
        holder.tell.setText(nData.get(position).getTell());
    }

    @Override
    public int getItemCount() {
        return nData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, tell, email;

        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.nomeP);
            tell = itemView.findViewById(R.id.tellP);
            email = itemView.findViewById(R.id.emilP);

        }
    }
}
