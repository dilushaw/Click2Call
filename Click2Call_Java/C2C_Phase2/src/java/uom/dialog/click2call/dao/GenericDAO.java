/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

/**
 *
 * @author Dewmini
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.Query;
 
public interface GenericDAO<T, ID extends Serializable> {
 
    public void save(T entity);
 
    public void update(T entity);
 
    public void delete(T entity);
 
    public List<T> findMany(Query query);
 
    public T findOne(Query query);
 
    public List findAll(Class clazz);
 
    public T findByID(Class clazz, Number id);
}