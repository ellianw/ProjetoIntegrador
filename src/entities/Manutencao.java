package entities;

public class Manutencao {
    private int CODIGO;
    private Objeto OBJETO;
    private boolean ATIVO = true;

    public Manutencao(Objeto OBJETO, boolean SITUACAO) {
        this.OBJETO = OBJETO;
        this.ATIVO = SITUACAO;
        OBJETO.setSITUACAO(Situacao.MANUTENCAO);
    }
    public Manutencao(Objeto OBJETO) {
        this.OBJETO = OBJETO;
        OBJETO.setSITUACAO(Situacao.MANUTENCAO);
    }
    public Manutencao(String NomeObjeto) {
        this.OBJETO = new Objeto(NomeObjeto,"");
        OBJETO.setSITUACAO(Situacao.MANUTENCAO);
    }

    public int getCODIGO() {
        return CODIGO;
    }

    public Objeto getOBJETO() {
        return OBJETO;
    }

    public boolean isATIVO() {
        return ATIVO;
    }
}
