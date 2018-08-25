package br.com.transescolar.Teste;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import br.com.transescolar.Activies.PassageirosActivity;
import br.com.transescolar.Model.Kids;
import br.com.transescolar.R;

public class TestAdapterKids extends RecyclerView.Adapter<TestAdapterKids.MyViewHolder> {
    RequestOptions options;
    private final Context context;
    private final List<Filhos> nData;

    public TestAdapterKids(Context mContext, List lst) {


        this.context = mContext;
        this.nData = lst;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.kids)
                .error(R.drawable.kids);

    }

    @NonNull
    @Override
    public TestAdapterKids.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.linha_passageiro_list,parent,false);
        // click listener here
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapterKids.MyViewHolder holder, int position) {
        holder.tvname.setText(nData.get(position).getNome());
        holder.tv_rate.setText(nData.get(position).getNm_escola());
        holder.tvstudio.setText(nData.get(position).getPeriodo());

        // load image from the internet using Glide
        Glide.with(context).load(nData.get(position).getImg()).apply(options).into(holder.AnimeThumbnail);

    }

    @Override
    public int getItemCount() {
        return nData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvname,tv_rate,tvstudio;
        ImageView AnimeThumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.textView14);
            tvstudio = itemView.findViewById(R.id.textView15);
            tv_rate = itemView.findViewById(R.id.textView16);
            AnimeThumbnail = itemView.findViewById(R.id.imageView2);
        }
    }
}
