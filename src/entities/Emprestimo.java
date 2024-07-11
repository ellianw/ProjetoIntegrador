package entities;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private int CODIGO;
    private Objeto OBJETO;
    private Pessoa PESSOA;
    private String DATA = "00/00/0000";
    private String ESTADO = "ATIVO";

    public Emprestimo(){

    }

    public Emprestimo(int CODIGO, Objeto OBJETO, Pessoa PESSOA, String DATA,String ESTADO) {
        this.CODIGO = CODIGO;
        this.OBJETO = OBJETO;
        this.PESSOA = PESSOA;
        this.DATA = DATA;
        this.ESTADO = ESTADO.toUpperCase();
    }

    public Emprestimo(Objeto OBJETO, Pessoa PESSOA) {
        this.OBJETO = OBJETO;
        this.PESSOA = PESSOA;
        LocalDate lcl = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DATA = lcl.format(formatter);
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

    public String getESTADO() {
        return ESTADO.toUpperCase();
    }

    public String getDATA() {
        return DATA;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO.toUpperCase();
    }

    @Override
    public String toString() {
        return "Manutenção nº "+CODIGO+": Objeto "+OBJETO.getNOME()+" emprestado para "+PESSOA.getNOME()+" em "+DATA+", estado atual: "+ESTADO;
    }
}
