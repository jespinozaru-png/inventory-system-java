/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.model;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author JEFERSON
 */
public class Inventario {

    private final Map<Integer, Producto> productosPorId = new HashMap<>();

    private final List<Producto> listaOrdenada = new ArrayList<>();

    public void agregar(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        if (productosPorId.containsKey(producto.getId())) {
            throw new IllegalArgumentException("Ya existe un producto con id: " + producto.getId()
            );
        }
        productosPorId.put(producto.getId(), producto);
        listaOrdenada.add(producto);
    }

    public Optional<Producto> buscarPorId(int id) {
        return Optional.ofNullable(productosPorId.get(id));
    }


    /*
    ***********MÉTODO CLASICO BUSCAR POR ID************
    metodo 1
    
    public Producto buscarPorId(int id) {
        Producto producto = productosPorId.get(id);

        if (producto != null) {
            return producto;
        }

        return null;
    }
    
    metodo 2
    
    public Producto buscarPorId(int id) {

        if (productosPorId.containsKey(id)) {
            return productosPorId.get(id);
        }

        return null;
    }
     */
    public List<Producto> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        String nombreLower = nombre.trim().toLowerCase();
        return listaOrdenada.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombreLower))
                .toList();
    }

    /*
    ***********MÉTODO CLASICO BUSCAR POR NOMBRE************

    
    public List<Producto> buscarPorNombre(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        
        String nombreLower = nombre.toLowerCase().trim();
        
        List<Producto> nueva = new ArrayList<>();
        for (Producto p : listaOrdenada) {
            
            String nombreProducto = p.getNombre().toLowerCase();
            
            if (nombreProducto.contains(nombreLower)) {
                nueva.add(p);
            }
        }
        
        return nueva;
    }
     */
    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }

        return listaOrdenada.stream()
                .filter(p -> p.getCategoria().getNombre()
                .equalsIgnoreCase(nombreCategoria.trim()))
                .toList();
    }

    /*
    ***********MÉTODO CLASICO BUSCAR POR CATEGORIA************

    public List<Producto> buscarPorCategoria(String nombreCategoria) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        
        String nombreCategoriaLower = nombreCategoria.trim().toLowerCase();
        
        List<Producto> resultado = new ArrayList<>();
        
        for (Producto p : listaOrdenada) {
            String categoria = p.getCategoria().getNombre().trim().toLowerCase();
            
            if (categoria.contains(nombreCategoriaLower)) {
                
            }
        }
        
        return resultado;        
    }
     */
    public void eliminar(int id) {
        Producto producto = productosPorId.remove(id);

        if (producto == null) {
            throw new ProductoNoEncontradoException(id);
        }

        listaOrdenada.remove(producto);
    }

    /*
    ***********MÉTODO CLASICO ELIMINAR************
    public void eliminar(int id) {
        Producto producto = productosPorId.get(id);

        if (producto == null) {
            throw new ProductoNoEncontradoException(id);
        }

        productosPorId.remove(id);

        for (int i = 0; i < listaOrdenada.size(); i++) {
            if (listaOrdenada.get(i).getId() == id) {
                listaOrdenada.remove(i);
                break;
            }
        }

    }
     */
    public List<Producto> listarTodos() {
        return new ArrayList<>(listaOrdenada);
    }

    /*
    ***********MÉTODO CLASICO LISTAR TODO************    
    
    public List<Producto> listarTodos(){
        List<Producto> lista = new ArrayList<>();
        
        for (int i = 0; i < listaOrdenada.size(); i++) {
            lista.add(listaOrdenada.get(i));
        }
        
        return lista;
    }
     */
    public int totalProducto() {
        return listaOrdenada.size();
    }

    public boolean estaVacio() {
        return listaOrdenada.isEmpty();
    }
}
