/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.exception;

/**
 *
 * @author JEFERSON
 */
public class ProductoNoEncontradoException extends RuntimeException {
    private final int productoId;

    public ProductoNoEncontradoException(int productoId) {
        super("No se encontro ningún producto con id: " + productoId);
        this.productoId = productoId;
    }

    public int getProductoId() {
        return productoId;
    }
    
    
    
}
