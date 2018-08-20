package iShare.dao;

import java.util.List;

import iShare.model.User;

public interface UserDAO extends GenericDAO<User,Long>{
	List<User> getAll();
	User getUserByUsername(String username);
}
