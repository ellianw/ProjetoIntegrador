import java.util.Scanner;
import java.sql.*;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static Connection conn = null;

    public static void main(String[] args) {
        conectardb();
            String primTexto = "Sistema de empréstimos de objetos\n" +
                "--------------------------------------\n" +
                "1 - Cadastrar\n" +
                "2 - Consultar\n" +
                "3 - Alterar\n" +
                "4 - Excluir\n" +
                "5 - Emprestimos\n" +
                "6 - Relatórios\n" +
                "7 - Sair\n"+
                "Escolha a sua opção:";
        System.out.println(primTexto);
        int opcao = sc.nextInt();
        while (opcao !=7){
            switch(opcao) {
                case 1:
                    Cadastro.menu(conn);
                    break;
                case 2:
                    Consulta.menu(conn);
                    break;
                case 3:
                    Alterar.menu(conn);
                    break;
                case 4:
                    Excluir.menu(conn);
                    break;
                case 5:
                    Emprestar.menu(conn);
                    break;
                case 6:
                    Relatorio.menu(conn);
                    break;
                default:
                    System.out.println("\nNúmero inválido! Tente novamente.\n");
            }
            System.out.println(primTexto);
            opcao = sc.nextInt();
        }
        sc.close();
        System.exit(0);
    }

    public static void conectardb(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS \"EMPRESTIMO\" (\"CODIGO\" INTEGER, \"COD_OBJETO\" INTEGER, \"COD_PESSOA\" INTEGER, \"DATA_EMP\" TEXT, \"ESTADO\" TEXT, FOREIGN KEY(\"COD_PESSOA\") REFERENCES \"PESSOA\"(\"CODIGO\") ON DELETE CASCADE, FOREIGN KEY(\"COD_OBJETO\") REFERENCES \"OBJETO\"(\"CODIGO\") ON DELETE CASCADE, PRIMARY KEY(\"CODIGO\" AUTOINCREMENT))");
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS \"MANUTENCAO\" (\"CODIGO\" INTEGER, \"OBJETO\" INTEGER, \"ATIVO\" TEXT, PRIMARY KEY(\"CODIGO\" AUTOINCREMENT))");
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS \"OBJETO\" (\"CODIGO\"\tINTEGER, \"NOME\"\tTEXT, \"COD_TIPO_OBJETO\"\tINTEGER, \"SITUACAO\" TEXT, FOREIGN KEY(\"COD_TIPO_OBJETO\") REFERENCES \"TIPO_OBJETO\"(\"CODIGO\") ON DELETE CASCADE, PRIMARY KEY(\"CODIGO\" AUTOINCREMENT))");
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS \"PESSOA\" (\"CODIGO\"\tINTEGER, \"NOME\"\tTEXT, PRIMARY KEY(\"CODIGO\" AUTOINCREMENT))");
            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS \"TIPO_OBJETO\" (\"CODIGO\" INTEGER, \"NOME\" INTEGER, PRIMARY KEY(\"CODIGO\" AUTOINCREMENT))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}