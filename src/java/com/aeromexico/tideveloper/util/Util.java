/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.util;

import com.aeromexico.tideveloper.security.CustomUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Elida Carrillo
 */
public class Util {
    public static void getIdUserLogged(HttpServletRequest request){
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer idUsuario = Long.valueOf(user.getId()).intValue();        
        request.setAttribute("idUsuario", idUsuario);
    }
    
}
