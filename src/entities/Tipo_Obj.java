package entities;

public class Tipo_Obj {
    private Integer CODIGO;
    private String TIPO;

    public Tipo_Obj(String TIPO) {
        this.TIPO = TIPO.toUpperCase();
    }

    public Tipo_Obj(int CODIGO, String TIPO) {
        this.CODIGO = CODIGO;
        this.TIPO = TIPO.toUpperCase();
    }

    public Tipo_Obj(){
        CODIGO = null;
        TIPO = null;
    }

    public Integer getCODIGO() {
        return CODIGO;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String tipo){
        this.TIPO = tipo;
    }

    @Override
    public String toString() {
        return "Tipo: "+TIPO+" Código: "+CODIGO;
    }
}
