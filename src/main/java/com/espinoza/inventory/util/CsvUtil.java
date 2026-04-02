/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.util;

import com.espinoza.inventory.model.Categoria;
import com.espinoza.inventory.model.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEFERSON
 */
public class CsvUtil {

    private static final String CABECERA = "id, nombre, precio, categoria_id, categoria_nombre ";
    private static final String SEPARADOR = ",";

    public static void exportarProductos(List<Producto> productos, String rutaArchivo) {
        Path ruta = Paths.get(rutaArchivo);

        try (BufferedWriter writer = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            writer.write(CABECERA);
            writer.newLine();
            for (Producto p : productos) {
                String linea = String.join(SEPARADOR,
                        String.valueOf(p.getId()),
                        escaparCampo(p.getNombre()),
                        String.valueOf(p.getPrecio()),
                        String.valueOf(p.getCantidad()),
                        String.valueOf(p.getCategoria().getIdCategoria()),
                        escaparCampo(p.getCategoria().getNombre()));
                writer.write(linea);
                writer.newLine();

            }

            System.out.println("Exportación exitosa: " + productos.size()
                    + " productos en " + rutaArchivo);
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar el CSV" + e.getMessage(), e);
        }

    }

    public static List<Producto> importarProductos(String rutaArchivo) {
        Path ruta = Paths.get(rutaArchivo);
        if (!Files.exists(ruta)) {
            throw new RuntimeException("El archivo no existe: " + rutaArchivo);
        }

        List<Producto> productos = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            String linea;
            boolean esCabecera = true;
            int numLinea = 0;

            while ((linea = reader.readLine()) != null) {
                numLinea++;

                if (esCabecera) {
                    esCabecera = false;
                    continue;
                }
                if (linea.trim().isEmpty()) {
                    continue;
                }

                try {
                    Producto producto = parsearLinea(linea);
                    productos.add(producto);
                } catch (Exception e) {
                    System.err.println("Error en línea " + numLinea
                            + ": " + e.getMessage() + " — línea ignorada");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al importar CSV: " + e.getMessage(), e);
        }

        System.out.println("Importación exitosa: " + productos.size() + " productos cargados.");
        return productos;
    }

    private static Producto parsearLinea(String linea) {
        String[] campos = linea.split(SEPARADOR, -1);

        if (campos.length < 6) {
            throw new IllegalArgumentException("Formato inválido, se esperan 6 campos");

        }

        int id = Integer.parseInt(campos[0].trim());
        String nombre = limpiarCampo(campos[1]);
        double precio = Double.parseDouble(campos[2]);
        int cantidad = Integer.parseInt(campos[3]);
        int categoriaId = Integer.parseInt(campos[4].trim());
        String categoriaNombre = limpiarCampo(campos[5]);

        Categoria categoria = new Categoria(categoriaId, categoriaNombre, "");
        return new Producto(id, nombre, precio, cantidad, categoria);
    }

    private static String escaparCampo(String campo) {
        if (campo.contains(SEPARADOR) || campo.contains("\"")) {
            return "\"" + campo.replace("\"", "\"\"") + "\"";
        }
        return campo;
    }

    private static String limpiarCampo(String campo) {
        campo = campo.trim();

        if (campo.startsWith("\"") && campo.endsWith("\"")) {
            campo = campo.substring(1, campo.length() - 1).replace("\"\"", "\"");
        }
        return campo;
    }

}
