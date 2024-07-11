package entities;

public class Pessoa {
    private Integer CODIGO = null;
    private String NOME;

    public Pessoa(){

    }

    public Pessoa(String NOME) {
        this.NOME = NOME;
    }

    public Pessoa(Integer CODIGO, String NOME){
        this.CODIGO = CODIGO;
        this.NOME = NOME;
    }

    public Integer getCODIGO() {
        return CODIGO;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String nome){
        this.NOME = nome;
    }

    @Override
    public String toString() {
        return "Nome: "+NOME+", código: "+CODIGO;
    }
}
