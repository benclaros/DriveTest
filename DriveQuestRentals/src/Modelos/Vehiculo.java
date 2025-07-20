package model;

public abstract class Vehiculo {
    protected String patente;
    protected int diasArriendo;

    public Vehiculo() {}

    public Vehiculo(String patente, int diasArriendo) {
        this.patente = patente;
        this.diasArriendo = diasArriendo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    public abstract int calcularMonto();

    public abstract String toString();
}
