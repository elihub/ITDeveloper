package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Servicios;
import java.util.List;

/**
 *
 * @author mperal01
 */
public interface ServiciosDAO {
    Servicios findById(int id);
    List<Servicios> findAll();
    int save(Servicios s);
    void update(Servicios s);
    void delete(Servicios s);
    
}
