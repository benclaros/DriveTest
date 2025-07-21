package logic;

import modelos.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GestorVehiculos {
    private Map<String, Vehiculo> mapaVehiculos;

    public GestorVehiculos() {
        this.mapaVehiculos = new ConcurrentHashMap<>();
    }

    public synchronized boolean agregarVehiculo(Vehiculo v) {
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

    public void guardarEnArchivo(String ruta) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (Vehiculo v : mapaVehiculos.values()) {
                if (v instanceof VehiculoCarga vc) {
                    writer.write("CARGA," + vc.getPatente() + "," + vc.getCapacidadCarga() + "," + vc.getDiasArriendo());
                } else if (v instanceof VehiculosPasajeros vp) {
                    writer.write("PASAJEROS," + vp.getPatente() + "," + vp.getNumPasajeros() + "," + vp.getDiasArriendo());
                }
                writer.newLine();
            }
            System.out.println("✅ Vehículos guardados exitosamente en archivo.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivoConHilo(String ruta) {
        Thread hilo = new Thread(() -> {
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
                    int valor1 = Integer.parseInt(partes[2]);
                    int dias = Integer.parseInt(partes[3]);

                    Vehiculo v = switch (tipo.toUpperCase()) {
                        case "CARGA" -> new VehiculoCarga(patente, valor1, dias);
                        case "PASAJEROS" -> new VehiculosPasajeros(patente, valor1, dias);
                        default -> null;
                    };

                    if (v != null) {
                        this.agregarVehiculo(v);
                    }
                }
                System.out.println("✅ Vehículos cargados (hilo concurrente).");
            } catch (IOException | NumberFormatException e) {
                System.out.println("❌ Error al leer archivo: " + e.getMessage());
            }
        });
        hilo.start();
    }

    public void mostrarBoletas() {
        if (mapaVehiculos.isEmpty()) {
            System.out.println("⚠️ No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : mapaVehiculos.values()) {
            System.out.println("=== BOLETA DE ARRIENDO ===");
            System.out.println(v);
            System.out.println("==========================\n");
        }
    }
}
