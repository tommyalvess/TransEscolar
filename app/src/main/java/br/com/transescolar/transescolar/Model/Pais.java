package br.com.transescolar.transescolar.Model;

public class Pais {

    private String nome;
    private String cpf;
    private String tell;
    private String img;
    private Kids filhos;

    public Pais() {
    }

    public Pais(String nome, String cpf, String tell, String img, Kids filhos) {
        this.nome = nome;
        this.cpf = cpf;
        this.tell = tell;
        this.img = img;
        this.filhos = filhos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Kids getFilhos() {
        return filhos;
    }

    public void setFilhos(Kids filhos) {
        this.filhos = filhos;
    }
}
