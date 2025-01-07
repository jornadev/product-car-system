package bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/carrinho_virtual";
    private static final String usuario = "root";
    private static final String senha = "1203";

    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, usuario, senha);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        try (Connection con = createConnectionToMySQL()){
            if(con!= null){
                System.out.println("conexao bem sucedidade!");
                con.close();
            }
        }
    }

}
