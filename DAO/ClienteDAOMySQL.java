package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ArrayList;
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

    public ArrayList<Cliente> OrdenarPorRecaudacion() {
        ArrayList<Cliente> lista = new ArrayList<Cliente>();
        try{
            String query = "SELECT f.idCliente, c.nombre, c.email, (SUM(fp.cantidad * p.valor)) AS FACTURADO " +
                    "FROM factura_producto fp JOIN producto p ON fp.idProducto = p.idProducto " +
                    "JOIN factura f ON fp.idFactura = f.idFactura " +
                    "JOIN cliente c ON f.idCliente = c.idCliente " +
                    "GROUP BY f.idCliente " +
                    "ORDER BY FACTURADO DESC;";

            //this.connection.prepareStatement(query).execute();
            //this.connection.commit();
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String nombre = rs.getString(2);
                String email = rs.getString(3);
                lista.add(new Cliente(id, nombre, email));
            }
            ps.close();
            this.connection.commit();
        } catch (SQLException error) {
            error.printStackTrace();
        } return lista;
    }
}
