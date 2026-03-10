/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.model;

import com.espinoza.inventory.exception.DatoInvalidoException;

/**
 *
 * @author JEFERSON
 */
public class Producto {

    private int idProducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private Categoria categoria;

    public Producto(int idProducto, String nombre, double precio, int cantidad, Categoria categoria) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("nombre", nombre, "El nombre del producto no puede estar vacío");
        }

        if (precio < 0) {
            throw new DatoInvalidoException("precio", precio, "El precio no puede ser negativo");
        }

        if (cantidad < 0) {
            throw new DatoInvalidoException("cantidad", cantidad, "la cantidad no puede ser negativa");
        }

        if (categoria == null) {
            throw new DatoInvalidoException("categoria", categoria, "El producto debe tener una categoría");
        }

        this.idProducto = idProducto;
        this.nombre = nombre.trim();
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public int getId() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("nombre", nombre, "El nombre del producto no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new DatoInvalidoException("precio", precio, "El precio no puede ser negativo");
        }
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            throw new DatoInvalidoException("cantidad", cantidad, "la cantidad no puede ser negativa");
        }
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new DatoInvalidoException("categoria", categoria, "El producto debe tener una categoría");
        }
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', precio=%.2f, cantidad=%d, categoria=%s}",
                idProducto, nombre, precio, cantidad, categoria.getNombre());
    }

}
