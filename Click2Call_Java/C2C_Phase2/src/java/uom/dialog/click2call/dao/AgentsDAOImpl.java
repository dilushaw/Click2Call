/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uom.dialog.click2call.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import uom.dialog.click2call.data.Agents;
import uom.dialog.click2call.utils.HibernateUtil;

/**
 *
 * @author Hasala ©Dialog - University of Moratuwa Mobile Communications
 * Research Laboratory
 */
@Component
public class AgentsDAOImpl extends GenericDAOImpl<Agents, String> implements AgentsDAO {

    public void saveAgent(Agents agent) {

        save(agent);
        System.out.println("Saved Agent Successfully");
    }

    public void deleteAgent(Agents agent) {
        delete(agent);
        System.out.println("Agent Deleted");
    }

    public void updateAgent(Agents agent) {

        update(agent);
        System.out.println("Agent Updated");
    }

    public Agents findAgentByCompanyId(Integer companyId) {
        Agents agent = null;
        String hql = "From Agents a where a.companyId=:id";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("id", companyId);
        agent = findOne(query);
        return agent;
    }

    public List<Agents> findAgentsByCompanyId(Integer companyId) {
        List<Agents> agents = null;
        String hql = "From Agents a where a.company.companyId=:id";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("id", companyId);
        agents = findMany(query);
        return agents;
    }

    public int findAgentNumberByAgentKey(String agentKey) {
        Agents agent;
        String hql = "From Agents a where a.agentKey=:key";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("key", agentKey);
        agent = findOne(query);
        int number = Integer.parseInt(agent.getNumber());
        return number;
    }

    public Agents findAgentByAgentId(Integer agentId) {

        Agents agent = null;
        String hql = "From Agents a where a.agentId=" + agentId;
        Query query = HibernateUtil.getSession().createQuery(hql);
        agent = findOne(query);
        return agent;
    }

    /**
     * As Agent's phone number is unique within entire system, method will give
     * agent entity matching with the given phone number; returns null if no
     * matching found
     *
     * @param Number - Agent phone number
     * @return Agent entity matching the given phone number
     */
    public Agents findAgentByNumber(String number) {
        Agents agent = null;
        String hql = "From Agents a where a.number=:number";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("number", number);
        agent = findOne(query);
        return agent;
    }

    public Agents findAgentByNameAndCompanyId(int companyId, String agentName) {
        Agents agent = null;
        String hql = "From Agents a where a.name=:name and a.company.companyId=:companyId";
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("name", agentName).setParameter("companyId", companyId);
        agent = findOne(query);
        return agent;
    }
}
