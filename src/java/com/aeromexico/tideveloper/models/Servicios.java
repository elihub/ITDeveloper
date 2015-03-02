package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mperal01
 */
@Entity
@Table(name = "tabServicios")
public class Servicios implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idServicio")
    private int id;
    private String nombre;
    @OneToOne
    @JoinColumn(name = "idArea")
    private Area area;
    @OneToOne
    @JoinColumn(name = "idFuncion")
    private Funcion funcion;
    @OneToOne
    @JoinColumn(name = "idServicioTipo")
    private ServiciosTipo servicioTipo;
    private String descripcion;
    private int idUsuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

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

    public ServiciosTipo getServicioTipo() {
        return servicioTipo;
    }

    public void setServicioTipo(ServiciosTipo servicioTipo) {
        this.servicioTipo = servicioTipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
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

    @Override
    public String toString() {
        return "Servicios{" + "id=" + id + ", nombre=" + nombre + ", funcion=" + funcion + ", servicioTipo=" + servicioTipo + ", descripcion=" + descripcion + ", idUsuario=" + idUsuario + ", fechaCreacion=" + fechaCreacion + ", fechaModificacion=" + fechaModificacion + '}';
    }
}
