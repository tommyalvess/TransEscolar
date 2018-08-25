package br.com.transescolar.Model;

import com.google.gson.annotations.SerializedName;

public class Teste {

    @SerializedName("viii") private String vii;
    @SerializedName("id") private int idT;
    @SerializedName("userId") private int userIdT;
    @SerializedName("title") private String titleT;
    @SerializedName("body") private String bodyT;
    @SerializedName("corpo") private String corpoT;
    @SerializedName("cadeira") private String cadeira;

    public Teste(String vii, int idT, int userIdT, String titleT, String bodyT, String corpoT, String cadeira) {
        this.vii = vii;
        this.idT = idT;
        this.userIdT = userIdT;
        this.titleT = titleT;
        this.bodyT = bodyT;
        this.corpoT = corpoT;
        this.cadeira = cadeira;
    }

    public String getVii() {
        return vii;
    }

    public void setVii(String vii) {
        this.vii = vii;
    }

    public int getIdT() {
        return idT;
    }

    public void setIdT(int idT) {
        this.idT = idT;
    }

    public int getUserIdT() {
        return userIdT;
    }

    public void setUserIdT(int userIdT) {
        this.userIdT = userIdT;
    }

    public String getTitleT() {
        return titleT;
    }

    public void setTitleT(String titleT) {
        this.titleT = titleT;
    }

    public String getBodyT() {
        return bodyT;
    }

    public void setBodyT(String bodyT) {
        this.bodyT = bodyT;
    }

    public String getCorpoT() {
        return corpoT;
    }

    public void setCorpoT(String corpoT) {
        this.corpoT = corpoT;
    }

    public String getCadeira() {
        return cadeira;
    }

    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }
}
