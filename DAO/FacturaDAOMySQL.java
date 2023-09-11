package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Entidades.Factura;
import Interfaces.DAO;

public class FacturaDAOMySQL implements DAO<Factura>{
    private Connection connection;

    public FacturaDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Factura factura) throws SQLException {
        try {
            String query = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());

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
            String query= "CREATE TABLE IF NOT EXISTS factura(idFactura INT, idCliente INT, PRIMARY KEY (idFactura), FOREIGN KEY(idCliente) REFERENCES cliente(idCliente))";
            this.connection.prepareStatement(query).execute();
            this.connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
