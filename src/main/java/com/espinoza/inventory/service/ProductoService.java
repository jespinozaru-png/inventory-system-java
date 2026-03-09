/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.service;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Producto;
import com.espinoza.inventory.repository.ProductoRepositoryMemoria;
import java.util.List;

/**
 *
 * @author JEFERSON
 */
public class ProductoService {

    private final ProductoRepositoryMemoria repositorio;

    public ProductoService(ProductoRepositoryMemoria repositorio) {
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

    public List<Producto> buscarProductoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");

        }
        return repositorio.buscarPorNombre(nombre.trim());
    }

    public void eliminarProducto(int id) {
        repositorio.eliminar(id);
    }

    public void actualizarProducto(Producto producto) {
        repositorio.actualizar(producto);
    }
}
