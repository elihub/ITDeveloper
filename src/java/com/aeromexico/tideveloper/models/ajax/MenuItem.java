/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.models.ajax;

import java.util.List;

/**
 *
 * @author mperal01
 */
public class MenuItem {
    private String nombre;
    private String activo;
    private List<MenuItem> listaItem;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public void setListaItem(List<MenuItem> listaItem) {
        this.listaItem = listaItem;
    }
    public void addItem(MenuItem item){
        this.listaItem.add(item);
    }
    
}
