package entities;

public class Pessoa {
    private int CODIGO;
    private String NOME;

    public Pessoa(String NOME) {
        this.NOME = NOME;
    }

    public int getCODIGO() {
        return CODIGO;
    }

    public String getNOME() {
        return NOME;
    }
}
