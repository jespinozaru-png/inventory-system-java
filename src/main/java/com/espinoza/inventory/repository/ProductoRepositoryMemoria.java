/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.repository;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author JEFERSON
 */
public class ProductoRepositoryMemoria implements Repositorio<Producto, Integer> {

    private List<Producto> productos = new ArrayList<>();

    @Override
    public void guardar(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        productos.add(producto);
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    /* 
    **********MÉTODO CLÁSICO*************
    public Producto buscarPorId(int id){
        for (Producto p : productos) {
            if (p.getId()==id) {
                return p;
            }
        }
        return null;
    }*/
    @Override
    public List<Producto> buscarTodos() {
        return new ArrayList(productos);
    }

    /* 
    **********MÉTODO CLÁSICO*************
    public List<Producto> buscarTodoss(){
        List<Producto> copia = new ArrayList<>();
        
        for (Producto p : productos) {
            copia.add(p);
        }
        return copia;
    }*/
    @Override
    public void actualizar(Producto productoActualizado) {

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                return;
            }
        }
        throw new ProductoNoEncontradoException(productoActualizado.getId());
    }

    @Override
    public void eliminar(Integer id) {
        boolean eliminado = productos.removeIf(p -> p.getId() == id);
        if (!eliminado) {
            throw new ProductoNoEncontradoException(id);
        }
    }

    /*
    *********MÉTODO CLÁSICO *************
    public boolean eliminar(int id) {
        for (Producto p : productos) {
            if (p.getId()==id) {
                return true;
            }
        }
        return false;
    }
     */
    public List<Producto> buscarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre()
                .toLowerCase()
                .contains(nombre.toLowerCase())).toList();
    }

    /*
    *********MÉTODO CLÁSICO *************
    public List<Producto> buscarPorNombree(String nombre){
        List<Producto> lista = new ArrayList<>();
        for (Producto p : productos ) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                lista.add(p);
            }
        }
          return lista;      
    }
     */
}
