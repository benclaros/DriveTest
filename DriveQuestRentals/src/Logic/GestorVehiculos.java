package gestor;

import model.*;
import java.util.*;
import java.io.*;

public class GestorVehiculos {
    private static List<Vehiculo> vehiculos = Collections.synchronizedList(new ArrayList<>());
    private static Set<String> patentesRegistradas = new HashSet<>();
    private static final String RUTA = "vehiculos.txt";

    static {
        cargarDesdeArchivo();
    }

    public static boolean agregarVehiculo(Vehiculo v) {
        if (patentesRegistradas.contains(v.getPatente())) {
            System.out.println("ERROR: Patente duplicada.");
            return false;
        }
        vehiculos.add(v);
        patentesRegistradas.add(v.getPatente());
        guardarEnArchivo();
        return true;
    }

    public static void listarVehiculos() {
        System.out.println("\n📦 Vehículos de carga:");
        for (Vehiculo v : vehiculos) {
            if (v instanceof VehiculoCarga) {
                System.out.println(v);
            }
        }
        System.out.println("\n🚐 Vehículos de pasajeros:");
        for (Vehiculo v : vehiculos) {
            if (v instanceof VehiculosPasajeros) {
                System.out.println(v);
            }
        }
    }

    public static void mostrarBoletas() {
        for (Vehiculo v : vehiculos) {
            System.out.println("\nBoleta para patente " + v.getPatente());
            System.out.println("Días de arriendo: " + v.getDiasArriendo());
            System.out.println("Total a pagar: $" + v.calcularMonto() + " CLP");
        }
    }

    public static int contarLargosArriendos() {
        int count = 0;
        for (Vehiculo v : vehiculos) {
            if (v.getDiasArriendo() >= 7) count++;
        }
        return count;
    }

    private static void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Vehiculo v : vehiculos) {
                if (v instanceof VehiculoCarga) {
                    VehiculoCarga vc = (VehiculoCarga) v;
                    bw.write("CARGA," + vc.getPatente() + "," + vc.getCapacidadCarga() + "," + vc.getDiasArriendo());
                } else if (v instanceof VehiculosPasajeros) {
                    VehiculosPasajeros vp = (VehiculosPasajeros) v;
                    bw.write("PASAJEROS," + vp.getPatente() + "," + vp.getMaxPasajeros() + "," + vp.getDiasArriendo());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar vehículos: " + e.getMessage());
        }
    }

    private static void cargarDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals("CARGA")) {
                    agregarVehiculo(new VehiculoCarga(datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3])));
                } else if (datos[0].equals("PASAJEROS")) {
                    agregarVehiculo(new VehiculosPasajeros(datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado o vacío.");
        }
    }
}
