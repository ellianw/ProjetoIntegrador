import dao.*;
import entities.*;

import java.sql.Connection;
import java.util.Scanner;

public class Cadastro {
    static Scanner sc = new Scanner(System.in);
    static Connection conn = null;
    public static void menu(Connection conexao){
        conn = conexao;
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
        PessoaDAO dao = new PessoaDAO(conn);
        System.out.println("\nInclusão de pessoa:\nInsira o nome:");
        sc.nextLine();
        String nome = sc.nextLine();
        while(nome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o nome:");
            nome = sc.nextLine();
        }
        if (nome.equals("sair")) return;
        Pessoa pessoa = dao.buscaNome(nome);
        if (pessoa.getCODIGO() != null){
            System.out.println("Pessoa já cadastrada: "+pessoa.getNOME());
            return;
        }
        dao.salvar(pessoa);
        System.out.println("Cadastro concluído: "+pessoa.getNOME());
    }

    public static void cadastroTipoObj(){
        Tipo_ObjDAO dao = new Tipo_ObjDAO(conn);
        System.out.println("\nInclusão de tipo de objeto:\nInsira o tipo:");
        sc.nextLine();
        String tipo = sc.nextLine();
        while(tipo.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o tipo:");
            tipo = sc.nextLine();
        }
        if (tipo.equals("sair")) return;
        Tipo_Obj tipoobj = dao.buscaNome(tipo);
        if (tipoobj.getCODIGO() != null){
            System.out.println("Tipo já cadastrado: "+tipoobj.getTIPO());
            return;
        }
        dao.salvar(tipoobj);
        System.out.println("Cadastro concluído: "+tipoobj.getTIPO());
    }

    public static void cadastroObjeto(){
        Tipo_ObjDAO tipodao = new Tipo_ObjDAO(conn);
        ObjetoDAO dao = new ObjetoDAO(conn);
        System.out.println("\nInclusão de objeto:\nInsira o objeto:");
        sc.nextLine();
        String objNome = sc.nextLine();
        while(objNome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o objeto:");
            objNome = sc.nextLine();
        }
        if (objNome.equals("sair")) return;
        if (dao.objetoExiste(objNome)){
            System.out.println("Objeto já existe!");
            return;
        }
        System.out.println("Insira o tipo do objeto:");
        String objTipo = sc.nextLine();
        while(objTipo.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o tipo:");
            objTipo = sc.nextLine();
        }
        if (objTipo.equals("sair")) return;
        if (tipodao.tipoInvalido(objTipo)){
            System.out.println("Tipo não cadastrado!");
            return;
        }
        Objeto obj = new Objeto(objNome,tipodao.buscaNome(objTipo));
        dao.salvar(obj);
        System.out.println("Cadastro concluído: "+obj.getNOME());
    }

    public static void cadastroManutencao(){
        ObjetoDAO objdao = new ObjetoDAO(conn);
        ManutencaoDAO dao = new ManutencaoDAO(conn);
        System.out.println("\nInclusão de manutenção:\nInsira o objeto:");
        sc.nextLine();
        String objNome = sc.nextLine();
        while(objNome.isEmpty()){
            System.out.println("Campo vazio! Use 'sair' ou insira o objeto:");
            objNome = sc.nextLine();
        }
        if (!objdao.objetoExiste(objNome)){
            System.out.println("Objeto não existe!");
            return;
        }
        Objeto obj = objdao.buscaNome(objNome);
        if (obj.getSITUACAO().equals("MANUTENCAO")){
            System.out.println("Objeto já se encontra em manutenção");
            return;
        }
        obj.setSITUACAO("MANUTENCAO");
        objdao.atualizarObjeto(obj);
        Manutencao manutencao = new Manutencao(obj);
        dao.salvar(manutencao);
        System.out.println("Situação do objeto "+obj.getNOME()+" definida como MANUTENCAO");
    }
}
