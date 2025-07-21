package modelos;

public abstract class Vehiculo {
    private String patente;
    private int diasArriendo;

    public Vehiculo(String patente, int diasArriendo) {
        if (patente == null || patente.isEmpty()) {
            throw new IllegalArgumentException("La patente no puede estar vacía");
        }
        this.patente = patente;
        this.diasArriendo = diasArriendo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente == null || patente.isEmpty()) {
            throw new IllegalArgumentException("La patente no puede estar vacía");
        }
        this.patente = patente;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        if (diasArriendo <= 0) {
            throw new IllegalArgumentException("Los días de arriendo deben ser mayores a 0");
        }
        this.diasArriendo = diasArriendo;
    }

    @Override
    public String toString() {
        return "Patente: " + patente + ", Días de arriendo: " + diasArriendo;
    }
}
