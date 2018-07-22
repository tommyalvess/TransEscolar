package br.com.transescolar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tios {
    private String nome, email, cpf, apelido, placa, tell;
    private int id;

    public Tios(int id, String nome, String email, String cpf, String apelido, String placa, String tell) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.apelido = apelido;
        this.placa = placa;
        this.tell = tell;

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getApelido() {
        return apelido;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTell() {
        return tell;
    }
}
