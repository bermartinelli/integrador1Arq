
import DAO.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Entidades.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import Factory.DAOFactory;


import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

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

       /*//CARGA DE CLIENTES
        CSVParser parserCliente = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("csv/clientes.csv"));
        for(CSVRecord row: parserCliente) {
            Cliente cliente = new Cliente(parseInt(row.get("idCliente")),row.get("nombre"),row.get("email"));
            daoCliente.insert(cliente);
        }

        //CARGA DE FACTURAS
        CSVParser parserFactura = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("csv/facturas.csv"));
        for(CSVRecord row: parserFactura) {
            Factura factura = new Factura(parseInt(row.get("idFactura")), parseInt(row.get("idCliente")));
            daoFactura.insert(factura);
        }

        //CARGA DE PRODUCTOS
        CSVParser parserProducto = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("csv/productos.csv"));
        for(CSVRecord row: parserProducto) {
            Producto producto = new Producto(parseInt(row.get("idProducto")), row.get("nombre"), parseFloat(row.get("valor")));
            daoProducto.insert(producto);
        }

        //CARGA DE FACTURA PRODUCTOS
        CSVParser parserFP = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("csv/facturas-productos.csv"));
        for(CSVRecord row: parserFP) {
            Factura_Producto FP = new Factura_Producto(parseInt(row.get("idFactura")), parseInt(row.get("idProducto")), parseInt(row.get("cantidad")));
            daoFacturaProducto.insert(FP);
        }
*/

        System.out.println(daoProducto.elMasRecaudador());
    }
}
