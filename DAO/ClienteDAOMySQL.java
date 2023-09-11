package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Entidades.Cliente;
import Interfaces.DAO;

public class ClienteDAOMySQL implements DAO<Cliente> {

    private Connection connection;

    public ClienteDAOMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Cliente cliente) throws SQLException {
        try {
            String query = "INSERT into cliente (idCliente, nombre, email) VALUES(?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(query);

            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
            ps.close();
            this.connection.commit();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    //@Override
    public boolean delete(int id) {
        try {
            String query = "DELETE FROM cliente WHERE idCliente = ?";
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }


    @Override
    public void createTable() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS cliente (idCliente INT, nombre VARCHAR(255), email VARCHAR(255), PRIMARY KEY (idCliente))";

            this.connection.prepareStatement(query).execute();
            this.connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
