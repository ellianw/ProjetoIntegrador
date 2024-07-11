import dao.*;
import entities.*;

import java.sql.Connection;
import java.util.Scanner;

public class Consulta {
    static Scanner sc = new Scanner(System.in);
    static Connection conn = null;
    public static void menu(Connection conexao) {
        conn = conexao;
        String primTexto = "\nSistema de empréstimos de objetos\n" +
                "       Módulo de consultas\n"+
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
                    System.out.println("\nConsulta de pessoa:\nInsira o nome:");
                    sc.nextLine();
                    consultaPessoa(conn);
                    break;
                case 2:
                    System.out.println("\nConsulta de tipo de objeto:\nInsira o tipo:");
                    sc.nextLine();
                    consultaTipoObj(conn);
                    break;
                case 3:
                    System.out.println("\nConsulta de objeto:\nInsira o objeto:");
                    sc.nextLine();
                    consultaObjeto(conn);
                    break;
                case 4:
                    System.out.println("\nConsulta de manutenção:\nInsira o objeto:");
                    sc.nextLine();
                    consultaManutencao(conn);
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }
    public static Pessoa consultaPessoa(Connection conn){
        PessoaDAO dao = new PessoaDAO(conn);
        String nome = sc.nextLine();
        while(nome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            nome = sc.nextLine();
        }
        if (nome.equals("sair")) return null;
        if (!dao.pessoaExiste(nome)){
            System.out.println("Nenhuma pessoa cadastrada com esse nome!");
            return null;
        }
        Pessoa pessoa = dao.buscaNome(nome);
        System.out.println(pessoa.toString());
        return pessoa;
    }
    public static Tipo_Obj consultaTipoObj(Connection conn){
        Tipo_ObjDAO dao = new Tipo_ObjDAO(conn);
        //sc.nextLine();
        String tipo = sc.nextLine();
        while(tipo.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o tipo:");
            tipo = sc.nextLine();
        }
        if (tipo.equals("sair")) return null;
        if (dao.tipoInvalido(tipo)){
            System.out.println("O tipo de objeto informado não existe!");
            return null;
        }
        Tipo_Obj tipoobj = dao.buscaNome(tipo);
        System.out.println("Tipo de objeto: "+tipoobj.getTIPO()+", quantidade de cadastros: "+dao.qtdPorTipo(tipoobj));
        return tipoobj;
    }

    public static Objeto consultaObjeto(Connection conn){
        ObjetoDAO dao = new ObjetoDAO(conn);
        //sc.nextLine();
        String objNome = sc.nextLine();
        while(objNome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o objeto:");
            objNome = sc.nextLine();
        }
        if (objNome.equals("sair")) return null;
        if (!dao.objetoExiste(objNome)){
            System.out.println("Objeto não existe!");
            return null;
        }
        Objeto obj = dao.buscaNome(objNome);
        System.out.println(obj.toString());
        return obj;
    }
    public static Manutencao consultaManutencao(Connection conn){
        ObjetoDAO objdao = new ObjetoDAO(conn);
        ManutencaoDAO dao = new ManutencaoDAO(conn);
        //sc.nextLine();
        String objNome = sc.nextLine();
        while(objNome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o objeto:");
            objNome = sc.nextLine();
        }
        if (!objdao.objetoExiste(objNome)){
            System.out.println("Objeto não existe!");
            return null;
        }
        Objeto obj = objdao.buscaNome(objNome);
        if (!obj.getSITUACAO().equals("MANUTENCAO")){
            System.out.println("Objeto não se encontra em manutenção");
            return null;
        }
        Manutencao manutencao = dao.buscaObjeto(obj);
        System.out.println(manutencao.toString());
        return manutencao;
    }
}
