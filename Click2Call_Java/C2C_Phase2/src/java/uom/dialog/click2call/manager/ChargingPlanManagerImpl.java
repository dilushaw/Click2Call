/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.ChargingPlanDAO;
import uom.dialog.click2call.dao.ChargingPlanDAOImpl;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class ChargingPlanManagerImpl implements ChargingPlanManager {

    @Autowired
    ChargingPlanDAO chargingPlanDAO;

    public List<ChargingPlan> findAllChargingPlans() throws Click2CallException {

        List<ChargingPlan> plans = null;

        try {
            
            plans = chargingPlanDAO.findAllChargingPlans();
            

        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            ex.printStackTrace();
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }

        return plans;

    }

    public ChargingPlan findChargingPlanById(Integer planId) throws Click2CallException {
        ChargingPlan plan = null;

        try {
            
            plan = chargingPlanDAO.findByID(ChargingPlan.class, planId);

            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }
        return plan;
    }

    public void saveChargingPlan(ChargingPlan cPlan) throws Click2CallException {
        try {
            HibernateUtil.beginTransaction();
            chargingPlanDAO.save(cPlan);

            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }
    }
    
    public void updateChargingPlan(ChargingPlan cPlan) throws Click2CallException {
        try {
            HibernateUtil.beginTransaction();
            chargingPlanDAO.update(cPlan);

            HibernateUtil.commitTransaction();
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public ChargingPlan findPlanByName(String planName) throws Click2CallException {
ChargingPlan plan = null;

        try {
            
            plan = chargingPlanDAO.findPlanByName(planName);
            
        } catch (NonUniqueResultException ex) {
            
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            
            throw new Click2CallException("Exception occured", ex);
        }
        return plan;
    }
}
