package br.com.transescolar.Model;

import com.google.gson.annotations.SerializedName;

public class Escolas {

    @SerializedName("idEscola") private int id;
    @SerializedName("nome") private String nome;
    @SerializedName("endereco") private String endereco;
    @SerializedName("tell") private String tell;
    @SerializedName("img") private int img;
    @SerializedName("periodo") private String periodo;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTell() {
        return tell;
    }

    public int getImg() {
        return img;
    }

    public String getPeriodo() {
        return periodo;
    }
}
