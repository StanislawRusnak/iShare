package iShare.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import iShare.dao.DAOFactory;
import iShare.dao.DiscoveryDAO;
import iShare.model.Discovery;
import iShare.model.User;

public class DiscoveryService {
	public void addDiscovery(String name, String descr,String url, User user) {
		Discovery discovery = createDiscoveryObject(name, descr, url, user);
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
		discoveryDAO.create(discovery);
	}
	private Discovery createDiscoveryObject(String name, String descr,String url, User user) {
		Discovery disc = new Discovery();
		disc.setName(name);
		disc.setDescription(descr);
		disc.setUrl(url);
		User userCopy = new User(user);
		disc.setUser(userCopy);
		disc.setTimestamp(new Timestamp(new Date().getTime()));
		return disc;
		
	}
	public List<Discovery> getAllDiscoveries(){
		return getAllDiscoveries(null);
	}
	public List<Discovery> getAllDiscoveries(Comparator<Discovery> comparator) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
		List<Discovery> discoveryList = discoveryDAO.getAll();
		if(comparator != null && discoveryList != null) {
			discoveryList.sort(comparator);
		}
		return discoveryList;
	}
	public boolean updateDiscovery(Discovery discovery) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discDAO = factory.getDiscoveryDAO();
		boolean result = discDAO.updade(discovery);
		return result;
	}
	public Discovery getDiscoveryById(long discovery_id) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		DiscoveryDAO discoveryDAO = factory.getDiscoveryDAO();
		Discovery disc = discoveryDAO.read(discovery_id);
		return disc;
		
	}
}
