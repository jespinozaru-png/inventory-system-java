/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.service;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Producto;
import com.espinoza.inventory.repository.ProductoRepositoryJDBC;
import com.espinoza.inventory.repository.ProductoRepositoryMemoria;
import com.espinoza.inventory.repository.Repositorio;
import java.util.List;

/**
 *
 * @author JEFERSON
 */
public class ProductoService {

    private final Repositorio<Producto, Integer> repositorio;

    public ProductoService(Repositorio<Producto, Integer> repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarProducto(Producto producto) {
        repositorio.guardar(producto);
    }

    public List<Producto> listarProductos() {
        return repositorio.buscarTodos();
    }

    public Producto buscarProductoPorId(int id) {
        return repositorio.buscarPorId(id).orElseThrow(() -> new ProductoNoEncontradoException(id));
    }
/*
    
    
    public List<Producto> buscarProductoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");

        }
        return repositorio.buscarPorNombre(nombre.trim());
    }
*/
    public List<Producto> buscarProductoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        
        if (repositorio instanceof ProductoRepositoryJDBC jdbc) {
            return jdbc.buscarPorNombre(nombre.trim());
        }
        
        if (repositorio instanceof ProductoRepositoryMemoria memoria ) {
            return memoria.buscarPorNombre(nombre.trim());
        }
        return List.of();
    }

    public void eliminarProducto(int id) {
        repositorio.eliminar(id);
    }

    public void actualizarProducto(Producto producto) {
        repositorio.actualizar(producto);
    }
}
