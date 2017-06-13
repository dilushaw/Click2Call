/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

public class UserData {
    String username;
    String email;
    int age;
    int num1;
    int num2;

    public void setUsername( String value )
    {
        username = value;
    }

    public void setEmail( String value )
    {
        email = value;
    }

    public void setAge( int value )
    {
        age = value;
    }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public int getAge() { return age; }
    
    public String callGeneration(){
        String result="";
       result= new CallGenerate().generateCall(getNum1(),getNum2());
        
        return result;
    
    }

    /**
     * @return the num1
     */
    public int getNum1() {
        return num1;
    }

    /**
     * @param num1 the num1 to set
     */
    public void setNum1(int num1) {
        this.num1 = num1;
    }

    /**
     * @return the num2
     */
    public int getNum2() {
        return num2;
    }

    /**
     * @param num2 the num2 to set
     */
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    

}
