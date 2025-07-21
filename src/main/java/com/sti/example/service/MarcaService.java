package com.sti.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sti.example.exception.ObjetoNoEncontradoError;
import com.sti.example.model.Marca;
import com.sti.example.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> todasLasMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> marcaPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca marcaPoNombre(String nombre){
        Optional<Marca> existe = marcaRepository.findByNombre(nombre);
        if(existe.isPresent()){
            return existe.get();
        }else{
            throw new ObjetoNoEncontradoError("Marca '"+nombre+"' no encontrada ");
        }
    }

    public Marca crearMarca(Marca marca) {
        Optional<Marca> existe = marcaRepository.findByNombre(marca.getNombre());
        if(existe.isEmpty()){
            return marcaRepository.save(marca);
        }else{
            throw new ObjetoNoEncontradoError("Marca '"+marca.getNombre()+"' ya registrada");
        }
    }

    @Transactional
    public Marca actualizarMarca(Long id, Marca marca) {
        Optional<Marca> existe = marcaRepository.findById(id);
        if (existe.isPresent()) {
            Marca actualizada = existe.get();
            actualizada.setNombre(marca.getNombre());
            return marcaRepository.save(actualizada);
        } else {
            throw new ObjetoNoEncontradoError("Marca '"+marca.getNombre()+"' con encontrado con id:" + id);
        }
    }

    public boolean borrarMarca(Long id) {
        Optional<Marca> marca = marcaRepository.findById(id); 
        if (marca.isPresent()) {
            marcaRepository.deleteById(id);
            return true;
        } else {
            throw new ObjetoNoEncontradoError("Marca no encontrada con id:" + id);
        }
    }
}
