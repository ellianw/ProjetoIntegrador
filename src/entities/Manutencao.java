package entities;

public class Manutencao {
    private Integer CODIGO;
    private Objeto OBJETO;
    private boolean ATIVO = true;

    public Manutencao(Integer CODIGO, Objeto OBJETO, boolean ATIVO) {
        this.CODIGO = CODIGO;
        this.OBJETO = OBJETO;
        this.ATIVO = ATIVO;
    }

    public Manutencao(Objeto OBJETO) {
        this.OBJETO = OBJETO;
        OBJETO.setSITUACAO("MANUTENCAO");
    }

    public Manutencao(){
        CODIGO = null;
        OBJETO = null;
    }

    public Integer getCODIGO() {
        return CODIGO;
    }

    public Objeto getOBJETO() {
        return OBJETO;
    }

    public boolean isATIVO() {
        return ATIVO;
    }

    public void setEstado(boolean estado) {
        this.ATIVO = estado;
    }

    @Override
    public String toString() {
        String estado = "inativa";
        if (ATIVO) estado = "ativa";
        return "Manutenção nº "+CODIGO+", objeto: "+OBJETO.getNOME()+", situação atual: "+estado;
    }
}
