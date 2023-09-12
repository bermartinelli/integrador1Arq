package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Entidades.Producto;
import Interfaces.DAO;

public class ProductoDAOMySQL implements DAO<Producto>{
    private Connection connection;

    public ProductoDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Producto producto) throws SQLException {
        try {
            String query = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());

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
            String query = "CREATE TABLE IF NOT EXISTS producto (idProducto INT, nombre VARCHAR(45), valor FLOAT, PRIMARY KEY (idProducto))";

            this.connection.prepareStatement(query).execute();
            this.connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public Producto elMasRecaudador() {
        Producto producto = null;
        try{
            String query = "SELECT producto.idProducto, producto.nombre, producto.valor, (SUM(factura_producto.cantidad) * producto.valor) AS FACTURADO " +
                    "FROM producto JOIN factura_producto ON producto.idProducto = factura_producto.idProducto " +
                    "GROUP BY producto.idProducto " +
                    "ORDER BY FACTURADO DESC " +
                    "LIMIT 1;";

            //this.connection.prepareStatement(query).execute();
            //this.connection.commit();
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nombre = rs.getString(2);
                Integer valor = rs.getInt(4);
               producto = new Producto(id, nombre, valor);
            }
            ps.close();

            this.connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        } return producto;
    }
}
