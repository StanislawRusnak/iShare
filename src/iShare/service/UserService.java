package iShare.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import iShare.dao.DAOFactory;
import iShare.dao.UserDAO;
import iShare.model.User;

public class UserService {
	public void addUser(String name, String email, String password) {
		User user = new User();
		user.setUsername(name);
		user.setEmail(email);
		String md5Pass = encryptPassword(password);
		user.setPassword(md5Pass);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO= factory.getUserDAO();
		userDAO.create(user);
	}
	private String encryptPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(password.getBytes());
		String md5Pass = new BigInteger(1,digest.digest()).toString(16);
		return md5Pass;
	}
	public User getUserByID(long userId) {
		User user = null;
		DAOFactory factory = DAOFactory.getDAOFactory();
		user = factory.getUserDAO().read(userId);
		return user;
	}
	public User getUserByUsername(String username) {
		User user = null;
		DAOFactory factory = DAOFactory.getDAOFactory();
		user = factory.getUserDAO().getUserByUsername(username);
		return user;
	}
}
