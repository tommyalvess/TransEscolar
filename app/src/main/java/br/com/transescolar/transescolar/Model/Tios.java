package br.com.transescolar.transescolar.Model;

public class Tios {

    private String id;
    private String nomeT;
    private String cpfT;
    private String apelido;
    private String placa;
    private String imgT;
    private String tellT;
    private Kids passageiros;

    public Tios(String id, String nome, String cpf, String apelido, String placa, String tell) {
    }

    public Tios(String id, String nomeT, String cpfT, String apelido, String placa, String imgT, String tellT, Kids passageiros) {
        this.id = id;
        this.nomeT = nomeT;
        this.cpfT = cpfT;
        this.apelido = apelido;
        this.placa = placa;
        this.imgT = imgT;
        this.tellT = tellT;
        this.passageiros = passageiros;
    }

    public Tios(String id, String nome) {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Kids getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(Kids passageiros) {
        this.passageiros = passageiros;
    }

    @Override
    public String toString() {
        return nomeT;
    }
}
