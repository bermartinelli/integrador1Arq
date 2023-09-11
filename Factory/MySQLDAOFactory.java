package Factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DAO.*;
import Entidades.*;

public class MySQLDAOFactory extends DAOFactory{

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURI = "jdbc:mysql://localhost:3306/integrador1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static MySQLDAOFactory INSTANCE;

    private Connection con;

    private MySQLDAOFactory() throws SQLException {
        try {
            // sintaxis para hacer el import de acuerdo al DRIVER
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            // salir de la execución con un "1" = estado error
            System.exit(1);
        }
        openConnection();
    }

    public void openConnection() throws SQLException {
        con = DriverManager.getConnection(DBURI, USERNAME, PASSWORD);
        con.setAutoCommit(false);
    }

    public void closeConnection() throws SQLException {
        con.close();
    }

    public static MySQLDAOFactory getInstance() throws SQLException {
        if (INSTANCE == null) {
            return new MySQLDAOFactory();
        }
        return INSTANCE;
    }

    public  ClienteDAOMySQL getDAOCliente(){
        return new ClienteDAOMySQL(con);
    }
    public FacturaDAOMySQL getDAOFactura(){
        return new FacturaDAOMySQL(con);
    }
    public FacturaProductoDAOMySQL getDAOFacturaProducto(){
        return new FacturaProductoDAOMySQL(con);
    }
    public ProductoDAOMySQL getDAOProducto(){
        return new ProductoDAOMySQL(con);
    }

}
