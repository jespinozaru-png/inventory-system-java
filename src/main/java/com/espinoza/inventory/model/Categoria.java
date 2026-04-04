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
public class Categoria {
    private int idCategoria;
    private TipoCategoria tipo;

    public Categoria() {
    }

    public Categoria(int idCategoria, TipoCategoria tipo) {
        if (tipo==null) {
            throw new IllegalArgumentException("El tipo de categoría no puede ser null");
        }
        this.idCategoria = idCategoria;
        this.tipo=tipo;
    }
    
    public Categoria(int idCategoria, String nombreTipo, String descripcion){
        this.idCategoria = idCategoria;
        this.tipo = TipoCategoria.buscarPorNombre(nombreTipo).orElse(TipoCategoria.OTROS);
    }

    public int getIdCategoria() {
        return idCategoria;
    }
    
    public TipoCategoria getTipo(){
        return tipo;
    }
    
    public String getNombre(){
        return tipo.getNombre();
    }

    public String getDescripcion() {
        return tipo.getDescripcion();
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", tipo=" + tipo.getNombre() +'}';
    }
}
