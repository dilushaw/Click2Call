
package logic.login;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
//import lk.dialog.corporate.Qr.accesscontrol.ActionPriviledges;
//import lk.dialog.corporate.Qr.dataaccess.LogDetails_Dao;
//import lk.dialog.corporate.Qr.dataaccess.ValidateUser;
//import logs.Log;
/**
 *
 * @author Shamali
 */
public class Login {

private String uName;
private String pwd;

 /*   public void setUserName(String userName){

        uName= userName;

       
    }
    public void setPassword(String password){
    pwd=password;
    }

    public String getHostIP(){

    String server_ip="";
    try{
    File url_file = new File("qrServer.xml");
                    FileInputStream fileInput = new FileInputStream(url_file);
                    Properties properties = new Properties();
                    properties.loadFromXML(fileInput);
                    server_ip=properties.getProperty("server_url");

        }

    catch(Exception e){}

    return server_ip;


    }*/
    public String validateUser(String userName,String userPassword,String company){

       String result;
       
       
        if ((userName.equals(""))||(userPassword.equals(""))||(company.equals(""))) {
           result="-2";
       }

 else {
           result="1";
 }
 //-------------------- Anuradha's new method

      /*     ValidateUser validation=new ValidateUser();
           if(validation.isValidUser(userName, userPassword)){
                LogDetails_Dao userAccess=new LogDetails_Dao();
        String userAccessLevel=userAccess.getRoleType(userName);

        Log login=new Log();
            login.writeLog(ipAddress,userName,userAccessLevel,"INFO","User Logged Successfully");
               result=userAccessLevel;


           }
 else{
               Log log=new Log();
               log.writeLog(ipAddress, userName,"default",  "INFO", "User validation failed");
               result="-1";

 }*/
 //}


        return result;
    }
   /* public String sendHello(){

         String str1="Hello";

         return str1;

    }

    public String checkUserPrivileges(String userName){

    StringBuffer buffer=new StringBuffer();
ActionPriviledges privileges=new ActionPriviledges(userName);


if(privileges.is_ImageType_Avaliable()){ buffer.append("1");}
 else{ buffer.append("0");}
buffer.append(",");
if(privileges.is_errorCorrection_Avaliable()){ buffer.append("1");}
 else{ buffer.append("0");}
buffer.append(",");
if(privileges.is_ImageSize_Avaliable()){ buffer.append("1");}
 else{ buffer.append("0");}
buffer.append(",");
if(privileges.is_BulkBarcodes_Avaliable()){ buffer.append("1");}
 else{ buffer.append("0");}
buffer.append(",");
if(privileges.is_AsZip_Avaliable()){ buffer.append("1");}
 else{ buffer.append("0");}



    return buffer.toString();

    }
*/

}
