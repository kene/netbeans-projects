/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.unadm.mexican.food.restaurant.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

/**
 * Clase que contiene metodos utileria para las demas clases y servicios
 * @author Josue Palemon Perez
 */
@Service
public class ServiceUtil {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate hex with regular expression
     * 
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

}
