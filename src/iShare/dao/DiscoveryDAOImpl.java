package iShare.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import iShare.model.Discovery;
import iShare.model.User;
import iShare.util.ConnectionProvider;

public class DiscoveryDAOImpl implements DiscoveryDAO{
	private static final String CREATE_DISCOVERY = "INSERT INTO discovery(name,description,url,date,up_vote,down_vote,user_id)"
												   + "VALUES(:name,:description,:url,:date,:up_vote,:down_vote,:user_id)";
	
	private static final String READ_ALL_DISCOVERIES = "SELECT user.user_id, username, email, is_active, password, discovery_id, name, description, url, date, up_vote, down_vote "
			  + "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id;";
	
	private static final String UPDATE_DISCOVERY ="UPDATE discovery SET name=:name, description=:description, url=:url, date=:date, "
			+ "up_vote=:up_vote, down_vote=:down_vote, user_id=:user_id WHERE discovery_id=:discovery_id";
	
	private static final String READ_DISCOVERY = "SELECT user.user_id, username, email, is_active, password, discovery_id, name, description, url, date, up_vote, down_vote "
			+ "FROM discovery LEFT JOIN user ON discovery.user_id=user.user_id WHERE discovery_id=:discovery_id;";
	
	NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	
	@Override
	public Discovery create(Discovery discovery) {
		Discovery resultDisc = new Discovery(discovery);
		KeyHolder holder = new GeneratedKeyHolder();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("name", discovery.getName());
		paramMap.put("description", discovery.getDescription());
		paramMap.put("url", discovery.getUrl());
		paramMap.put("date", discovery.getTimestamp());
		paramMap.put("up_vote", discovery.getUpVote());
		paramMap.put("down_vote", discovery.getDownVote());
		paramMap.put("user_id", discovery.getUser().getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int update = template.update(CREATE_DISCOVERY, paramSource, holder);
		if(update>0) {
			try {
			resultDisc.setId(holder.getKey().longValue());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return resultDisc;
	}

	@Override
	public Discovery read(Long discovery_id) {
		SqlParameterSource paramSource = new MapSqlParameterSource("discovery_id", discovery_id);
		Discovery discovery = template.queryForObject(READ_DISCOVERY, paramSource, new DiscoveryRowMapper());
		return discovery;
	}

	@Override
	public boolean updade(Discovery discovery) {
		boolean result = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("discovery_id", discovery.getId());
		paramMap.put("name", discovery.getName());
		paramMap.put("description", discovery.getDescription());
		paramMap.put("url", discovery.getUrl());
		paramMap.put("date", discovery.getTimestamp());
		paramMap.put("up_vote", discovery.getUpVote());
		paramMap.put("down_vote", discovery.getDownVote());
		paramMap.put("user_id", discovery.getUser().getId());		
		SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
		int rowsAffected = template.update(UPDATE_DISCOVERY, paramSource);
		if(rowsAffected>0) {
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
	public List<Discovery> getAll() {
		List<Discovery> allDisc = null;
		allDisc = template.query(READ_ALL_DISCOVERIES, new DiscoveryRowMapper());
		return allDisc;
	}
	
	private class DiscoveryRowMapper implements RowMapper<Discovery>{

		@Override
		public Discovery mapRow(ResultSet resultSet, int row) throws SQLException {
			Discovery discovery = new Discovery();
			discovery.setId(resultSet.getLong("discovery_id"));
			discovery.setName(resultSet.getString("name"));
			discovery.setDescription(resultSet.getString("description"));
			discovery.setUrl(resultSet.getString("url"));
			discovery.setUpVote(resultSet.getInt("up_vote"));
			discovery.setDownVote(resultSet.getInt("down_vote"));
			discovery.setTimestamp(resultSet.getTimestamp("date"));
			User user = new User();
			user.setId(resultSet.getLong("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setEmail(resultSet.getString("email"));
			user.setPassword(resultSet.getString("password"));
			discovery.setUser(user);
			return discovery;
		}
	}
}
