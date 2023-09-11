package Entidades;

public class Factura {

    private int idFactura;
    private int idCliente;

    public Factura(int idFacutura, int idCliente) {
        this.idFactura = idFacutura;
        this.idCliente = idCliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public String toString() {
        return "id de la factura: " + this.idFactura + " " + "id del Cliente: " + this.idCliente;
    }
}
