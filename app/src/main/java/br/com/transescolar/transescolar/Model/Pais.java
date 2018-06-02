package br.com.transescolar.transescolar.Model;

public class Pais {

    private String nomeP;
    private String cpfP;
    private String tellP;
    private String imgP;
    private Kids filhos;

    public Pais() {
    }

    public Pais(String nomeP, String cpfP, String tellP, String imgP, Kids filhos) {
        this.nomeP = nomeP;
        this.cpfP = cpfP;
        this.tellP = tellP;
        this.imgP = imgP;
        this.filhos = filhos;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public String getCpfP() {
        return cpfP;
    }

    public void setCpfP(String cpfP) {
        this.cpfP = cpfP;
    }

    public String getTellP() {
        return tellP;
    }

    public void setTellP(String tellP) {
        this.tellP = tellP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    public Kids getFilhos() {
        return filhos;
    }

    public void setFilhos(Kids filhos) {
        this.filhos = filhos;
    }

    @Override
    public String toString() {
        return nomeP;

    }
}
