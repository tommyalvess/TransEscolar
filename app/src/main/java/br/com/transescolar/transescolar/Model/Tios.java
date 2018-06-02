package br.com.transescolar.transescolar.Model;

public class Tios {

    private String nome;
    private String cpf;
    private String apelido;
    private String placa;
    private String img;
    private String tell;
    private Kids passageiros;

    public Tios() {
    }

    public Tios(String nome, String cpf, String apelido, String placa, String img, String tell, Kids passageiros) {
        this.nome = nome;
        this.cpf = cpf;
        this.apelido = apelido;
        this.placa = placa;
        this.img = img;
        this.tell = tell;
        this.passageiros = passageiros;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public Kids getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(Kids passageiros) {
        this.passageiros = passageiros;
    }
}
