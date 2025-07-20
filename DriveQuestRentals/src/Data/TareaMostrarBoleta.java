package threads;

import gestor.GestorVehiculos;

public class TareaMostrarBoleta extends Thread {
    @Override
    public void run() {
        GestorVehiculos.mostrarBoletas();
    }
}
