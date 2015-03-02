/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Api;
import java.util.List;

/**
 *
 * @author Elida Carrillo
 */
public interface ApiDAO {
    Api findById(int id);
    List<Api> findAll();
    int save(Api s);
    void update(Api s);
    void delete(Api s); 
}
