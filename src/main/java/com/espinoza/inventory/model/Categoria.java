/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.model;

/**
 *
 * @author JEFERSON
 */
public class Categoria {
    private int idCategoria;
    private String nombre;
    private String descripcion;

    public Categoria() {
    }

    public Categoria(int idCategoria, String nombre, String descripcion) {
        if (nombre==null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        this.idCategoria = idCategoria;
        this.nombre = nombre.trim();
        this.descripcion = descripcion != null ? descripcion.trim() : "";
    }

    public int getIdCategoria() {
        return idCategoria;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion.trim() : " " ;
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
    
}
