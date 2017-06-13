/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.Date;
import java.util.List;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.AgentDTO;
import uom.dialog.click2call.exception.Click2CallException;

/**
 *
 * @author Hasala
 * ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
public interface AgentManager {
    void saveAgent(Agents agent) throws Click2CallException;
    void deleteAgent(Agents agent) throws Click2CallException;
    void updateAgent (AgentDTO editAgent,Users user) throws Click2CallException;
    void updateAgent (Agents agent) throws Click2CallException;
    double calculateUsedmins (Integer agentId,Date startTime,String endTime) throws Click2CallException;
    public void updateAgentusedmins (Integer agentId,int duration) throws Click2CallException;
    public Agents findAgentByID(Number id) throws Click2CallException;
    public List<Agents> findAgentsByCompanyId (Integer id) throws Click2CallException;
    public int findAgentNumberByAgentKey(String agentKey) throws Click2CallException;
    public Agents findAgentByAgentId(Integer agentId) throws Click2CallException;
    public Agents findAgentByNumber(String number) throws Click2CallException;//aded by Dewmini

    public Agents findAgentByNameAndCompanyId(int companyId, String agentName) throws Click2CallException;//aded by Dewmini
}
