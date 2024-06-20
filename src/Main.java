import entities.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Objeto> objs;
    static ArrayList<Emprestimo> emprestimos;
    static ArrayList<Manutencao> manutencoes;
    static ArrayList<Tipo_Obj> tiposObj;
    static ArrayList<Pessoa> pessoas;


    public static void main(String[] args) {
            String primTexto = "Sistema de empréstimos de objetos\n" +
                "--------------------------------------\n" +
                "1 - Cadastrar\n" +
                "2 - Consultar (Não disponível)\n" +
                "3 - Alterar (Não disponível)\n" +
                "4 - Excluir (Não disponível)\n" +
                "5 - Realizar empréstimo (Não disponível)\n" +
                "6 - Relatótios (Não disponível)\n" +
                "7 - Sair\n" +
                "Escolha a sua opção:";
        System.out.println(primTexto);
        int opcao = sc.nextInt();
        while (opcao !=7){
            switch(opcao) {
                case 1:
                    menuCadastro();
                    break;
                /*case 2: //Código comentado para próxima entrega do trabalho, no código atual poderia ser trocado por um "if"
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break; */
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
        sc.close();
        System.exit(0);
    }

    public static void menuCadastro(){
        Scanner sc = new Scanner(System.in);
        String primTexto = "\nSistema de empréstimos de objetos\n" +
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
                    cadastroPessoa();
                    break;
                case 2:
                    cadastroTipoObj();
                    break;
                case 3:
                    cadastroObjeto();
                    break;
                case 4:
                    cadastroManutencao();
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
    }

    public static void cadastroPessoa(){
        System.out.println("\nInclusão de pessoa:\nInsira o nome:");
        String nome = sc.nextLine();
        while(nome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            nome = sc.nextLine();
        }
        if (nome.equals("sair")) return;
        pessoas.add(new Pessoa(nome));
    }

    public static void cadastroTipoObj(){
        System.out.println("\nInclusão de tipo de objeto:\nInsira o tipo:");
        String tipo = sc.nextLine();
        while(tipo.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o tipo:");
            tipo = sc.nextLine();
        }
        if (tipo.equals("sair")) return;
        tiposObj.add(new Tipo_Obj(tipo));
    }

    public static void cadastroObjeto(){
        System.out.println("\nInclusão de objeto:\nInsira o objeto:");
        String objNome = sc.nextLine();
        while(objNome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o objeto:");
            objNome = sc.nextLine();
        }
        System.out.println("Insira o tipo do objeto:");
        String objTipo = sc.nextLine();
        while(objTipo.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o tipo:");
            objTipo = sc.nextLine();
        }
        if (objTipo.equals("sair")) return;
        objs.add(new Objeto(objNome,objTipo));
    }

    public static void cadastroManutencao(){
        System.out.println("\nInclusão de manutenção:\nInsira o objeto:");
        String objNome = sc.nextLine();
        while(objNome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o objeto:");
            objNome = sc.nextLine();
        }
        manutencoes.add(new Manutencao(objNome));
    }
}