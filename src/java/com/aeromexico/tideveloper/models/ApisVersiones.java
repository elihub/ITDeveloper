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

/**
 *
 * @author Elida Carrillo
 */
@Table(name="tabApisVersiones")
@Entity
public class ApisVersiones implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idApiVersion")
    private Integer id;
    private Double version;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idApiVersion")
    private List<ApisVersionesResources> resources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public List<ApisVersionesResources> getResources() {
        return resources;
    }

    public void setResources(List<ApisVersionesResources> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "ApisVersiones{" + "id=" + id + ", version=" + version + ", resources=" + resources + '}';
    }
    
}
