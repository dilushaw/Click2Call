package user;


import java.net.URL;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
import axita.dialog.telecommunication.ivr.obd.IVROBDManager;
public class CallGenerate {
    
    public String generateCall(int num1,int num2){
        String  str="";
       try{ 
        str= (new IVROBDManager()).createCall(
num1,
num2,
new URL("http://10.62.229.135:8080/newsivr/lab/obd.jsp"),
"",
1,
"PT30M",
"PT30M",
"PT30M",
"PT30M",
new URL("http://10.48.248.167:8080/newsivr/report.jsp"),
"SampathBank",
new URL("http://10.48.248.102:5000/callout/test.do"),
true);
       }
       catch(Exception error){
           str=error.getMessage();
       }
       
    return str;  
    }
    
}
