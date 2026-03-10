/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.exception;

/**
 *
 * @author JEFERSON
 */
public class DatoInvalidoException extends RuntimeException {

    private String campo;
    private Object valorRecibido;

    public DatoInvalidoException(String campo, Object valorRecibido, String mensaje) {
        super(mensaje);
        this.campo = campo;
        this.valorRecibido = valorRecibido;
    }

    public String getCampo() {
        return campo;
    }

    public Object getValorRecibido() {
        return valorRecibido;
    }
}
