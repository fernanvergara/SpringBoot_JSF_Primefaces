package com.sti.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sti.example.exception.ObjetoNoEncontradoError;
import com.sti.example.model.Categoria;
import com.sti.example.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> todasLasCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> categoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria categoriaPorNombre(String nombre){
        Optional<Categoria> existe = categoriaRepository.findByNombre(nombre);
        if(existe.isPresent()){
            return existe.get();
        }else{
            throw new ObjetoNoEncontradoError("Categoria '"+nombre+"' no encontrada ");
        }
    }

    public Categoria createCategory(Categoria categoria) {
        Optional<Categoria> existe = categoriaRepository.findByNombre(categoria.getNombre());
        if(existe.isPresent()){
            throw new ObjetoNoEncontradoError("Categoria '"+categoria.getNombre()+"' ya registrada");
        }else{
            return categoriaRepository.save(categoria);
        }
    }

    @Transactional
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Optional<Categoria> existe = categoriaRepository.findById(id);
        if (existe.isPresent()) {
            Categoria updatedCategory = existe.get();
            updatedCategory.setNombre(categoria.getNombre());
            return categoriaRepository.save(updatedCategory);
        } else {
            throw new ObjetoNoEncontradoError("Category '"+categoria.getNombre()+"' no encontrada id:" + id);
        }
    }

    public boolean borrarCategoria(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id); 
        if (categoria.isPresent()) {
            categoriaRepository.deleteById(id);
            return true;
        } else {
            throw new ObjetoNoEncontradoError("Categoriano no encontrada con id:" + id);
        }
    }
}
