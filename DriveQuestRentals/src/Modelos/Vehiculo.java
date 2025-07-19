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

    public abstract void mostrarDatos();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehiculo) {
            Vehiculo otro = (Vehiculo) obj;
            return this.patente.equalsIgnoreCase(otro.patente);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return patente.hashCode();
    }
}
