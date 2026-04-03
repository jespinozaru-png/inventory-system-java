/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.util;

/**
 *
 * @author JEFERSON
 */
public class Queue<T> {

    private Node<T> frente;
    private Node<T> final_;
    private int tamanio;

    private static class Node<T> {

        T dato;
        Node<T> siguiente;

        public Node(T dato) {
            this.dato = dato;
        }

    }

    public void enqueue(T elemento) {
        Node<T> nuevo = new Node<>(elemento);

        if (estaVacio()) {
            frente = nuevo;
            final_ = nuevo;
        } else {
            final_.siguiente = nuevo;
            final_ = nuevo;
        }
        tamanio++;
    }

    public T dequeue() {
        if (estaVacio()) {
            throw new RuntimeException("Queue vacía — no hay elementos para extraer");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            final_ = null;
        }
        tamanio--;
        return dato;
    }

    public T peek() {
        if (estaVacio()) {
            throw new RuntimeException("Queue vacía — no hay elementos para ver");
        }
        
        return frente.dato;
    }

    public boolean estaVacio() {
        return tamanio == 0;
    }

    public int tamanio() {
        return tamanio;
    }

}
