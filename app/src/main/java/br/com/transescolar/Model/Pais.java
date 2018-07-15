package br.com.transescolar.Model;

public class Pais {

    private int id;
    private String nomeP;
    private String cpfP;
    private String tellP;
    private String imgP;
    private String senha;
    private String email;
    private Kids filhos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
