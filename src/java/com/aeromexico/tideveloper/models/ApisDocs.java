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
@Table(name="tabApisDocs")
@Entity
public class ApisDocs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idApiDoc")
    private Integer id;
    @Column(name="nombre")
    private String nombreDoc;
    @Column(name="resumen")
    private String resumenDoc;
    private String dirDoc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getResumenDoc() {
        return resumenDoc;
    }

    public void setResumenDoc(String resumenDoc) {
        this.resumenDoc = resumenDoc;
    }

    


    public String getDirDoc() {
        return dirDoc;
    }

    public void setDirDoc(String dirDoc) {
        this.dirDoc = dirDoc;
    }

    @Override
    public String toString() {
        return "ApisDocs{" + "id=" + id + ", nombre=" + nombreDoc + ", resumen=" + resumenDoc + ", dirDoc=" + dirDoc + '}';
    }
    
}
