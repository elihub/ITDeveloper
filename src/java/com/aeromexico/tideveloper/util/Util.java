/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeromexico.tideveloper.util;

import com.aeromexico.tideveloper.models.ApisVersionesResources;
import com.aeromexico.tideveloper.security.CustomUser;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

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
    
    public static void editResources(List<ApisVersionesResources> apisVersionesResources, int idApi){
        ResourceBundle properties = ResourceBundle.getBundle("TIDeveloper");
        String rootPath = properties.getString("RootPath");
        String nameFolder = idApi + "";
        
        for(ApisVersionesResources versionResource: apisVersionesResources){
            if(versionResource.getDirResource() == null || versionResource.getDirResource().equals("")){
                for (int i = 0; i < versionResource.getFiles().length; i++) {
                    MultipartFile file = versionResource.getFiles()[i];
                    if (!file.isEmpty()) {
                        System.out.println("name file:" + file.getOriginalFilename());
                        String extencionFile = "." + FilenameUtils.getExtension(file.getOriginalFilename());
                        String name = versionResource.getNombreResource() + extencionFile;
                        try {
                            byte[] bytes = file.getBytes();

                            // Creating the directory to store file                     
                            File dir = new File(rootPath + nameFolder + "\\resources");
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }

                            // Create the file on server
                            String pathFile = dir.getAbsolutePath() + File.separator + name;
                            File serverFile = new File(pathFile);
                            BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                            stream.write(bytes);
                            stream.close();

                            System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                            //message = message + "You successfully uploaded file=" + name + extencionFile + "<br />";
                            versionResource.setDirResource(serverFile.getAbsolutePath());

                            //System.out.println(message);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                           //return "You failed to upload " + name + " => " + e.getMessage();
                        }
                    }
                }
            }else if(versionResource.getFiles() != null && versionResource.getFiles().length > 0){
                for (int i = 0; i < versionResource.getFiles().length; i++) {
                    MultipartFile file = versionResource.getFiles()[i];
                    if (!file.isEmpty()) {
                        System.out.println("name file:" + file.getOriginalFilename());
                        String extencionFile = "." + FilenameUtils.getExtension(file.getOriginalFilename());
                        String name = versionResource.getNombreResource() + extencionFile;
                        try {
                            byte[] bytes = file.getBytes();

                            // Creating the directory to store file                     
                            File dir = new File(rootPath + nameFolder + "\\resources");
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }

                            // Create the file on server
                            String pathFile = dir.getAbsolutePath() + File.separator + name;
                            File serverFile = new File(pathFile);
                            BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                            stream.write(bytes);
                            stream.close();

                            System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                            //message = message + "You successfully uploaded file=" + name + extencionFile + "<br />";
                            versionResource.setDirResource(serverFile.getAbsolutePath());

                            //System.out.println(message);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                           //return "You failed to upload " + name + " => " + e.getMessage();
                        }
                    }
                }
            }
        }
    }
    
}
