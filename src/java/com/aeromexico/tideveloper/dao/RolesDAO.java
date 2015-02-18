package com.aeromexico.tideveloper.dao;

import com.aeromexico.tideveloper.models.Roles;
import java.util.List;

/**
 *
 * @author mperal01
 */
public interface RolesDAO {
    List<Roles> findAll();
}
