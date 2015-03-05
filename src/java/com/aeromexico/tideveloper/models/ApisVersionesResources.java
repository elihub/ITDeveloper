package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mperal01
 */
@Table(name="tabApisVersionesResources")
@Entity
public class ApisVersionesResources implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idApiVersionResource")
    private Integer id;
    private String nombre;
    private String dirResource;

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

    public String getDirResource() {
        return dirResource;
    }

    public void setDirResource(String dirResource) {
        this.dirResource = dirResource;
    }

    @Override
    public String toString() {
        return "ApisVersionesResources{" + "id=" + id + ", nombre=" + nombre + ", dirResource=" + dirResource + '}';
    }
    
}