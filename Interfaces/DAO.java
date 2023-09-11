package Interfaces;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    public void insert(T objeto) throws SQLException;
   // public boolean delete(int id);
   // public boolean update(int id);
    //public T get(int id);
    //public List<T> getAll();
    public void createTable();

}
