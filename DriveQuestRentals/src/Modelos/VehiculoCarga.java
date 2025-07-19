package model;

public class VehiculoCarga extends Vehiculo implements Calculable {
    private double capacidadCarga;

    public VehiculoCarga() {
        super();
    }
    
    @Override
    public String toString() {
    return "Vehículo de Carga - Patente: " + patente +
           ", Capacidad: " + capacidadCarga + " kg" +
           ", Días de arriendo: " + diasArriendo;
}


    public VehiculoCarga(String patente, int diasArriendo, double capacidadCarga) {
        super(patente, diasArriendo);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("=== VEHÍCULO DE CARGA ===");
        System.out.println("Patente: " + getPatente());
        System.out.println("Días arriendo: " + getDiasArriendo());
        System.out.println("Capacidad de carga: " + capacidadCarga + " kg");
    }

    @Override
    public void mostrarBoleta() {
        double bruto = getDiasArriendo() * 20000;
        double descuento = bruto * DESCUENTO_CARGA;
        double neto = bruto - descuento;
        double iva = neto * IVA;
        double total = neto + iva;

        System.out.println("=== BOLETA VEHÍCULO DE CARGA ===");
        System.out.println("Patente: " + getPatente());
        System.out.printf("Valor bruto: $%,.0f \n", bruto);
        System.out.printf("Descuento (%.0f%%): $%,.0f\n", DESCUENTO_CARGA * 100, descuento);
        System.out.printf("IVA (19%%): $%,.0f\n", iva);
        System.out.printf("Total a pagar: $%,.0f \n", total);
    }
}
