/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.List;
import uom.dialog.click2call.data.ChargingPlan;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface ChargingPlanManager {
     List<ChargingPlan> findAllChargingPlans() throws Click2CallException;
     ChargingPlan findChargingPlanById(Integer planId)throws Click2CallException;
     void saveChargingPlan(ChargingPlan cPlan) throws Click2CallException;

    ChargingPlan findPlanByName(String planName) throws Click2CallException;
    void updateChargingPlan(ChargingPlan cPlan) throws Click2CallException;
}
