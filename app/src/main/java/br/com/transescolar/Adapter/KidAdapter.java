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


import br.com.transescolar.Model.Kids;
import br.com.transescolar.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class KidAdapter extends RecyclerView.Adapter<KidAdapter.MyViewHolder> {

    RequestOptions options;
    private final Context context;
    private final List<Kids> nData;


    public KidAdapter(List<Kids> filhos, Context context) {
        this.context = context;
        this.nData = filhos;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.kids)
                .error(R.drawable.kids);
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
        holder.nome.setText(nData.get(position).getNome());
        holder.escola.setText(nData.get(position).getNm_escola());
        holder.periodo.setText(nData.get(position).getPeriodo());
        Glide.with(context).load(nData.get(position).getImg()).apply(options).into(holder.img);

    }


    @Override
    public int getItemCount() {
        return nData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, escola, periodo;
        CircleImageView img;

        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.textView14);
            escola = itemView.findViewById(R.id.textView15);
            periodo = itemView.findViewById(R.id.textView16);
            img = itemView.findViewById(R.id.imgPass);

        }
    }


}
