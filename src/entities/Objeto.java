package entities;

enum Situacao {
    DISPONIVEL,
    EMPRESTADO,
    MANUTENCAO;
}

public class Objeto {
    private int CODIGO;
    private String NOME;
    private String TIPO;
    private Situacao SITUACAO = Situacao.DISPONIVEL;

    public Objeto(int CODIGO, String NOME, String TIPO) {
        this.CODIGO = CODIGO;
        this.NOME = NOME;
        this.TIPO = TIPO;
    }

    public Objeto(String NOME, String TIPO) {
        this.NOME = NOME;
        this.TIPO = TIPO;
    }

    public int getCODIGO() {
        return CODIGO;
    }

    public String getNOME() {
        return NOME;
    }

    public String getTIPO() {
        return TIPO;
    }

    public Situacao getSITUACAO() {
        return SITUACAO;
    }

    public void setSITUACAO(Situacao SITUACAO) {
        this.SITUACAO = SITUACAO;
    }
}
