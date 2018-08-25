package br.com.transescolar.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.transescolar.Model.Escolas;
import br.com.transescolar.Model.Kids;
import br.com.transescolar.R;

public class PassAdapter extends RecyclerView.Adapter<PassAdapter.MyViewHolder>{

    private List<Kids> kids;
    private List<Escolas> escolas;
    private Context context;

    public PassAdapter(List<Kids> kids, Context context) {
        this.kids = kids;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, escola, periodo;
        ImageView img;

        public MyViewHolder(View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.textView14);
            escola = itemView.findViewById(R.id.textView15);
            periodo = itemView.findViewById(R.id.textView16);
            img = itemView.findViewById(R.id.imageView2);
            //Picasso.get().load(strImage).into(imageView2);
        }
    }
}
