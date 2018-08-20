package iShare.service;

import java.sql.Timestamp;
import java.util.Date;

import iShare.dao.DAOFactory;
import iShare.dao.VoteDAO;
import iShare.model.Vote;
import iShare.model.VoteType;

public class VoteService {
	private Vote addVote(VoteType voteType, long discoveryId, long userId) {
		Vote vote = new Vote();
		vote.setDate(new Timestamp(new Date().getTime()));
		vote.setDiscoveryId(discoveryId);
		vote.setUserId(userId);
		vote.setVoteType(voteType);
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		vote = voteDAO.create(vote);  //overriding vote
		return vote;	
	}
	private Vote updateVote(VoteType voteType, long discoveryId, long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdDiscoveryId(userId, discoveryId);
		if(vote != null) {
			vote.setVoteType(voteType);
			voteDAO.updade(vote);
		}
		return vote;
	}
	public Vote getVoteByDiscoveryUserId(long discoveryId, long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdDiscoveryId(userId, discoveryId);
		return vote;
	}
	public Vote addOrUpdateVote(VoteType voteType, long discoveryId, long userId) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdDiscoveryId(userId, discoveryId);
		Vote resultVote = null;
		if(vote == null) {
			resultVote = addVote(voteType, discoveryId, userId);
		}else {
			resultVote = updateVote(voteType, discoveryId, userId);
		}
		return resultVote;
	}
}
