/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.manager;

import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.hibernate.HibernateException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.dao.AgentsDAO;
import uom.dialog.click2call.dao.AgentsDAOImpl;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.data.Users;
import uom.dialog.click2call.dto.AgentDTO;
import uom.dialog.click2call.exception.Click2CallException;
import uom.dialog.click2call.translator.FormToDomainTranslator;
import uom.dialog.click2call.utils.CommonUtil;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications Research Laboratory
 */
@Component
public class AgentManagerImpl implements AgentManager {

    @Autowired
    private AgentsDAO agentsDAO;
    @Autowired
    private FormToDomainTranslator formToDomainTranslator;
    @Autowired
    SystemHistoryManager systemHistoryManager;

    public void saveAgent(Agents agent) throws Click2CallException {
        try {

            HibernateUtil.beginTransaction();
            agentsDAO.saveAgent(agent);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public void deleteAgent(Agents agent) throws Click2CallException {
        try {

            HibernateUtil.beginTransaction();
            agentsDAO.delete(agent);

            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public void updateAgent(AgentDTO editAgent, Users user) throws Click2CallException {
        try {
            Agents agent = this.findAgentByAgentId(editAgent.getAgentId());
            String newNumber = editAgent.getAgentNumber();
            String oldNumber = agent.getNumber();
            String agentId = agent.getAgentId().toString();

            if (!newNumber.equalsIgnoreCase(oldNumber)) {
                systemHistoryManager.saveToSystemHistory("agents", "number", agentId, newNumber, oldNumber, user);
            }

            agent.setName(editAgent.getAgentName());
            agent.setNumber(editAgent.getAgentNumber());
            agent.setTotalMins(editAgent.getTotalMinutes());
            agent.setStatus(editAgent.getAgentStatus());
            String key = formToDomainTranslator.generateAgentKey() + editAgent.getAgentNumber().substring(6) + formToDomainTranslator.generateAgentKey();
            agent.setAgentKey(key);
            agent.setIframe("http://www.dialog.lk/click2call/" + key);
            HibernateUtil.beginTransaction();
            agentsDAO.updateAgent(agent);

            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            ex.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();


        }
    }

    public double calculateUsedmins(Integer agentId, Date startTime, String endTime) throws Click2CallException {
        Date endtime = (CommonUtil.getFormattedDate(endTime));
        long durationmilSecs = endtime.getTime() - startTime.getTime();
        System.out.print(durationmilSecs);
        double duration = durationmilSecs / (1000 * 60);
        System.out.print("duration" + duration);
        return duration;
    }

    public void updateAgentusedmins(Integer agentId, int duration) throws Click2CallException {

        try {
            Agents agent = this.findAgentByAgentId(agentId);

            int prevUsedSec = agent.getUsedSeconds();
            agent.setUsedSeconds(prevUsedSec + duration);
            //agent.setAgentId(editAgent.getAgentId());
            System.out.println("agent class" + prevUsedSec);
            HibernateUtil.beginTransaction();
            agentsDAO.updateAgent(agent);

            HibernateUtil.commitTransaction();

        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }

    }

    public void updateAgent(Agents agent) throws Click2CallException {
        try {
            HibernateUtil.beginTransaction();
            agentsDAO.update(agent);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
    }

    public Agents findAgentByID(Number id) throws Click2CallException {
        Agents agent = null;
        try {
            AgentsDAO usersDAO = new AgentsDAOImpl();
            agent = usersDAO.findByID(Agents.class, id);
        } catch (NonUniqueResultException ex) {
            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {
            throw new Click2CallException("Database exception occured", ex);
        }
        return agent;
    }

    public List<Agents> findAgentsByCompanyId(Integer id) throws Click2CallException {
        List<Agents> agents = null;
        try {
            //AgentsDAO agentsDAO = new AgentsDAOImpl();
            agents = agentsDAO.findAgentsByCompanyId(id);
        } catch (HibernateException ex) {
            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {
            throw new Click2CallException("Exception occured", ex);
        }
        return agents;
    }

    public int findAgentNumberByAgentKey(String agentKey) throws Click2CallException {
        int number;
        try {
            number = agentsDAO.findAgentNumberByAgentKey(agentKey);
        } catch (NonUniqueResultException ex) {

            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
        return number;
    }

    public Agents findAgentByAgentId(Integer agentId) throws Click2CallException {

        Agents agent = null;
        try {
            agent = agentsDAO.findAgentByAgentId(agentId);
        } catch (NonUniqueResultException ex) {

            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
        return agent;
    }

    /**
     * When agent phone number is given return the mathing agent object. As phone number is unique
     * within entire system this should return only on specific agent object, added by Dewmini
     *
     * @param number - agent number
     * @return
     * @throws Click2CallException
     */
    public Agents findAgentByNumber(String number) throws Click2CallException {
        Agents agent = null;
        try {

            agent = agentsDAO.findAgentByNumber(number);

        } catch (NonUniqueResultException ex) {

            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
        return agent;
    }

    public Agents findAgentByNameAndCompanyId(int companyId, String agentName) throws Click2CallException {
        Agents agent = null;
        try {

            agent = agentsDAO.findAgentByNameAndCompanyId(companyId, agentName);

        } catch (NonUniqueResultException ex) {

            throw new Click2CallException("More result found", ex);
        } catch (HibernateException ex) {

            throw new Click2CallException("Hibernate exception occured", ex);
        } catch (Exception ex) {

            throw new Click2CallException("Exception occured", ex);
        }
        return agent;
    }
}
