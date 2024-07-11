import dao.*;
import entities.*;

import java.sql.Connection;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Relatorio {
    static Scanner sc = new Scanner(System.in);
    static Connection conn;
    public static void menu(Connection conexao){
        conn = conexao;
        String primTexto = "\nSistema de empréstimos de objetos\n" +
                "       Módulo de relatórios\n"+
                "--------------------------------------\n" +
                "1 - Pessoas\n" +
                "2 - Tipos de objetos\n" +
                "3 - Objetos\n" +
                "4 - Manutenção\n" +
                "5 - Emprestimos\n" +
                "6 - Sair\n" +
                "Escolha a sua opção:";
        System.out.println(primTexto);
        int opcao = sc.nextInt();
        while (opcao !=6){
            switch (opcao){
                case 1:
                    relatorioPessoas();
                    break;
                case 2:
                    relatorioTipos();
                    break;
                case 3:
                    relatorioObjetos();
                    break;
                case 4:
                    relatorioManutencao();
                    break;
                case 5:
                    relatorioEmprestimo();
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }

    public static void relatorioPessoas(){
        PessoaDAO dao = new PessoaDAO(conn);
        System.out.println("Listagem de pessoas cadastrados:");
        ArrayList<Pessoa> arr = dao.buscaTodos();
        arr.forEach(pessoa -> System.out.println(pessoa.toString()));
    }
    public static void relatorioTipos(){
        Tipo_ObjDAO dao = new Tipo_ObjDAO(conn);
        System.out.println("Listagem de tipo de objeto cadastrados:");
        ArrayList<Tipo_Obj> arr = dao.buscaTodos();
        arr.forEach(tipo -> System.out.println(tipo.toString()));
    }
    public static void relatorioObjetos(){
        ObjetoDAO dao = new ObjetoDAO(conn);
        System.out.println("Relatório por:\n1 - Tipo\n2 - Situação");
        sc.nextLine();
        String opcao = sc.nextLine();
        ArrayList<Objeto> arr;
        switch (opcao){
            case "1":
                System.out.println("Insira o tipo de objeto:");
                Tipo_Obj tipo = Consulta.consultaTipoObj(conn);
                if (tipo==null) return;
                arr = dao.buscaPorTipo(tipo.getCODIGO());
                imprimirObjetos(arr);
                break;
            case "2":
                arr = dao.buscaPorSituacao();
                imprimirObjetos(arr);
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    public static void relatorioManutencao(){
        ManutencaoDAO dao = new ManutencaoDAO(conn);
        System.out.println("Relatório ordenado por:\n1 - Objeto\n2 - Situação");
        sc.nextLine();
        String opcao = sc.nextLine();
        ArrayList<Manutencao> arr;
        switch (opcao){
            case "1":
                arr = dao.buscaOrdenadaObjeto();
                break;
            case "2":
                arr = dao.buscaOrdenadaSituacao();
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        arr.forEach(manutencao -> System.out.println(manutencao.toString()));
    }
    public static void relatorioEmprestimo(){
        EmprestimoDAO dao = new EmprestimoDAO(conn);
        System.out.println("Relatório ordenado por:\n1 - Objeto\n2 - Pessoa\n3 - Situação\n4 - Data");
        sc.nextLine();
        String opcao = sc.nextLine();
        ArrayList<Emprestimo> arr;
        switch (opcao){
            case "1":
                arr = dao.buscaOrdenadaObjeto();
                break;
            case "2":
                arr = dao.buscaOrdenadaPessoa();
                break;
            case "3":
                arr = dao.buscaOrdenadaSituacao();
                break;
            case "4":
                System.out.println("Insira a data:");
                String data = sc.nextLine();
                if (dataInvalida(data)) {
                    System.out.println("Data inválida!");
                    return;
                }
                arr = dao.buscaData(data);
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }
        arr.forEach(emprestimo -> System.out.println(emprestimo.toString()));
    }

    public static void imprimirObjetos(ArrayList<Objeto> arr){
        EmprestimoDAO empdao = new EmprestimoDAO(conn);
        System.out.println("Objetos disponíveis para empréstimo:");
        arr.forEach(obj -> {
            if (obj.getSITUACAO().equals("DISPONIVEL")) {
                System.out.println("\t"+obj);
            }});
        System.out.println("Objetos indisponíveis para empréstimo:");
        arr.forEach(obj -> {
            if (obj.getSITUACAO().equals("EMPRESTADO")) {
                Emprestimo emp = empdao.buscaObjetoAtivo(obj);
                System.out.println("\t"+obj+" para: "+emp.getPESSOA().getNOME());
            }
            if (obj.getSITUACAO().equals("MANUTENCAO")) {
                System.out.println("\t"+obj);
            }
        });
    }

    public static boolean dataInvalida(String data) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(data, dateFormatter);
        } catch (DateTimeParseException e) {
            return true;
        }
        return false;
    }
}
