package entities;

public class Emprestimo {
    private int CODIGO;
    private Objeto OBJETO;
    private Pessoa PESSOA;
    private boolean SITUACAO = true;

    public Emprestimo(Objeto OBJETO, Pessoa PESSOA) {
        this.OBJETO = OBJETO;
        this.PESSOA = PESSOA;
    }

    public int getCODIGO() {
        return CODIGO;
    }

    public Objeto getOBJETO() {
        return OBJETO;
    }

    public Pessoa getPESSOA() {
        return PESSOA;
    }

    public boolean getESTADO() {
        return SITUACAO;
    }
}
