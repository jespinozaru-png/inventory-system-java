/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espinoza.inventory.service;

import com.espinoza.inventory.model.Categoria;
import com.espinoza.inventory.repository.Repositorio;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author JEFERSON
 */
public class CategoriaService {
    private final Repositorio<Categoria, Integer> repositorio;

    public CategoriaService(Repositorio<Categoria, Integer> repositorio) {
        this.repositorio = repositorio;
    }
    
    
    public void agregarCategoria(Categoria categoria){
        repositorio.guardar(categoria);
    }
    
    public Optional<Categoria> buscarPorId(int id){
       return  repositorio.buscarPorId(id);
    }
    
    public List<Categoria> listarCategorias(){
        return repositorio.buscarTodos();
    }
}
