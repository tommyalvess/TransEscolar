package br.com.transescolar.Model;

import com.google.gson.annotations.SerializedName;

public class Kids {

    @SerializedName("idKids") private int idKids;
    @SerializedName("idTios") private int idTios;
    @SerializedName("nome") private String nome;
    @SerializedName("periodo") private String periodo;
    @SerializedName("img") private String img;
    @SerializedName("nm_escola") private String nm_escola;
    @SerializedName("dt_nas") private String dt_nas;
    @SerializedName("end_principal") private String end_principal;
    //@SerializedName("idTios") private Tios tio;
    //@SerializedName("idEscola") private Escolas escola;
    //@SerializedName("idPais") private Pais pai;


    public Kids(int idKids, int idTios, String nome, String periodo, String img, String nm_escola, String dt_nas, String end_principal) {
        this.idKids = idKids;
        this.idTios = idTios;
        this.nome = nome;
        this.periodo = periodo;
        this.img = img;
        this.nm_escola = nm_escola;
        this.dt_nas = dt_nas;
        this.end_principal = end_principal;
    }

    public int getIdKids() {
        return idKids;
    }

    public void setIdKids(int idKids) {
        this.idKids = idKids;
    }

    public int getIdTios() {
        return idTios;
    }

    public void setIdTios(int idTios) {
        this.idTios = idTios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNm_escola() {
        return nm_escola;
    }

    public void setNm_escola(String nm_escola) {
        this.nm_escola = nm_escola;
    }

    public String getDt_nas() {
        return dt_nas;
    }

    public void setDt_nas(String dt_nas) {
        this.dt_nas = dt_nas;
    }

    public String getEnd_principal() {
        return end_principal;
    }

    public void setEnd_principal(String end_principal) {
        this.end_principal = end_principal;
    }
}

