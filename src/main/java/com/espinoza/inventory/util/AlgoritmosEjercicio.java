/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.util;

/**
 *
 * @author JEFERSON
 */
public class AlgoritmosEjercicio {
     /**
     * Búsqueda lineal: recorre el array elemento por elemento
     * hasta encontrar el target o llegar al final.
     * Complejidad: O(n) — en el peor caso revisa todos los elementos.
     */
    public static int busquedaLineal(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i; // Retorna el índice donde lo encontró
            }
        }
        return -1; // Convención: -1 significa que no lo encontró
    }

    /**
     * Búsqueda binaria: solo funciona si el array está ORDENADO.
     * Divide el espacio de búsqueda a la mitad en cada iteración.
     * Complejidad: O(log n) — con 1000 elementos, máximo ~10 comparaciones.
     */
    public static int busquedaBinaria(int[] arrayOrdenado, int target) {
        int izquierda = 0;
        int derecha = arrayOrdenado.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arrayOrdenado[medio] == target) {
                return medio;
            }

            if (arrayOrdenado[medio] < target) {
                izquierda = medio + 1; // El target está en la mitad derecha
            } else {
                derecha = medio - 1;  // El target está en la mitad izquierda
            }
        }

        return -1;
    }

    /**
     * Ejercicios sobre arrays: máximo, mínimo, promedio
     */
    public static int encontrarMaximo(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        int maximo = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximo) {
                maximo = array[i];
            }
        }
        return maximo;
    }

    public static int encontrarMinimo(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        int minimo = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minimo) {
                minimo = array[i];
            }
        }
        return minimo;
    }

    public static double calcularPromedio(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        int suma = 0;
        for (int valor : array) {
            suma += valor;
        }
        return (double) suma / array.length;
    }
}
