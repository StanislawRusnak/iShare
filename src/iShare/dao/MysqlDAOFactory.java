package iShare.dao;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public DiscoveryDAO getDiscoveryDAO() {
		return new DiscoveryDAOImpl();
	}

	@Override
	public VoteDAO getVoteDAO() {
		return new VoteDAOImpl();
	}

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

}
