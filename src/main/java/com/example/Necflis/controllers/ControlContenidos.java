package com.example.Necflis.controllers;



import com.example.Necflis.entities.Contenido;
import com.example.Necflis.repositories.RepositorioContenido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping(value = "/contenido")
public class ControlContenidos {

    @Autowired
    RepositorioContenido repositorioContenido;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Contenido crearContenido(@RequestBody Contenido contenido, @DateTimeFormat(pattern = "yyyy.MM.ddd") Date date){
        Contenido nuevoContenido = repositorioContenido.save(contenido);
        return nuevoContenido;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Contenido editarContenido(@PathVariable(name = "id") Long id , @RequestBody Contenido contenido){
        Optional<Contenido> contenidoviejo = repositorioContenido.findById(id);
        if(contenidoviejo.isPresent()){
            Contenido actual = contenidoviejo.get();
            actual.setId(id);
            actual.setNombre(contenido.getNombre());
            actual.setResumen(contenido.getResumen());
            actual.setFecha(contenido.getFecha());
            actual.setIdCategoria(contenido.getIdCategoria());
            actual.setIdGenero(contenido.getIdGenero());
            Contenido contenidonuevo = repositorioContenido.save(actual);
            return contenidonuevo;
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void borrarContenido(@PathVariable(name = "id") Long id){

        repositorioContenido.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Contenido> TodosContenidos(){
        Iterable<Contenido> contenidos = repositorioContenido.findAll();
        return (Collection<Contenido>) contenidos;
    }




    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Contenido obtenerContenido(@PathVariable(name = "id") Long id){
        Optional<Contenido> categoria = repositorioContenido.findById(id);
        Contenido result = null;
        if(categoria.isPresent()){
            result = categoria.get();
        }
        return result;
    }




}
