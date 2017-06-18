package Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ania
 */
public class Data {

    public static String dzisiejszaData(){
        SimpleDateFormat simpleDateHere = new SimpleDateFormat("yyyy-MM-dd");
        return  simpleDateHere.format(new Date());
    };
    
    public static Date toSqlDate(String strDate){
    DateFormat dateFrm = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date myDate = new java.util.Date();
    java.sql.Date sqlDate;

    try
    {
      myDate = dateFrm.parse(strDate);
      sqlDate = new java.sql.Date(myDate.getTime());
    }
    catch (Exception e)
    {
      sqlDate = null;
    }

    return (sqlDate);
}

}
