package main;

import gestor.GestorVehiculos;
import model.Vehiculo;
import model.VehiculoCarga;
import model.VehiculosPasajeros;

import java.util.Scanner;

public class Main {

    private static final String ARCHIVO_DATOS = "vehiculos.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GestorVehiculos gestor = new GestorVehiculos();

        // 🔃 Cargar datos desde archivo al inicio
        gestor.cargarDesdeArchivo(ARCHIVO_DATOS);

        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Listar vehículos");
            System.out.println("3. Mostrar boletas");
            System.out.println("4. Mostrar cantidad de vehículos con arriendo >= 7 días");
            System.out.println("0. Salir y guardar");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarVehiculo(scanner, gestor);
                    break;

                case 2:
                    listarVehiculos(gestor);
                    break;

                case 3:
                    mostrarBoletas(gestor);
                    break;

                case 4:
                    long count = gestor.obtenerVehiculos().stream()
                            .filter(v -> v.getDiasArriendo() >= 7).count();
                    System.out.println("🚚 Vehículos con arriendo largo (>= 7 días): " + count);
                    break;

                case 0:
                    gestor.guardarEnArchivo(ARCHIVO_DATOS);
                    System.out.println("👋 Programa finalizado.");
                    break;

                default:
                    System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void agregarVehiculo(Scanner scanner, GestorVehiculos gestor) {
        try {
            System.out.print("Ingrese tipo de vehículo (1 = Carga, 2 = Pasajeros): ");
            int tipo = Integer.parseInt(scanner.nextLine());

            System.out.print("Ingrese patente: ");
            String patente = scanner.nextLine().toUpperCase();

            System.out.print("Ingrese cantidad de días de arriendo: ");
            int dias = Integer.parseInt(scanner.nextLine());

            if (tipo == 1) {
                System.out.print("Ingrese capacidad de carga (kg): ");
                int capacidad = Integer.parseInt(scanner.nextLine());
                VehiculoCarga carga = new VehiculoCarga(patente, capacidad, dias);
                gestor.agregarVehiculo(carga);
            } else if (tipo == 2) {
                System.out.print("Ingrese número de pasajeros: ");
                int pasajeros = Integer.parseInt(scanner.nextLine());
                VehiculosPasajeros vp = new VehiculosPasajeros(patente, pasajeros, dias);
                gestor.agregarVehiculo(vp);
            } else {
                System.out.println("❌ Tipo inválido.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Asegúrese de ingresar números correctamente.");
        }
    }

    private static void listarVehiculos(GestorVehiculos gestor) {
        System.out.println("\n Vehículos de carga:");
        for (Vehiculo v : gestor.obtenerVehiculosCarga()) {
            System.out.println(v);
        }

        System.out.println("\n Vehículos de pasajeros:");
        for (Vehiculo v : gestor.obtenerVehiculosPasajeros()) {
            System.out.println(v);
        }
    }

    private static void mostrarBoletas(GestorVehiculos gestor) {
        for (Vehiculo v : gestor.obtenerVehiculos()) {
            System.out.println("\n🧾 Boleta para vehículo: " + v.getPatente());
            System.out.println(v.calcularBoleta());
        }
    }
}
