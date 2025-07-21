package data;

import logic.GestorVehiculos;

public class TareaMostrarBoleta extends Thread {
    private GestorVehiculos gestor;

    public TareaMostrarBoleta(GestorVehiculos gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        gestor.mostrarBoletas();
    }
}