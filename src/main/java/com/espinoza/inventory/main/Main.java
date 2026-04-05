/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.main;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Categoria;
import com.espinoza.inventory.model.Producto;
import com.espinoza.inventory.repository.CategoriaRepositoryJDBC;
import com.espinoza.inventory.repository.ProductoRepositoryJDBC;
import com.espinoza.inventory.repository.ProductoRepositoryMemoria;
import com.espinoza.inventory.service.CategoriaService;
import com.espinoza.inventory.service.ProductoService;
import com.espinoza.inventory.util.CsvUtil;
import com.espinoza.inventory.util.Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author JEFERSON
 */
public class Main {
    
    private static final ProductoService servicio = new ProductoService(new ProductoRepositoryJDBC());
    private static final CategoriaService servicioCat = new CategoriaService(new CategoriaRepositoryJDBC());
    private static final Scanner sc = new Scanner(System.in);
    private static final Stack<String> historialOperaciones = new Stack<>();
    
    public static void main(String[] args) {
        // cargarDatosIniciales();

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
                case 5 ->
                    exportarCsv();
                case 6 ->
                    importarCsv();
                case 7 ->
                    verHistorial();
                case 8 ->
                    buscarProductoPorId();
                case 9 ->
                    listarCategorias();
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
        System.out.println("5. Exportar inventario a CSV");
        System.out.println("6. Importar productos desde CSV");
        System.out.println("7. Ver historial");
        System.out.println("8. Buscar por ID");
        System.out.println("9. Listar categorías disponibles");
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
        historialOperaciones.push("AGREGADO: " + nombre);
    }
    
    private static void eliminarProducto() {
        System.out.println("Ingrese el ID del producto a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        
        try {
            servicio.eliminarProducto(id);
            System.out.println("Producto eliminado correctamente.");
            historialOperaciones.push("ELIMINADO: producto id=" + id);
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
    
    private static void exportarCsv() {
        List<Producto> productos = servicio.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos para exportar.");
            return;
        }
        
        String ruta = "inventario_export.csv";
        CsvUtil.exportarProductos(productos, ruta);
        System.out.println("Archivo guardado en: " + ruta);
    }
    
    private static void importarCsv() {
        System.out.println("Ingresa la ruta del archivo: ");
        String ruta = sc.nextLine().trim();
        
        try {
            List<Producto> importados = CsvUtil.importarProductos(ruta);
            importados.forEach(servicio::agregarProducto);
        } catch (RuntimeException e) {
            System.out.println("Error al importar: " + e.getMessage());
            
        }
    }
    
    private static void verHistorial() {
        if (historialOperaciones.estaVacio()) {
            System.out.println("No hay operaciones registrada");
            return;
        }
        
        System.out.println("\n--- ÚLTIMAS OPERACIONES ---");
        Stack<String> temporal = new Stack<>();
        List<String> operaciones = new ArrayList<>();
        while (!historialOperaciones.estaVacio()) {
            String op = historialOperaciones.pop();
            operaciones.add(op);
            temporal.push(op);
        }
        
        while (!temporal.estaVacio()) {
            historialOperaciones.push(temporal.pop());
        }
        
        int limite = Math.min(5, operaciones.size());
        for (int i = 0; i < limite; i++) {
            System.out.println(operaciones.get(i));
        }
        
    }
    
    private static void buscarProductoPorId() {
        System.out.println("Ingresa el ID del producto: ");
        
        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            Producto producto = servicio.buscarProductoPorId(id);
            System.out.println("Producto encontrado: " + producto);
            
        } catch (ProductoNoEncontradoException e) {
            System.out.println("No se encontró ningún producto con ese ID.");
            
        } catch (NumberFormatException e) {
            System.out.println("El ID debe ser un número entero.");
            
        }
    }
    
    
    
    
    private static void listarCategorias() {
        List<Categoria> categorias = servicioCat.listarCategorias();
        
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias regitradas");
            return;
        }
        
        System.out.println("\n--- CATEGORIAS ---");
        categorias.forEach(c
                -> System.out.println(c.getIdCategoria() + ". " + c.getNombre()
                        + " — " + c.getDescripcion()));
    }
}
