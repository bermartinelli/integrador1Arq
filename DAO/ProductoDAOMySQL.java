package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
