package com.espinoza.inventory.service;

import com.espinoza.inventory.exception.DatoInvalidoException;
import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Categoria;
import com.espinoza.inventory.model.Producto;
import com.espinoza.inventory.repository.ProductoRepositoryMemoria;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author JEFERSON
 */
public class ProductoServiceTest {

    // El servicio que vamos a testear
    private ProductoService servicio;
    // Categorías reutilizables en los tests
    private Categoria electronica;
    private Categoria herramientas;

    @BeforeEach
    void setUp() {
        // Este método se ejecuta ANTES de cada test
        // Garantiza que cada test empieza con estado limpio
        servicio = new ProductoService(new ProductoRepositoryMemoria());
        electronica = new Categoria(1, "Electrónica", "Dispositivos electrónicos");
        herramientas = new Categoria(2, "Herramientas", "Herramientas manuales");
    }

    // ==================== TESTS DE AGREGAR ====================
    @Test
    @DisplayName("Debe agregar un producto correctamente y poder recuperarlo")
    void debeAgregarProductoYRecuperarlo() {
        //ARRANGE
        Producto producto = new Producto(1, "Laptop Dell", 2599.99, 10, electronica);

        //ACT 
        servicio.agregarProducto(producto);

        //ASSERT
        Producto recuperado = servicio.buscarProductoPorId(1);
        assertEquals("Laptop Dell", recuperado.getNombre());
        assertEquals(2599.99, recuperado.getPrecio());
        assertEquals(10, recuperado.getCantidad());
    }

    @Test
    @DisplayName("la lista debe estar vacia cuando no hay productos")
    void debeRetornarListaVaciaSinProductos() {

        //ACT
        List<Producto> productos = servicio.listarProductos();
        //ASSERT
        assertTrue(productos.isEmpty());

    }

    @Test
    @DisplayName("Debe listar todos los productos agregados")
    void debeListarTodosLosProductos() {
        // Arrange
        servicio.agregarProducto(new Producto(1, "Laptop", 2599.99, 10, electronica));
        servicio.agregarProducto(new Producto(2, "Mouse", 89.90, 45, electronica));
        servicio.agregarProducto(new Producto(3, "Taladro", 349.00, 15, herramientas));

        //ACT
        List<Producto> productos = servicio.listarProductos();

        //Assert
        assertEquals(3, productos.size());

    }

    // ==================== TESTS DE BÚSQUEDA ====================
    @Test
    @DisplayName("Debe lanzar excepción al buscar un producto que no existe")
    void debeLanzarExcepcionAlBuscarProductoInexistente() {
        // Act & Assert juntos porque la excepción es el resultado

        assertThrows(
                ProductoNoEncontradoException.class,
                () -> servicio.buscarProductoPorId(999));
    }
    
    
    @Test
    @DisplayName("Debe encontrar productos que coincidan con el nombre buscado")
    void debeBuscarProductosPorNombre() {
        // Arrange
        servicio.agregarProducto(new Producto(1, "Laptop Dell", 2599.99, 10, electronica));
        servicio.agregarProducto(new Producto(2, "Laptop HP", 1999.00, 5, electronica));
        servicio.agregarProducto(new Producto(3, "Mouse Logitech", 89.90, 45, electronica));

        // Act
        List<Producto> resultados = servicio.buscarProductoPorNombre("Laptop");

        // Assert
        assertEquals(2, resultados.size());
    }
    
     @Test
    @DisplayName("Debe retornar lista vacía si ningún producto coincide con la búsqueda")
    void debeRetornarListaVaciaSiNoHayCoincidencias() {
        // Arrange
        servicio.agregarProducto(new Producto(1, "Laptop Dell", 2599.99, 10, electronica));

        // Act
        List<Producto> resultados = servicio.buscarProductoPorNombre("Taladro");

        // Assert
        assertTrue(resultados.isEmpty());
    }
    
    // ==================== TESTS DE ELIMINACIÓN ====================

    @Test
    @DisplayName("Debe eliminar un producto existente correctamente")
    void debeEliminarProductoExistente() {
        // Arrange
        servicio.agregarProducto(new Producto(1, "Laptop Dell", 2599.99, 10, electronica));

        // Act
        servicio.eliminarProducto(1);

        // Assert: intentar buscarlo debe lanzar excepción
        assertThrows(
            ProductoNoEncontradoException.class,
            () -> servicio.buscarProductoPorId(1)
        );
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar un producto que no existe")
    void debeLanzarExcepcionAlEliminarProductoInexistente() {
        assertThrows(
            ProductoNoEncontradoException.class,
            () -> servicio.eliminarProducto(999)
        );
    }
    
    
    // ==================== TESTS DE VALIDACIÓN ====================

    @Test
    @DisplayName("Debe lanzar excepción al crear producto con precio negativo")
    void debeLanzarExcepcionConPrecioNegativo() {
        assertThrows(
            DatoInvalidoException.class,
            () -> new Producto(1, "Producto test", -50.0, 10, electronica)
        );
    }

    @Test
    @DisplayName("Debe lanzar excepción al buscar con nombre vacío")
    void debeLanzarExcepcionAlBuscarConNombreVacio() {
        assertThrows(
            IllegalArgumentException.class,
            () -> servicio.buscarProductoPorNombre("")
        );
    }


}
