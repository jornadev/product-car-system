package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/product_cart";
    private static final String user = "root";
    private static final String password = "1203";

    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, user, password);
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
