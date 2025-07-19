package gestor;

import model.Vehiculo;
import model.VehiculoCarga;
import model.VehiculosPasajeros;

import java.io.*;
import java.util.*;

public class GestorVehiculos {

    private Map<String, Vehiculo> mapaVehiculos;

    public GestorVehiculos() {
        this.mapaVehiculos = new HashMap<>();
    }

    public boolean agregarVehiculo(Vehiculo v) {
        if (mapaVehiculos.containsKey(v.getPatente())) {
            System.out.println("❌ Error: La patente ya está registrada.");
            return false;
        }
        mapaVehiculos.put(v.getPatente(), v);
        return true;
    }

    public Collection<Vehiculo> obtenerVehiculos() {
        return mapaVehiculos.values();
    }

    public List<Vehiculo> obtenerVehiculosCarga() {
        List<Vehiculo> carga = new ArrayList<>();
        for (Vehiculo v : mapaVehiculos.values()) {
            if (v instanceof VehiculoCarga) {
                carga.add(v);
            }
        }
        return carga;
    }

    public List<Vehiculo> obtenerVehiculosPasajeros() {
        List<Vehiculo> pasajeros = new ArrayList<>();
        for (Vehiculo v : mapaVehiculos.values()) {
            if (v instanceof VehiculosPasajeros) {
                pasajeros.add(v);
            }
        }
        return pasajeros;
    }

    public void guardarEnArchivo(String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Vehiculo v : mapaVehiculos.values()) {
                if (v instanceof VehiculoCarga) {
                    VehiculoCarga vc = (VehiculoCarga) v;
                    writer.write("CARGA," + vc.getPatente() + "," + vc.getCapacidadCarga() + "," + vc.getDiasArriendo());
                } else if (v instanceof VehiculosPasajeros) {
                    VehiculosPasajeros vp = (VehiculosPasajeros) v;
                    writer.write("PASAJEROS," + vp.getPatente() + "," + vp.getNumPasajeros() + "," + vp.getDiasArriendo());
                }
                writer.newLine();
            }
            System.out.println("✅ Vehículos guardados exitosamente en archivo.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("⚠️ Archivo no encontrado: " + ruta);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 4) continue;

                String tipo = partes[0];
                String patente = partes[1];
                int valor1 = Integer.parseInt(partes[2]); // capacidad o numPasajeros
                int dias = Integer.parseInt(partes[3]);

                Vehiculo v = null;

                if (tipo.equalsIgnoreCase("CARGA")) {
                    v = new VehiculoCarga(patente, valor1, dias);
                } else if (tipo.equalsIgnoreCase("PASAJEROS")) {
                    v = new VehiculosPasajeros(patente, valor1, dias);
                }

                if (v != null) {
                    this.agregarVehiculo(v); // ya valida duplicados
                }
            }
            System.out.println("✅ Vehículos cargados exitosamente desde archivo.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Error al leer archivo: " + e.getMessage());
        }
    }
}
