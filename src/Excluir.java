import dao.*;
import entities.*;

import java.sql.Connection;
import java.util.Scanner;

public class Excluir {
    static Scanner sc = new Scanner(System.in);
    static Connection conn = null;
    public static void menu(Connection conexao){
        conn = conexao;
        String primTexto = "\nSistema de empréstimos de objetos\n" +
                "       Módulo de exclusão\n"+
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
                    excluirPessoa();
                    break;
                case 2:
                    excluirTipoObj();
                    break;
                case 3:
                    excluirObjeto();
                    break;
                case 4:
                    excluirManutencao();
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }

    public static void excluirPessoa(){
        PessoaDAO dao = new PessoaDAO(conn);
        Pessoa pessoa = Consulta.consultaPessoa(conn);
        if (pessoa == null) return;
        System.out.println("Confirma a exclusão de "+pessoa.getNOME()+"?\nEssa ação deletará juntamente todos os registros de empréstimos registrados para essa pessoa e não pode ser desfeita!(Sim/Nao)");
        if(exclusaoNegada()) return;
        dao.excluirPessoa(pessoa);
        System.out.println("Pessoa excluida!");
    }

    public static void excluirTipoObj(){
        Tipo_ObjDAO dao = new Tipo_ObjDAO(conn);
        Tipo_Obj tipoObj = Consulta.consultaTipoObj(conn);
        if (tipoObj ==null) return;
        System.out.println("Confirma a exclusão de "+tipoObj.getTIPO()+"?\nEssa ação deletará juntamente todos os registros de objetos registrados com esse tipo e não pode ser desfeita!(Sim/Nao)");
        if(exclusaoNegada()) return;
        dao.excluirTipo(tipoObj);
        System.out.println("Tipo de objeto excluido!");
    }

    public static void excluirObjeto(){
        ObjetoDAO dao = new ObjetoDAO(conn);
        Objeto obj = Consulta.consultaObjeto(conn);
        if (obj ==null) return;
        System.out.println("Confirma a exclusão de "+ obj.getNOME()+"?\nEssa ação deletará juntamente todos os registros de manutenção registrados com esse objeto e não pode ser desfeita!(Sim/Nao)");
        if(exclusaoNegada()) return;
        dao.excluirObjeto(obj);
        System.out.println("Objeto excluido!");
    }

    public static void excluirManutencao(){
        ManutencaoDAO dao = new ManutencaoDAO(conn);
        Manutencao manutencao = Consulta.consultaManutencao(conn);
        ObjetoDAO objdao = new ObjetoDAO(conn);
        if (manutencao ==null) return;
        System.out.println("Confirma a exclusão de "+ manutencao.getOBJETO().getNOME()+"?\nEssa ação não pode ser desfeita!(Sim/Nao)");
        if(exclusaoNegada()) return;
        manutencao.getOBJETO().setSITUACAO("DISPONIVEL");
        dao.excluirManutencao(manutencao);
        objdao.atualizarObjeto(manutencao.getOBJETO());;
        System.out.println("Manutenção excluida!");
    }

    public static boolean exclusaoNegada(){
        String estado = sc.nextLine();
        while(estado.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira uma opção válida:");
            estado = sc.nextLine();
        }
        estado = estado.toUpperCase();
        if (estado.equals("N") || estado.equals("NAO")) return true;
        if (!estado.equals("S") && !estado.equals("SIM")) {
            System.out.println("Opção inválida!");
            return true;
        }
        return false;
    }
}
