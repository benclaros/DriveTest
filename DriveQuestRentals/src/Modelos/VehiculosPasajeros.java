package model;

public class VehiculosPasajeros extends Vehiculo {
    private int maxPasajeros;
    private static final double VALOR_DIA = 30000;
    private static final double DESCUENTO = 0.12;

    public VehiculosPasajeros() {}

    public VehiculosPasajeros(String patente, int maxPasajeros, int diasArriendo) {
        super(patente, diasArriendo);
        this.maxPasajeros = maxPasajeros;
    }

    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Override
    public int calcularMonto() {
        double bruto = VALOR_DIA * diasArriendo;
        double descuento = bruto * DESCUENTO;
        double neto = bruto - descuento;
        double iva = neto * 0.19;
        return (int)(neto + iva);
    }

    @Override
    public String toString() {
        return "Vehículo de Pasajeros - Patente: " + patente +
               ", Máx. Pasajeros: " + maxPasajeros +
               ", Días: " + diasArriendo;
    }
}
