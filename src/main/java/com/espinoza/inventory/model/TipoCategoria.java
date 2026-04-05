/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.model;

import java.util.Optional;

/**
 *
 * @author JEFERSON
 */
public enum TipoCategoria {

    ELECTRONICA("Electrónica", "Dispositivos electrónicos y accesorios"),
    HERRAMIENTAS("Herramientas", "Herramientas manuales y eléctricas"),
    OFICINA("Oficina", "Artículos de oficina y papelería"),
    LIMPIEZA("Limpieza", "Productos de limpieza e higiene"),
    OTROS("Otros", "Categoría general");

    private final String nombre;
    private final String descripcion;

    private TipoCategoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static Optional<TipoCategoria> buscarPorNombre(String nombre) {
        for (TipoCategoria tipo : values()) {
            
            if (tipo.getNombre().equalsIgnoreCase(nombre)) {
                return Optional.of(tipo);
            }
            
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return nombre;
    }

}
