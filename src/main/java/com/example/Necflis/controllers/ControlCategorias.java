package com.example.Necflis.controllers;

import com.example.Necflis.entities.Categoria;
import com.example.Necflis.repositories.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categoria")
public class ControlCategorias {


    @Autowired
    RepositorioCategoria repositorioCategoria;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria NuevaCategoria(@RequestBody Categoria categoria, @DateTimeFormat(pattern = "yyyy.MM.ddd") Date date){
        Categoria nuevaCategoria = repositorioCategoria.save(categoria);
        return nuevaCategoria;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Categoria editarCategoria(@PathVariable(name = "id") Long id , @RequestBody Categoria categoria){
        Optional<Categoria> categoriavieja = repositorioCategoria.findById(id);
        if(categoriavieja.isPresent()){
            Categoria actual = categoriavieja.get();
            actual.setIdCategoria(id);
            actual.setNombre(categoria.getNombre());
            actual.setDescripcion(categoria.getDescripcion());
            actual.setFecha(categoria.getFecha());
            Categoria categorianueva = repositorioCategoria.save(actual);
            return categorianueva;
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void eliminarCategoria(@PathVariable(name = "id") Long id){

        repositorioCategoria.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Categoria> TodasCategorias(){
        Iterable<Categoria> categorias = repositorioCategoria.findAll();
        return (Collection<Categoria>) categorias;
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Categoria ObtenerCategoria(@PathVariable(name = "id") Long id){
        Optional<Categoria> categoria = repositorioCategoria.findById(id);
        Categoria result = null;
        if(categoria.isPresent()){
            result = categoria.get();
        }
        return result;
    }







}
