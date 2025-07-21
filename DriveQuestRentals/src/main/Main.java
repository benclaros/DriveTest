package main;

import logic.GestorVehiculos;
import modelos.*;
import data.TareaMostrarBoleta;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorVehiculos gestor = new GestorVehiculos();
        Scanner sc = new Scanner(System.in);
        String ruta = "vehiculos.txt";
        int opcion;

        gestor.cargarDesdeArchivoConHilo(ruta);

        do {
            System.out.println("\n=== MENÚ DE ARRIENDO DE VEHÍCULOS ===");
            System.out.println("1. Agregar vehículo de carga");
            System.out.println("2. Agregar vehículo de pasajeros");
            System.out.println("3. Mostrar boletas");
            System.out.println("4. Guardar en archivo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Patente: ");
                    String patCarga = sc.next();
                    System.out.print("Capacidad (kg): ");
                    int cap = sc.nextInt();
                    System.out.print("Días de arriendo: ");
                    int diasC = sc.nextInt();
                    gestor.agregarVehiculo(new VehiculoCarga(patCarga, cap, diasC));
                }
                case 2 -> {
                    System.out.print("Patente: ");
                    String patPas = sc.next();
                    System.out.print("Nº de pasajeros: ");
                    int pas = sc.nextInt();
                    System.out.print("Días de arriendo: ");
                    int diasP = sc.nextInt();
                    gestor.agregarVehiculo(new VehiculosPasajeros(patPas, pas, diasP));
                }
                case 3 -> {
                    TareaMostrarBoleta tarea = new TareaMostrarBoleta(gestor);
                    tarea.start();
                }
                case 4 -> gestor.guardarEnArchivo(ruta);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
