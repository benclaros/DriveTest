package model;

public class VehiculoCarga extends Vehiculo {
    private int capacidadCarga; // en kilos
    private static final double VALOR_DIA = 35000;
    private static final double DESCUENTO = 0.07;

    public VehiculoCarga() {}

    public VehiculoCarga(String patente, int capacidadCarga, int diasArriendo) {
        super(patente, diasArriendo);
        this.capacidadCarga = capacidadCarga;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
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
        return "Vehículo de Carga - Patente: " + patente +
               ", Capacidad: " + capacidadCarga + " kg" +
               ", Días: " + diasArriendo;
    }
}
