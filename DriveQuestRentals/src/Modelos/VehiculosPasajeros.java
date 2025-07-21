package modelos;

public class VehiculosPasajeros extends Vehiculo implements Calculable {
    private int numPasajeros;

    public VehiculosPasajeros(String patente, int numPasajeros, int diasArriendo) {
        super(patente, diasArriendo);
        this.numPasajeros = numPasajeros;
    }

    public int getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(int numPasajeros) {
        this.numPasajeros = numPasajeros;
    }

    @Override
    public double calcularCostoArriendo() {
        return getDiasArriendo() * (numPasajeros * 1000);
    }

    @Override
    public String toString() {
        return "[PASAJEROS] " + super.toString() + ", Pasajeros: " + numPasajeros + ", Costo: $" + calcularCostoArriendo();
    }
}