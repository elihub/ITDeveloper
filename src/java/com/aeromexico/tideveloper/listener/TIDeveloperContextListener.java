/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.listener;

import java.io.File;
import java.util.ResourceBundle;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Elida Carrillo
 */
public class TIDeveloperContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ResourceBundle rb = ResourceBundle.getBundle("TIDeveloper");
        String apisPath = rb.getString("ApisPath");
        String soapPath = rb.getString("SoapPath");
        File dir = new File(apisPath);
        if (!dir.exists()) {
            System.out.println("Creando Carpeta: " + apisPath);
            dir.mkdirs();
        }
        dir = new File(soapPath);
        if (!dir.exists()) {
            System.out.println("Creando Carpeta: " + soapPath);
            dir.mkdirs();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
