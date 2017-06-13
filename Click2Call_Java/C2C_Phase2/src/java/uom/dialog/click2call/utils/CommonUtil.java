/**
 * Copyright(c) 2012 Dialog-University of Moratuwa Mobile Communications
 * Research Laboratory. All Rights Reserved. This software is the proprietary
 * information of Dialog-University of Moratuwa Mobile Communications Research
 * Laboratory(Dialog-UOM Lab).
 *
 * Dialog-UOM Lab or Dialog Axiata PLC reserves to right to modify, update
 * and/or enhance the software as it sees fit.
 *
 */
package uom.dialog.click2call.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CommonUtil.java (UTF-8) This class is to provide Common utility function that
 * may be used in several other functions. Apr 3, 2013, 1:23:52 PM
 *
 * @author Dewmini
 */
public class CommonUtil {

    /**
     * When Date is in the form of 2013-04-03T07:33:39+00:00 and as a String
     * this method will convert it to the Date with the format of yyyy-MM-dd
     * HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static Date getFormattedDate(String dateTime) {
        Date tmpdate = null;
        try {
            System.out.println("startTime: " + dateTime);
            
            SimpleDateFormat formater1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            formater1.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = formater1.parse(dateTime);

            SimpleDateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            String finalFormat = formater2.format(date);
System.out.println("finalFormat: " + finalFormat);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse(finalFormat);
            String formattedTime = output.format(d);
            System.out.println("formattedTime: " + formattedTime);



            
            Date stdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(formattedTime);
            return stdate;
        } catch (ParseException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmpdate;
    }
}
