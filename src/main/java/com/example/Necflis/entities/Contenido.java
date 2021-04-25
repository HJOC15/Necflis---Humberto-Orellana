package com.example.Necflis.entities;



import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contenido")
public class Contenido {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "resumen")
    private String resumen;

    @Column(name = "categoria")
    private Long idCategoria;

    @Column(name = "genero")
    private Long idGenero;


    @Column(name = "fecha")

    private Date fecha;


    public Contenido() {
    }

    public Contenido(Long id, String nombre, String resumen, Long idCategoria, Long idGenero, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.resumen = resumen;
        this.idCategoria = idCategoria;
        this.idGenero = idGenero;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }


    public Long getIdCategoria() {

        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Long getIdGenero() {

        return idGenero;
    }

    public void setIdGenero(Long idGenero) {
        this.idGenero = idGenero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(@DateTimeFormat(pattern = "yyyy.MM.ddd")  Date fecha) {
        this.fecha = fecha;
    }


}
