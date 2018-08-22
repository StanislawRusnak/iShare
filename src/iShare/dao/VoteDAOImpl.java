package iShare.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import iShare.model.Vote;
import iShare.model.VoteType;
import iShare.util.ConnectionProvider;

public class VoteDAOImpl implements VoteDAO{
	private static final String CREATE_VOTE ="INSERT INTO vote(user_id,discovery_id, date, type) VALUES (:user_id,:discovery_id,:date,:type);";
	private static final String READ_VOTE ="SELECT user_id, discovery_id, vote_id, date, type FROM vote WHERE vote_id = :vote_id;";
	private static final String UPDATE_VOTE ="UPDATE vote SET date=:date, type=:type WHERE vote_id=:vote_id";
	private static final String READ_VOTE_BY_USER_DISC_ID ="SELECT user_id, discovery_id, vote_id, date, type FROM vote WHERE user_id=:user_id AND discovery_id=:discovery_id;";
	
	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	
	@Override
	public Vote create(Vote vote) {
		Vote copyVote = new Vote(vote);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", copyVote.getUserId());
		paramMap.put("discovery_id", copyVote.getDiscoveryId());
		paramMap.put("date", copyVote.getDate());
		paramMap.put("type", copyVote.getVoteType().toString());
		KeyHolder key = new GeneratedKeyHolder();
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int rowsAffected = template.update(CREATE_VOTE, paramSource,key);
		if(rowsAffected>0) {
			try {
				try {
					copyVote.setId(key.getKey().longValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (InvalidDataAccessApiUsageException e) {
				e.printStackTrace();
			}
		}
		return copyVote;
	}

	@Override
	public Vote read(Long primaryKey) {
		SqlParameterSource paramSource = new MapSqlParameterSource("vote_id",primaryKey);
		Vote vote = template.queryForObject(READ_VOTE, paramSource, new VoteRowMapper());
		return vote;
	}

	@Override
	public boolean updade(Vote vote) {
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("vote_id",vote.getId());
		paramMap.put("type", vote.getVoteType().toString());
		paramMap.put("date", vote.getDate());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(UPDATE_VOTE, paramSource);
		if(update>0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Vote> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("user_id", userId);
		paramMap.put("discovery_id", discoveryId);
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		Vote vote = null;
		try {
			vote = template.queryForObject(READ_VOTE_BY_USER_DISC_ID, paramSource, new VoteRowMapper());
		} catch (EmptyResultDataAccessException e) {
			// vote not found
		}
		return vote;
	}
	private class VoteRowMapper implements RowMapper<Vote>{

		@Override
		public Vote mapRow(ResultSet resSet, int arg1) throws SQLException {
			Vote vote = new Vote();
			vote.setDate(resSet.getTimestamp("date"));
			vote.setDiscoveryId(resSet.getLong("discovery_id"));
			vote.setId(resSet.getLong("vote_id"));
			vote.setUserId(resSet.getLong("user_id"));
			vote.setVoteType(VoteType.valueOf(resSet.getString("type")));
			return vote;
		}
		
	}
}
