package iShare.dao;

import java.util.List;

import iShare.model.Discovery;

public interface DiscoveryDAO extends GenericDAO<Discovery,Long> {
	List<Discovery> getAll();
}
