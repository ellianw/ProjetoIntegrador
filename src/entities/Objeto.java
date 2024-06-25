package entities;


public class Objeto {
    private Integer CODIGO;
    private String NOME;
    private Tipo_Obj TIPO;
    private String SITUACAO = "DISPONIVEL";

    public Objeto(String NOME, Tipo_Obj TIPO) {
        this.NOME = NOME;
        this.TIPO = TIPO;
    }

    public Objeto(Integer CODIGO, String NOME, Tipo_Obj TIPO, String SITUACAO) {
        this.CODIGO = CODIGO;
        this.NOME = NOME;
        this.TIPO = TIPO;
        this.SITUACAO = SITUACAO;
    }

    public Objeto(){
        CODIGO = null;
        NOME = null;
        TIPO = null;
        SITUACAO = null;
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

    public Tipo_Obj getTIPO() {
        return TIPO;
    }

    public void setTIPO(Tipo_Obj tipo){
        this.TIPO = tipo;
    }

    public String getSITUACAO() {
        return SITUACAO;
    }

    public void setSITUACAO(String SITUACAO) {
        this.SITUACAO = SITUACAO;
    }

    @Override
    public String toString() {
        return "Objeto: "+NOME+", código "+CODIGO+", tipo: "+TIPO.getTIPO()+", situação atual: "+SITUACAO;
    }
}
