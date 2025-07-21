package modelos;

public class VehiculoCarga extends Vehiculo implements Calculable {
    private int capacidadCarga;

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
    public double calcularCostoArriendo() {
        return getDiasArriendo() * (capacidadCarga * 0.5);
    }

    @Override
    public String toString() {
        return "[CARGA] " + super.toString() + ", Capacidad: " + capacidadCarga + "kg, Costo: $" + calcularCostoArriendo();
    }
}
