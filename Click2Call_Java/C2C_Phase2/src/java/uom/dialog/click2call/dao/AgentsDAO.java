/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Agents;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface AgentsDAO extends GenericDAO<Agents,String> {
    
    public void saveAgent(Agents agent); // Saves Company Agent
    public void deleteAgent (Agents agent);
    public void updateAgent(Agents agent);
    public Agents findAgentByCompanyId(Integer CompanyId);
    public List<Agents> findAgentsByCompanyId(Integer companyId);
    public int findAgentNumberByAgentKey(String agentKey);
    public Agents findAgentByAgentId(Integer agentId);
    Agents findAgentByNumber(String number);

    public Agents findAgentByNameAndCompanyId(int companyId, String agentName);
    
}
