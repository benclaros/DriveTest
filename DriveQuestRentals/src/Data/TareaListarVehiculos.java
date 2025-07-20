package threads;

import gestor.GestorVehiculos;

public class TareaListarVehiculos extends Thread {
    @Override
    public void run() {
        GestorVehiculos.listarVehiculos();
    }
}
