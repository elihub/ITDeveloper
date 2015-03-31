package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author mperal01
 */
@Entity
@Table( name= "tabSoap")
public class Soap implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idSoap")
    private int id;
    private String nombre;
    @OneToOne
    @JoinColumn(name = "idArea")
    private Area area;
    @OneToOne
    @JoinColumn(name = "idFuncion")
    private Funcion funcion;
    private String descripcion;
    private String resumen;
    private String serviceActionCode;
    private int usuarioCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "idSoap",nullable = false)
    private List<SoapVersiones> versiones;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "idSoap",nullable = false)
    private List<SoapDocs> docs;

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
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

    public String getServiceActionCode() {
        return serviceActionCode;
    }

    public void setServiceActionCode(String serviceActionCode) {
        this.serviceActionCode = serviceActionCode;
    }

    public int getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(int usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
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

    public List<SoapVersiones> getVersiones() {
        return versiones;
    }

    public void setVersiones(List<SoapVersiones> versiones) {
        this.versiones = versiones;
    }

    public List<SoapDocs> getDocs() {
        return docs;
    }

    public void setDocs(List<SoapDocs> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "Soap{" + "id=" + id + ", nombre=" + nombre + ", area=" + area + ", funcion=" + funcion + ", descripcion=" + descripcion + ", resumen=" + resumen + ", serviceActionCode=" + serviceActionCode + ", usuarioCreacion=" + usuarioCreacion + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + ", versiones=" + versiones + ", docs=" + docs + '}';
    }
    
    
}
