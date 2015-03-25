package com.aeromexico.tideveloper.models;

import java.io.Serializable;
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
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author mperal01
 */
@Entity
@Table( name= "tabSoapVersiones")
public class SoapVersiones implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idSoapVersion")
    private int id;
    private Double version;
    @Fetch(value = FetchMode.SUBSELECT)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "idSoapVersion",nullable = false)
    private List<SoapVersionesResources> resources;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public List<SoapVersionesResources> getResources() {
        return resources;
    }

    public void setResources(List<SoapVersionesResources> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "SoapVersiones{" + "id=" + id + ", version=" + version + ", resources=" + resources + '}';
    }
    
}
