/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Area;
import java.util.List;

/**
 *
 * @author Elida Carrillo
 */
public interface AreaDAO {

    Area findById(int id);

    List<Area> findAll();

    int save(Area s);

    void update(Area s);

    void delete(Area s);
}
