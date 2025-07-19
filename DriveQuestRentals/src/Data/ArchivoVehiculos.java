package data;

import model.*;
import java.io.*;
import java.util.*;

public class ArchivoVehiculos {
    private static final String RUTA = "archivos/vehiculos.txt";

    public void guardarVehiculos(List<Vehiculo> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Vehiculo v : lista) {
                if (v instanceof VehiculoCarga) {
                    VehiculoCarga vc = (VehiculoCarga) v;
                    bw.write("CARGA;" + vc.getPatente() + ";" + vc.getDiasArriendo() + ";" + vc.getCapacidadCarga());
                } else if (v instanceof VehiculosPasajeros) {
                    VehiculosPasajeros vp = (VehiculosPasajeros) v;
                    bw.write("PASAJERO;" + vp.getPatente() + ";" + vp.getDiasArriendo() + ";" + vp.getMaxPasajeros());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar vehículos: " + e.getMessage());
        }
    }

    public List<Vehiculo> cargarVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        File archivo = new File(RUTA);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String tipo = partes[0];
                String patente = partes[1];
                int dias = Integer.parseInt(partes[2]);

                if (tipo.equalsIgnoreCase("CARGA")) {
                    double capacidad = Double.parseDouble(partes[3]);
                    lista.add(new VehiculoCarga(patente, dias, capacidad));
                } else if (tipo.equalsIgnoreCase("PASAJERO")) {
                    int maxPasajeros = Integer.parseInt(partes[3]);
                    lista.add(new VehiculosPasajeros(patente, dias, maxPasajeros));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer vehículos: " + e.getMessage());
        }

        return lista;
    }
}
