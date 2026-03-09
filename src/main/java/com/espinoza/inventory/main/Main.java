/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.main;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Categoria;
import com.espinoza.inventory.model.Producto;
import com.espinoza.inventory.repository.ProductoRepositoryMemoria;
import com.espinoza.inventory.service.ProductoService;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author JEFERSON
 */
public class Main {

    private static final ProductoService servicio = new ProductoService(new ProductoRepositoryMemoria());
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosIniciales();

        boolean ejecutando = true;
        while (ejecutando) {
            mostrarMenu();

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 ->
                    listarProductos();
                case 2 ->
                    buscarProducto();
                case 3 ->
                    agregarProducto();
                case 4 ->
                    eliminarProducto();
                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    ejecutando = false;
                }
                default ->
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE INVENTARIO =====");
        System.out.println("1. Listar todos los productos");
        System.out.println("2. Buscar producto por nombre");
        System.out.println("3. Agregar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void listarProductos() {
        List<Producto> productos = servicio.listarProductos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos regitrados");
            return;
        }
        System.out.println("\n--- PRODUCTOS ---");
        productos.forEach(System.out::println);
    }

    public static void buscarProducto() {
        System.out.println("Ingresa el nombre a buscar: ");
        String nombre = sc.nextLine();

        List<Producto> resultados = servicio.buscarProductoPorNombre(nombre);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre.");

        } else {
            resultados.forEach(System.out::println);
        }
    }

    private static void agregarProducto() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine().trim());

        Categoria categoria = new Categoria(1, "General", "Categoría por defecto");
        Producto producto = new Producto(id, nombre, precio, cantidad, categoria);
        servicio.agregarProducto(producto);
        System.out.println("Producto agregado correctamente.");
    }

    private static void eliminarProducto() {
        System.out.println("Ingrese el ID del producto a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        try {
            servicio.eliminarProducto(id);
            System.out.println("Producto eliminado correctamente.");
        } catch (ProductoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
        private static void cargarDatosIniciales() {
        Categoria electronica = new Categoria(1, "Electrónica", "Dispositivos electrónicos");
        Categoria herramientas = new Categoria(2, "Herramientas", "Herramientas manuales");

        servicio.agregarProducto(new Producto(1, "Laptop Dell", 2599.99, 10, electronica));
        servicio.agregarProducto(new Producto(2, "Mouse Logitech", 89.90, 45, electronica));
        servicio.agregarProducto(new Producto(3, "Taladro Bosch", 349.00, 15, herramientas));
    }
}
