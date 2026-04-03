/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.util;

/**
 *
 * @author JEFERSON
 */
public class Stack<T> {

    private Object[] elementos;
    private int tope;
    private static final int CAPACIDAD_INICIAL = 10;

    public Stack() {
        this.elementos = new Object[CAPACIDAD_INICIAL];
        tope = -1;
    }

    public void push(T elemento) {
        if (tope == elementos.length - 1) {
            crecer();
        }

        elementos[++tope] = elemento;
    }
    
    @SuppressWarnings("unchecked")
    public T peek() {
        if (estaVacio()) {
            throw new RuntimeException("Stack vacío — no hay elementos para ver");
        }

        return (T) elementos[tope];
    }
    
    @SuppressWarnings("unchecked")
    public T pop() {
        if (estaVacio()) {
            throw new RuntimeException("Stack vacío — no hay elementos para extraer");
        }
        
        T elemento = (T) elementos[tope];
        elementos[tope--] = null;
        return elemento;
    }

    public boolean estaVacio() {
        return tope == -1;
    }

    public int tamanio() {
        return tope + 1;
    }

    private void crecer() {
        Object nuevo[] = new Object[elementos.length * 2];
        System.arraycopy(elementos, 0, nuevo, 0, elementos.length);
        elementos = nuevo;
    }

}
