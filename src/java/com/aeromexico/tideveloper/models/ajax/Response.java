/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.models.ajax;

import java.util.List;

/**
 *
 * @author Elida Carrillo
 */
public class Response {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<?> data;   

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" + "draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered=" + recordsFiltered + ", data=" + data + '}';
    }

   
    
}
