package com.aeromexico.tideveloper.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author mperal01
 */
@Table(name = "tabApisVersionesResources")
@Entity
public class ApisVersionesResources implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idApiVersionResource")
    private Integer id;
    @Column(name = "nombre")
    private String nombreResource;
    private String dirResource;
    @Transient
    private String[] nombreResources;
    @Transient
    private MultipartFile[] files;
   

    public ApisVersionesResources() {
    }

    public ApisVersionesResources(String nombreResource, String dirResource) {

        this.nombreResource = nombreResource;
        this.dirResource = dirResource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreResource() {
        return nombreResource;
    }

    public void setNombreResource(String nombreResource) {
        this.nombreResource = nombreResource;
    }

    public String getDirResource() {
        return dirResource;
    }

    public void setDirResource(String dirResource) {
        this.dirResource = dirResource;
    }

    public String[] getNombreResources() {
        return nombreResources;
    }

    public void setNombreResources(String[] nombreResources) {
        this.nombreResources = nombreResources;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
     

    @Override
    public String toString() {
        return "ApisVersionesResources{" + "id=" + id + ", nombreResource=" + nombreResource + ", dirResource=" + dirResource + ", nombreResources=" + nombreResources + ", files=" + files + '}';
    }

}
