package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Entidades.Factura_Producto;
import Interfaces.DAO;

public class FacturaProductoDAOMySQL implements DAO<Factura_Producto>{
    private Connection connection;

    public FacturaProductoDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Factura_Producto fp) throws SQLException {
        try {
            String query = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());

            ps.executeUpdate();
            ps.close();

            this.connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTable(){
        try{
            String query = "CREATE TABLE IF NOT EXISTS factura_producto (idFactura INT, idProducto INT, cantidad INT)";

            this.connection.prepareStatement(query).execute();
            this.connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
