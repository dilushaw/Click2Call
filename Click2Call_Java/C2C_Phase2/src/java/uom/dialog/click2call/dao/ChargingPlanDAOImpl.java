/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class ChargingPlanDAOImpl extends GenericDAOImpl<ChargingPlan, Integer> implements ChargingPlanDAO {
        
    public List<ChargingPlan> findAllChargingPlans(){
        List<ChargingPlan> plan;
        //String hql = "From ChargingPlan c where c.planId != :id";
        //Query query = HibernateUtil.getSession().createQuery(hql).setParameter("id", 0);
        //plan = findMany(query);
        plan = findAll(ChargingPlan.class);
        return plan;
    }

    public ChargingPlan findPlanByName(String planName) {
ChargingPlan plan;
String hql = "From ChargingPlan c where c.name = :givenName";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("givenName", planName);
        plan = findOne(query);
        return plan;
    }
}

