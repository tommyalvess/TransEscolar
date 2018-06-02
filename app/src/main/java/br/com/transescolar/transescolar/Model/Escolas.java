package br.com.transescolar.transescolar.Model;

public class Escolas {

    private int id;
    private String nome;
    private String end;

    public Escolas() {
    }

    public Escolas(int id, String nome, String end) {
        this.id = id;
        this.nome = nome;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
