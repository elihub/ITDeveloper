package com.aeromexico.tideveloper.util;

import com.aeromexico.tideveloper.dao.AreaDAO;
import com.aeromexico.tideveloper.models.Area;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author mperal01
 */
@Component
public class AreaFormater implements Formatter<Area>{
    
    @Autowired
    private AreaDAO areaDAO;
    
    @Override
    public String print(Area t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Area parse(String string, Locale locale) throws ParseException {
        Area area = areaDAO.findById(Integer.parseInt(string));
        return area;
    }
    
}
