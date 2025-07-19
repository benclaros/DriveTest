package model;

public class VehiculosPasajeros extends Vehiculo implements Calculable {
    private int maxPasajeros;

    public VehiculosPasajeros() {
        super();
    }

    public VehiculosPasajeros(String patente, int diasArriendo, int maxPasajeros) {
        super(patente, diasArriendo);
        this.maxPasajeros = maxPasajeros;
    }
    @Override
    public String toString() {
    return "Vehículo de Pasajeros - Patente: " + patente +
           ", Máx. pasajeros: " + maxPasajeros +
           ", Días de arriendo: " + diasArriendo;
    }


    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== VEHÍCULO DE PASAJEROS ===");
        System.out.println("Patente: " + getPatente());
        System.out.println("Días arriendo: " + getDiasArriendo());
        System.out.println("Máximo de pasajeros: " + maxPasajeros);
    }

    @Override
    public void mostrarBoleta() {
        double bruto = getDiasArriendo() * 15000;
        double descuento = bruto * DESCUENTO_PASAJEROS;
        double neto = bruto - descuento;
        double iva = neto * IVA;
        double total = neto + iva;

        System.out.println("=== BOLETA VEHÍCULO DE PASAJEROS ===");
        System.out.println("Patente: " + getPatente());
        System.out.printf("Valor bruto: $%,.0f CLP\n", bruto);
        System.out.printf("Descuento (%.0f%%): $%,.0f\n", DESCUENTO_PASAJEROS * 100, descuento);
        System.out.printf("IVA (19%%): $%,.0f\n", iva);
        System.out.printf("Total a pagar: $%,.0f CLP\n", total);
    }

    public String getNumPasajeros() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
