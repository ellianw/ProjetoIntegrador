import dao.*;
import entities.*;

import java.sql.Connection;
import java.util.Scanner;

public class Emprestar {
    static Scanner sc = new Scanner(System.in);
    static Connection conn;
    public static void menu(Connection conexao){
        conn = conexao;
        String primTexto = "\nSistema de empréstimos de objetos\n" +
                "       Módulo de empréstimos\n"+
                "--------------------------------------\n" +
                "1 - Realizar Empréstimo\n" +
                "2 - Devolver objeto\n" +
                "3 - Sair\n" +
                "Escolha a sua opção:";
        System.out.println(primTexto);
        int opcao = sc.nextInt();
        while (opcao !=3){
            switch (opcao){
                case 1:
                    realizarEmprestimo();
                    break;
                case 2:
                    devolverObjeto();
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }

    public static void realizarEmprestimo(){
        ObjetoDAO objdao = new ObjetoDAO(conn);
        EmprestimoDAO dao = new EmprestimoDAO(conn);
        System.out.println("Insira o objeto a ser emprestado:");
        sc.nextLine();
        Objeto objeto = Consulta.consultaObjeto(conn);
        if (objeto == null) return;
        if (!objeto.getSITUACAO().equals("DISPONIVEL")) {
            System.out.println("O objeto não está disponível para empréstimo!");
            return;
        }
        System.out.println("Insira a pessoa que irá retirar o objeto:");
        Pessoa pessoa = Consulta.consultaPessoa(conn);
        if (pessoa == null) return;
        Emprestimo empr = new Emprestimo(objeto,pessoa);
        dao.salvar(empr);
        objeto.setSITUACAO("EMPRESTADO");
        objdao.atualizarObjeto(objeto);
        System.out.println("Emprestimo realizado com sucesso!");
    }

    public static void devolverObjeto(){
        ObjetoDAO objdao = new ObjetoDAO(conn);
        EmprestimoDAO dao = new EmprestimoDAO(conn);
        System.out.println("Insira o objeto a ser devolvido:");
        sc.nextLine();
        Objeto objeto = Consulta.consultaObjeto(conn);
        if (objeto == null) return;
        if (!objeto.getSITUACAO().equals("EMPRESTADO")) {
            System.out.println("O objeto não está emprestado!");
            return;
        }
        Emprestimo emprestimo = dao.buscaObjetoAtivo(objeto);
        if (emprestimo.getOBJETO() == null) return;
        emprestimo.setESTADO("INATIVO");
        dao.atualizar(emprestimo);
        objeto.setSITUACAO("DISPONIVEL");
        objdao.atualizarObjeto(objeto);
        System.out.println("Objeto devolvido!");
    }
}
