package com.example.Necflis.controllers;

import com.example.Necflis.entities.Genero;
import com.example.Necflis.repositories.RepositorioGenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/generos")
public class ControlGenero {


    @Autowired
    RepositorioGenero repositorioGenero;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Genero crearGenero(@RequestBody Genero genero, @DateTimeFormat(pattern = "yyyy.MM.ddd") Date date){
        Genero nuevoGenero = repositorioGenero.save(genero);
        return nuevoGenero;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Genero editarGenero(@PathVariable(name = "id") Long id , @RequestBody Genero genero){
        Optional<Genero> generoviejo = repositorioGenero.findById(id);
        if(generoviejo.isPresent()){
            Genero actual = generoviejo.get();
            actual.setIdGenero(id);
            actual.setNombre(genero.getNombre());
            actual.setDescripcion(genero.getDescripcion());
            actual.setFecha(genero.getFecha());
            Genero generonuevo = repositorioGenero.save(actual);
            return generonuevo;
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void borrarGenero(@PathVariable(name = "id") Long id){

        repositorioGenero.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Genero> TodosGeneros(){
        Iterable<Genero> generos = repositorioGenero.findAll();
        return (Collection<Genero>) generos;
    }




    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Genero obtenerGenero(@PathVariable(name = "id") Long id){
        Optional<Genero> genero = repositorioGenero.findById(id);
        Genero result = null;
        if(genero.isPresent()){
            result = genero.get();
        }
        return result;
    }




}
