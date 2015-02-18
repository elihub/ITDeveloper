package com.aeromexico.tideveloper.dao;

import java.util.List;

import com.aeromexico.tideveloper.models.Usuarios;

public interface UsuariosDAO {
    String validateUsername(String username);
    Usuarios findByID(Long idUsuario);
    Usuarios find(Usuarios u);
    List<Usuarios> findAll();
    void updateUsuarios(Usuarios u);
    Long saveUsuario(Usuarios u);
    void deleteUsuario(Usuarios u);
    void savePermisos(Long idUsuario,String idRol);
    void deletePermisos(Long idUsuario,String idRol);
    List<String> findPermisos(Long idUsuario);
}
