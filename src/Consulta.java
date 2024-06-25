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
                    consultaPessoa();
                    break;
                case 2:
                    System.out.println("\nConsulta de tipo de objeto:");
                    consultaTipoObj();
                    break;
                case 3:
                    consultaObjeto();
                    break;
                case 4:
                    System.out.println("\nConsulta de manutenção:");
                    consultaManutencao();
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }
    public static Pessoa consultaPessoa(){
        PessoaDAO dao = new PessoaDAO(conn);
        System.out.println("\nConsulta de pessoa:\nInsira o nome:");
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
    public static Tipo_Obj consultaTipoObj(){
        Tipo_ObjDAO dao = new Tipo_ObjDAO(conn);
        System.out.println("\nInsira o tipo:");
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

    public static Objeto consultaObjeto(){
        ObjetoDAO dao = new ObjetoDAO(conn);
        System.out.println("\nConsulta de objeto:\nInsira o objeto:");
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
    public static Manutencao consultaManutencao(){
        ObjetoDAO objdao = new ObjetoDAO(conn);
        ManutencaoDAO dao = new ManutencaoDAO(conn);
        System.out.println("\nInsira o objeto:");
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
