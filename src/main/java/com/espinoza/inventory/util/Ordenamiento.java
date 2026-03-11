/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.util;

/**
 *
 * @author JEFERSON
 */
public class Ordenamiento {

    /**
     * Bubble Sort: compara pares adyacentes y "burbujea" el mayor hacia el
     * final. Complejidad: O(n²) en peor y caso promedio. O(n) en mejor caso (ya
     * ordenado). Modifica el array original.
     */
    public static void bubbleSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Selection Sort: en cada iteración encuentra el mínimo y lo coloca en su
     * posición. Complejidad: O(n²) siempre, incluso si el array ya está
     * ordenado. Hace menos intercambios que Bubble Sort pero las mismas
     * comparaciones.
     */
    public static void selectSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[indiceMenor]) {
                    indiceMenor = j;
                }
            }

            if (indiceMenor != i) {
                int temp = array[i];
                array[i] = array[indiceMenor];
                array[indiceMenor] = temp;
            }
        }

    }
}
