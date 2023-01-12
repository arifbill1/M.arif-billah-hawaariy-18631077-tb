/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author arif billah
 */
public class DateHelper {
        
    public static Date stringToDate (String dateString) throws ParseException {
        String pattern = "yyyy-MM-dd";
        Date thedate = new SimpleDateFormat(pattern).parse(dateString);
        return thedate;
    }
    
    public static String dateToString (Date date){
        String pattern = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(pattern);  
        return dateFormat.format(date);  
    }
    
}
