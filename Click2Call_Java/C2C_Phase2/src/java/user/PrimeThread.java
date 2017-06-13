/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author HP
 */
 public class PrimeThread extends Thread {
        String[] val=new String[1];
        int val1,val2;
        String str1;
         public PrimeThread(String str) {
             str1=str;
          //   val=str.split("|".toString());
           //  val1=Integer.parseInt(val[0]);
           //  val2=Integer.parseInt(val[1]);
         }
 
         public void run() {
              val=str1.split(":");
            val1=Integer.parseInt(val[0]);
             val2=Integer.parseInt(val[1]);
          //   val1=Integer.parseInt(str1.substring(0,9));
           //  val2=Integer.parseInt(str1.substring(10,17));
             new CallGenerate().generateCall( val1,val2);
         //    new CallGenerate().generateCall( val1,val2);
         }
     }