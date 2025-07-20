package main;

import gestor.GestorVehiculos;
import model.*;
import threads.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== DriveQuest Rentals ===");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Mostrar boletas");
            System.out.println("4. Ver cantidad de arriendos largos (>= 7 días)");
            System.out.println("0. Salir");
            System.out.print("Ingrese opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Tipo (CARGA/PASAJEROS): ");
                    String tipo = sc.nextLine().toUpperCase();
                    System.out.print("Patente: ");
                    String patente = sc.nextLine();
                    System.out.print("Días de arriendo: ");
                    int dias = sc.nextInt();

                    if (tipo.equals("CARGA")) {
                        System.out.print("Capacidad de carga (kg): ");
                        int capacidad = sc.nextInt();
                        GestorVehiculos.agregarVehiculo(new VehiculoCarga(patente, capacidad, dias));
                    } else if (tipo.equals("PASAJEROS")) {
                        System.out.print("Máximo de pasajeros: ");
                        int pasajeros = sc.nextInt();
                        GestorVehiculos.agregarVehiculo(new VehiculosPasajeros(patente, pasajeros, dias));
                    } else {
                        System.out.println("Tipo no válido.");
                    }
                    break;

                case 2:
                    new TareaListarVehiculos().start();
                    break;

                case 3:
                    new TareaMostrarBoletas().start();
                    break;

                case 4:
                    System.out.println("Vehículos con arriendos largos: " +
                            GestorVehiculos.contarLargosArriendos());
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
