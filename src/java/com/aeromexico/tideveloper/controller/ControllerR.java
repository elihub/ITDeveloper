/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Elida Carrillo
 */
@Controller
public class ControllerR {
    @RequestMapping(method= RequestMethod.GET,value="/login")
	public void home(){
		System.out.println("LoginLogoutController.java");
		
	}
        @RequestMapping(method= RequestMethod.GET,value="/bootstrap.jsp")
	public String bootStrap(){
		System.out.println("estamos en el metodo bootstrap");
		return "bootstrap";
	}
    
}
