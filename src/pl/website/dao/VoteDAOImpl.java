package pl.website.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.website.model.Vote;
import pl.website.model.VoteType;
import pl.website.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VoteDAOImpl implements VoteDAO {

    private static final String CREATE_VOTE =
            "INSERT INTO vote(entry_id, user_id, date, type) VALUES(:entry_id, :user_id, :date, :type);";
    private static final String READ_VOTE =
            "SELECT vote_id, entry_id, user_id, date, type FROM vote WHERE vote_id = :vote_id;";
    private static final String READ_VOTE_BY_ENTRY_USER_ID =
            "SELECT vote_id, entry_id, user_id, date, type FROM vote WHERE user_id = :user_id AND entry_id = :entry_id;";
    private static final String UPDATE_VOTE =
            "UPDATE vote SET date = :date, type = :type WHERE vote_id = :vote_id";

    private NamedParameterJdbcTemplate template;

    public VoteDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDS());
    }

    @Override
    public Vote getVoteByUserIdEntryId(long userId, long entryId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("entry_id", entryId);
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        Vote vote = null;
        try {
            vote = template.queryForObject(READ_VOTE_BY_ENTRY_USER_ID, parameterSource, new VoteRowMapper());
        } catch (EmptyResultDataAccessException e) {

        }
        return vote;
    }

    @Override
    public Vote create(Vote vote) {
        Vote voteCopy = new Vote(vote);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("entry_id", voteCopy.getEntryId());
        paramMap.put("user_id", voteCopy.getUserId());
        paramMap.put("date", voteCopy.getDate());
        paramMap.put("type", voteCopy.getVoteType().toString());
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_VOTE, parameterSource, holder);
        if (update > 0) {
            voteCopy.setId(Objects.requireNonNull(holder.getKey()).longValue());
        }
        return voteCopy;
    }

    @Override
    public Vote read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("vote_id" , primaryKey);
        Vote vote = template.queryForObject(READ_VOTE, parameterSource, new VoteRowMapper());
        return null;
    }

    @Override
    public boolean update(Vote vote) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("date", vote.getDate());
        paramMap.put("type", vote.getVoteType().toString());
        paramMap.put("vote_id", vote.getId());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_VOTE, parameterSource);
        if (update > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    private class VoteRowMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet resultSet, int i) throws SQLException {
            Vote vote = new Vote();
            vote.setId(resultSet.getLong("vote_id"));
            vote.setEntryId(resultSet.getLong("entry_id"));
            vote.setUserId(resultSet.getLong("user_id"));
            vote.setDate(resultSet.getTimestamp("date"));
            vote.setVoteType(VoteType.valueOf(resultSet.getString("type")));
            return vote;
        }
    }
}
