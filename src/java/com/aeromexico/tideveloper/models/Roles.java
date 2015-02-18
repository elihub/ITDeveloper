package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "catRoles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idRol;
    private String nombre;
    private String descripcion;
    /*@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listaRoles")
    private List<Usuarios> listaUsuarios = new ArrayList<>(0);*/

    public Long getIdRol() {
        return idRol;
    }
    public void setIdRol(Long idRol) {
            this.idRol = idRol;
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
    /*
    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    */ 
	@Override
	public String toString() {
		return "Roles [idRol=" + idRol + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}
	
	
}
