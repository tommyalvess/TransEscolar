package br.com.transescolar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tios {

    @SerializedName("response")
    private String Response;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nome")
    @Expose
    private String nomeT;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("cpfT")
    @Expose
    private String cpfT;

    @SerializedName("apelido")
    @Expose
    private String apelido;

    @SerializedName("placa")
    @Expose
    private String placa;

    @SerializedName("imgT")
    @Expose
    private String imgT;

    @SerializedName("tellT")
    @Expose
    private String tellT;

    @SerializedName("senhaT")
    @Expose
    private String senhaT;

    @SerializedName("locali")
    @Expose
    private String locali;


    public int getUid() {
        return id;
    }

    public void setUid(int uid) {
        this.id = uid;
    }

    public String getNomeT() {
        return nomeT;
    }

    public void setNomeT(String nomeT) {
        this.nomeT = nomeT;
    }

    public String getCpfT() {
        return cpfT;
    }

    public void setCpfT(String cpfT) {
        this.cpfT = cpfT;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getImgT() {
        return imgT;
    }

    public void setImgT(String imgT) {
        this.imgT = imgT;
    }

    public String getTellT() {
        return tellT;
    }

    public void setTellT(String tellT) {
        this.tellT = tellT;
    }

    public String getSenhaT() {
        return senhaT;
    }

    public void setSenhaT(String senhaT) {
        this.senhaT = senhaT;
    }

    public String getLocali() {
        return locali;
    }

    public void setLocali(String locali) {
        this.locali = locali;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
