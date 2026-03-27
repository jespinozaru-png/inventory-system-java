/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.repository;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Categoria;
import com.espinoza.inventory.model.Producto;
import com.espinoza.inventory.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author JEFERSON
 */
public class ProductoRepositoryJDBC implements Repositorio<Producto, Integer> {

    private final Connection conexion;

    public ProductoRepositoryJDBC() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    @Override
    public void guardar(Producto producto) {
        String sql = "INSERT INTO productos (idProducto, nombre, precio, cantidad, categoria) VALUES (?,?,?,?,?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId());
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getCantidad());
            stmt.setInt(5, producto.getCategoria().getIdCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el producto " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {
        String sql = "SELECT p.idProducto, p.nombre, p.precio, p.cantidad, "
                + "c.idCategoria AS cat_id, c.nombre AS cat_nombre, c.descripcion AS cat_des"
                + "FROM productos p"
                + "INNER JOIN categorias c ON p.categoria_id=c.idCategoria"
                + "where p.idProducto= ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearProducto(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar producto por id: "
                    + e.getMessage(), e);
        }
    }

    @Override
    public List<Producto> buscarTodos() {
        String sql = "SELECT p.idProducto, p.nombre, p.precio, p.cantidad"
                + "c.idCategoria AS cat_id, c.nombre AS cat_nombre, c.descripcion AS cat_des"
                + "FROM productos p"
                + "INNER JOIN categorias c ON p.categoria_id=c.idCategoria "
                + "ORDER BY p.nombre ASC";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos: " + e.getMessage(), e);

        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, precio = ?, cantidad=?"
                + "categoria_id=? WHERE idProducto=?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidad());
            stmt.setInt(4, producto.getCategoria().getIdCategoria());
            stmt.setInt(5, producto.getId());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException(producto.getId());

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos: " + e.getMessage(), e);

        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM productos WHERE idProducto=?";
  
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar producto: "
                    + e.getMessage(), e);
        }
    }

    public List<Producto> buscarPorNombre(String nombre) {
        String sql = "SELECT p.idProducto, p.nombre, p.precio, p.cantidad"
                + "c.idCategoria AS cat_id, c.nombre AS cat_nombre, c.descripcion AS cat_des"
                + "FROM productos p"
                + "INNER JOIN categorias on p.categoria_id=c.idCategoria"
                + "WHERE p.nombre LIKE ?";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                productos.add(mapearProducto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar por nombre: "
                    + e.getMessage(), e);
        }
        return productos;
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria(
        rs.getInt("cat_id"),
        rs.getString("cat_nombre"),
        rs.getString("cat_des"));

        return new Producto(
                rs.getInt("idProducto"),
                rs.getString("nombre"),
                rs.getDouble("precio"),
                rs.getInt("cantidad"),
                categoria);
    }
}
