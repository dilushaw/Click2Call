/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface ChargingPlanDAO extends GenericDAO<ChargingPlan, Integer> {
     public List<ChargingPlan> findAllChargingPlans();

    ChargingPlan findPlanByName(String planName);
}
