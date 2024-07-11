package entities;

public class Manutencao {
    private Integer CODIGO;
    private Objeto OBJETO;
    private String SITUACAO = "ATIVO";

    public Manutencao(Integer CODIGO, Objeto OBJETO, String ATIVO) {
        this.CODIGO = CODIGO;
        this.OBJETO = OBJETO;
        this.SITUACAO = ATIVO;
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

    public String getSITUACAO() {
        return SITUACAO;
    }

    public void setEstado(String estado) {
        this.SITUACAO = estado.toUpperCase();
    }

    @Override
    public String toString() {
        return "Manutenção nº "+CODIGO+", objeto: "+OBJETO.getNOME()+", situação atual: "+SITUACAO;
    }
}
