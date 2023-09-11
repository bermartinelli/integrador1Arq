
import DAO.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import Factory.DAOFactory;



public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        // obtengo el DAO de MySQL con BBDD = 1, creo una instancia unica del MYSQLDAOFactory.
        DAOFactory DAOMySQL = DAOFactory.getDAOFactory(1);

        // obtengo los DAOs llamando a los metodos del DAOMySQL delcarado arriba
        ClienteDAOMySQL daoCliente = DAOMySQL.getDAOCliente();
        FacturaDAOMySQL daoFactura = DAOMySQL.getDAOFactura();
        FacturaProductoDAOMySQL daoFacturaProducto = DAOMySQL.getDAOFacturaProducto();
        ProductoDAOMySQL daoProducto = DAOMySQL.getDAOProducto();

        // Creo las tablas con las intancias creadas arriba
       daoCliente.createTable();
       daoFactura.createTable();
       daoProducto.createTable();
       daoFacturaProducto.createTable();

       //CONTINUAR DESDE ACA CON LA CARGA DE LOS CSV!!!!

    }
}
