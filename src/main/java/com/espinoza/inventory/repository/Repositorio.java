/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author JEFERSON
 */
public interface Repositorio <T, ID>{
    
    void guardar(T entidad);
    Optional<T> buscarPorId(ID id);
    List<T> buscarTodos();
    void actualizar(T entidad);
    void eliminar(ID id);
}
