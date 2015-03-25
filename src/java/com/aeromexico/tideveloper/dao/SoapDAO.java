package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Soap;
import java.util.List;

/**
 *
 * @author mperal01
 */
public interface SoapDAO {
    Soap findById(int id);
    List<Soap> findAll();
    int save(Soap s);
    void update(Soap s);
    void delete(Soap s);
}
