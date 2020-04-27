package pl.website.dao;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.website.model.Entry;
import pl.website.model.User;
import pl.website.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EntryDAOImpl implements EntryDAO {

    private static final String CREATE_ENTRY =
            "INSERT INTO entry(name, description, url, user_id, date, up_vote, down_vote)" +
                    "VALUES(:name, :description, :url, :user_id, :date, :up_vote, :down_vote);";
    private static final String READ_ALL_ENTRIES =
            "SELECT user.user_id, username, email, is_active, password, entry_id, name, description, url, date, up_vote, down_vote " +
                    "FROM entry LEFT JOIN user ON entry.user_id = user.user_id;";
    private static final String READ_ENTRY =
            "SELECT user.user_id, username, email, is_active, password, entry_id, name, description, url, date, up_vote, down_vote " +
                    "FROM entry LEFT JOIN user ON entry.user_id = user.user_id WHERE entry_id = :entry_id;";
    private static final String UPDATE_ENTRY =
            "UPDATE entry SET name = :name, description = :description, url = :url, user_id = :user_id, date = :date, up_vote = :up_vote, down_vote = :down_vote " +
                    "WHERE entry_id = :entry_id;";

    private NamedParameterJdbcTemplate template;

    public EntryDAOImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDS());
    }

    @Override
    public Entry create(Entry entry) {
        Entry resultEntry = new Entry(entry.getId(),
                entry.getName(),
                entry.getDescription(),
                entry.getUrl(),
                entry.getTimestamp(),
                entry.getUser(),
                entry.getUpVote(),
                entry.getDownVote());
        KeyHolder holder = new GeneratedKeyHolder();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", entry.getName());
        paramMap.put("description", entry.getDescription());
        paramMap.put("url", entry.getUrl());
        paramMap.put("user_id", entry.getUser().getId());
        paramMap.put("date", entry.getTimestamp());
        paramMap.put("up_vote", entry.getUpVote());
        paramMap.put("down_vote", entry.getDownVote());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(CREATE_ENTRY, parameterSource, holder);
        if (update > 0) {
            resultEntry.setId(Objects.requireNonNull(holder.getKey()).longValue());
        }
        return resultEntry;
    }

    @Override
    public Entry read(Long primaryKey) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("entry_id", primaryKey);
        Entry entry = template.queryForObject(READ_ENTRY, parameterSource, new EntryRowMapper());
        return entry;
    }

    @Override
    public boolean update(Entry entry) {
        boolean result = false;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("entry_id", entry.getId());
        paramMap.put("name", entry.getName());
        paramMap.put("description", entry.getDescription());
        paramMap.put("url", entry.getUrl());
        paramMap.put("user_id", entry.getUser().getId());
        paramMap.put("date", entry.getTimestamp());
        paramMap.put("up_vote", entry.getUpVote());
        paramMap.put("down_vote", entry.getDownVote());
        SqlParameterSource parameterSource = new MapSqlParameterSource(paramMap);
        int update = template.update(UPDATE_ENTRY, parameterSource);
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
    public List<Entry> getAll() {
        List<Entry> entries = template.query(READ_ALL_ENTRIES, new EntryRowMapper());
        return entries;
    }

    private class EntryRowMapper implements RowMapper<Entry> {
        @Override
        public Entry mapRow(ResultSet resultSet, int i) throws SQLException {
            Entry entry = new Entry();
            entry.setId(resultSet.getLong("entry_id"));
            entry.setName(resultSet.getString("name"));
            entry.setDescription(resultSet.getString("description"));
            entry.setUrl(resultSet.getString("url"));
            entry.setUpVote(resultSet.getInt("up_vote"));
            entry.setDownVote(resultSet.getInt("down_vote"));
            entry.setTimestamp(resultSet.getTimestamp("date"));
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            entry.setUser(user);
            return entry;
        }
    }
}
