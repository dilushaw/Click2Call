package uom.dialog.click2call.data;
// Generated Apr 8, 2013 10:35:36 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * AuthModel generated by hbm2java
 */
@Component
public class AuthModel  implements java.io.Serializable {


     private Integer authId;
     private String name;
     private Set companies = new HashSet(0);

    public AuthModel() {
    }

	
    public AuthModel(String name) {
        this.name = name;
    }
    public AuthModel(String name, Set companies) {
       this.name = name;
       this.companies = companies;
    }
   
    public Integer getAuthId() {
        return this.authId;
    }
    
    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getCompanies() {
        return this.companies;
    }
    
    public void setCompanies(Set companies) {
        this.companies = companies;
    }




}


