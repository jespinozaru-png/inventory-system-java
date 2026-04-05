/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.repository;

import com.espinoza.inventory.exception.ProductoNoEncontradoException;
import com.espinoza.inventory.model.Categoria;
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
public class CategoriaRepositoryJDBC implements Repositorio<Categoria, Integer> {

    private final Connection conexion;

    public CategoriaRepositoryJDBC() {
        this.conexion = DatabaseConnection.getInstance().getConexion();
    }

    @Override
    public void guardar(Categoria categoria) {
        String sql = "INSERT INTO categorias (idCategoria, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, categoria.getIdCategoria());
            stmt.setString(2, categoria.getNombre());
            stmt.setString(3, categoria.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar categoría: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Categoria> buscarPorId(Integer id) {
        String sql = "SELECT idCategoria, nombre, descripcion FROM categorias WHERE idCategoria = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearCategoria(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar categoría: " + e.getMessage(), e);

        }
    }

    @Override
    public List<Categoria> buscarTodos() {
        String sql = "SELECT idCategoria, nombre, descripcion FROM categorias ORDER BY nombre ";
        List<Categoria> categorias = new ArrayList<>();
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categorias.add(mapearCategoria(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar categorías: " + e.getMessage(), e);

        }

        return categorias;
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nombre?, descripcion=? WHERE idCategoria=?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getIdCategoria());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException(categoria.getIdCategoria());

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar categorias: " + e.getMessage(), e);

        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM categorias WHERE idCategoria=? ";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException(id);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar categoria: "
                    + e.getMessage(), e);
        }
    }

    private Categoria mapearCategoria(ResultSet rs) throws SQLException {
        return new Categoria(
                rs.getInt("idCategoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"));

    }

}
