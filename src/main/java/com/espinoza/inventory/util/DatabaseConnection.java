/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author JEFERSON
 */
public class DatabaseConnection {

    private static DatabaseConnection instancia;
    private Connection conexion;

    private DatabaseConnection() {
        try {
            Properties props = cargarPropiedades();
            String url = props.getProperty("db.url");
            String usuario = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            this.conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion a base de datos establecida...");
        } catch (SQLException ex) {
            throw new RuntimeException("Error al conectar con la base de datos: " + ex.getMessage(), ex);
        }
    }

    private Properties cargarPropiedades() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró config.properties. "
                        + "Copia config.properties.example y configura tus credenciales.");
            }

            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer config.properties: "
                    + e.getMessage(), e);
        }

        return props;
    }

    public static DatabaseConnection getInstance() {
        if (instancia == null) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
              instancia = new DatabaseConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar conexión: "
                    + e.getMessage(), e);
        }
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexion correctamente cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }
}
