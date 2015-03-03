/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.models;

import java.util.List;

/**
 *
 * @author Elida Carrillo
 */
public class Responce {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<Api> data;   

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<Api> getData() {
        return data;
    }

    public void setData(List<Api> data) {
        this.data = data;
    }

   
    
}
