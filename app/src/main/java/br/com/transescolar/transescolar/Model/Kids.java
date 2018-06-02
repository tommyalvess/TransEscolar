package br.com.transescolar.transescolar.Model;

public class Kids {

    private String nomeK;
    private String dt_nas;
    private String end_p;
    private Escolas escola;
    private String periodo;
    private String imgK;
    private Tios tio;

    public Kids() {
    }

    public Kids(String nomeK, String dt_nas, String end_p, Escolas escola, String periodo, String imgK, Tios tio) {
        this.nomeK = nomeK;
        this.dt_nas = dt_nas;
        this.end_p = end_p;
        this.escola = escola;
        this.periodo = periodo;
        this.imgK = imgK;
        this.tio = tio;
    }

    public String getNomeK() {
        return nomeK;
    }

    public void setNomeK(String nomeK) {
        this.nomeK = nomeK;
    }

    public String getDt_nas() {
        return dt_nas;
    }

    public void setDt_nas(String dt_nas) {
        this.dt_nas = dt_nas;
    }

    public String getEnd_p() {
        return end_p;
    }

    public void setEnd_p(String end_p) {
        this.end_p = end_p;
    }

    public Escolas getEscola() {
        return escola;
    }

    public void setEscola(Escolas escola) {
        this.escola = escola;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getImgK() {
        return imgK;
    }

    public void setImgK(String imgK) {
        this.imgK = imgK;
    }

    public Tios getTio() {
        return tio;
    }

    public void setTio(Tios tio) {
        this.tio = tio;
    }
}

