package Entidades;

public class Cliente {

    private int IdCliente;
    private String nombre;
    private String email;

    public Cliente(int idCliente, String nombre, String email) {
        this.IdCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "IdCliente=" + IdCliente +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
