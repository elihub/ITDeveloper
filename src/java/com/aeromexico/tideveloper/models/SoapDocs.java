package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mperal01
 */
@Entity
@Table(name = "tabSoapDocs")
public class SoapDocs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSoapDoc")
    private Integer id;
    @Column(name = "nombre")
    private String nombreDoc;
    @Column(name = "resumen")
    private String resumenDoc;
    private String dirDoc;
    @Transient
    private String[] nombreDocs;
    @Transient
    private String[] resumenDocs;
    @Transient
    private MultipartFile[] filesDocs;

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

    public String[] getNombreDocs() {
        return nombreDocs;
    }

    public void setNombreDocs(String[] nombreDocs) {
        this.nombreDocs = nombreDocs;
    }

    public String[] getResumenDocs() {
        return resumenDocs;
    }

    public void setResumenDocs(String[] resumenDocs) {
        this.resumenDocs = resumenDocs;
    }

    public MultipartFile[] getFilesDocs() {
        return filesDocs;
    }

    public void setFilesDocs(MultipartFile[] filesDocs) {
        this.filesDocs = filesDocs;
    }

    @Override
    public String toString() {
        return "SoapDocs{" + "id=" + id + ", nombreDoc=" + nombreDoc + ", resumenDoc=" + resumenDoc
                + ", dirDoc=" + dirDoc + ", nombreDocs=" + nombreDocs + ", resumenDocs=" + resumenDocs 
                + ", filesDocs=" + filesDocs + '}';
    }
    
}
