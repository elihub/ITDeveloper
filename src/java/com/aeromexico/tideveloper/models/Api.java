/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Elida Carrillo
 */
@Entity
@Table(name="tabApis")
public class Api implements Serializable{
    @Id    
    @GeneratedValue
    @Column(name = "idApi")
    private Integer id;
    private String nombre;
    private String descripcion;
    private String resumen;
    private Integer idUsuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "idApi",nullable = false)
    private List<ApisVersiones> versiones;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "idApi",nullable = false)
    private List<ApisDocs> docs;

   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<ApisVersiones> getVersiones() {
        return versiones;
    }

    public void setVersiones(List<ApisVersiones> versiones) {
        this.versiones = versiones;
    }
    
    public List<ApisDocs> getDocs() {
        return docs;
    }

    public void setDocs(List<ApisDocs> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Api{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", resumen=" + resumen + ", idUsuario=" + idUsuario + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", versiones=" + versiones + ", docs=" + docs + '}';
    }
    
}