package Factory;

import java.sql.SQLException;

import DAO.*;

public abstract class DAOFactory {
    public static int MYSQL_JDBC = 1;

    public abstract ClienteDAOMySQL getDAOCliente();
    public abstract FacturaDAOMySQL getDAOFactura();
    public abstract FacturaProductoDAOMySQL getDAOFacturaProducto();
    public abstract ProductoDAOMySQL getDAOProducto();


    public static DAOFactory getDAOFactory(int BBDD) throws SQLException {
        switch (BBDD) {
            case 1 : {
                return MySQLDAOFactory.getInstance();
            }
            //case DERBY_JDBC: return null;
            //case JPA_HIBERNATE: return null;
            default: return null;
        }
    }

}
