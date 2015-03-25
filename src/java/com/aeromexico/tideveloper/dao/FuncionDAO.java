/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Funcion;
import java.util.List;

/**
 *
 * @author Elida Carrillo
 */
public interface FuncionDAO {
    Funcion findById(int id);
    List<Funcion> findAll();
    int save(Funcion s);
    void update(Funcion s);
    void delete(Funcion s);
}
