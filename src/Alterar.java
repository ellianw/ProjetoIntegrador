import dao.*;
import entities.*;

import java.sql.Connection;
import java.util.Scanner;


public class Alterar {
    static Scanner sc = new Scanner(System.in);
    static Connection conn;
    public static void menu(Connection conexao){
        conn = conexao;
        String primTexto = "\nSistema de empréstimos de objetos\n" +
                "       Módulo de alteração\n"+
                "--------------------------------------\n" +
                "1 - Pessoas\n" +
                "2 - Tipos de objetos\n" +
                "3 - Objetos\n" +
                "4 - Manutenção\n" +
                "5 - Sair\n" +
                "Escolha a sua opção:";
        System.out.println(primTexto);
        int opcao = sc.nextInt();
        while (opcao !=5){
            switch (opcao){
                case 1:
                    alterarPessoa();
                    break;
                case 2:
                    alterarTipoObj();
                    break;
                case 3:
                    alterarObjeto();
                    break;
                case 4:
                    alterarManutencao();
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }

    public static void alterarPessoa(){
        PessoaDAO dao = new PessoaDAO(conn);
        Pessoa pessoa = Consulta.consultaPessoa(conn);
        if (pessoa == null) return;
        System.out.println("Insira o nome para alteração:");
        String nome = sc.nextLine();
        while(nome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            nome = sc.nextLine();
        }
        if (nome.equals("sair")) return;
        if (dao.pessoaExiste(nome)) {
            System.out.println("Uma pessoa com esse nome já existe!");
            return;
        }
        pessoa.setNOME(nome.toUpperCase());
        dao.atualizarPessoa(pessoa);
    }

    public static void alterarTipoObj(){
        Tipo_ObjDAO dao = new Tipo_ObjDAO(conn);
        Tipo_Obj tipoObj = Consulta.consultaTipoObj(conn);
        if (tipoObj ==null) return;
        System.out.println("Insira o nome para alteração:");
        String nome = sc.nextLine();
        while(nome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            nome = sc.nextLine();
        }
        if (nome.equals("sair")) return;
        if (!dao.tipoInvalido(nome)) {
            System.out.println("Esse tipo de objeto já existe!");
            return;
        }
        tipoObj.setTIPO(nome);
        dao.atualizarTipo(tipoObj);
    }

    public static void alterarObjeto(){
        ObjetoDAO dao = new ObjetoDAO(conn);
        Objeto obj = Consulta.consultaObjeto(conn);
        if (obj ==null) return;
        System.out.println("Insira o nome para alteração:");
        String nome = sc.nextLine();
        while(nome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            nome = sc.nextLine();
        }
        if (nome.equals("sair")) return;
        //System.out.println("Insira o tipo para alteração:");
        Tipo_Obj tipo = Consulta.consultaTipoObj(conn);
        if (tipo==null) return;
        obj.setNOME(nome);
        obj.setTIPO(tipo);
        dao.atualizarObjeto(obj);
    }

    public static void alterarManutencao(){
        ManutencaoDAO dao = new ManutencaoDAO(conn);
        ObjetoDAO objdao = new ObjetoDAO(conn);
        Manutencao manutencao = Consulta.consultaManutencao(conn);
        if (manutencao ==null) return;
        System.out.println("Deseja alterar o estado da manutenção?(Sim/Não)");
        String estado = sc.nextLine();
        while(estado.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            estado = sc.nextLine();
        }
        if (estado.equals("sair")) return;
        estado = estado.toUpperCase();
        if (estado.equals("N") || estado.equals("NAO")) return;
        if (!estado.equals("S") && !estado.equals("SIM")) {
            System.out.println("Opção inválida!");
            return;
        }
        manutencao.setEstado(false);
        Objeto obj = manutencao.getOBJETO();
        obj.setSITUACAO("DISPONIVEL");
        objdao.atualizarSituacao(obj);
        dao.atualizarManutencao(manutencao);
    }
}
