package com.epam.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("tokenRepositoryDao")
public class TokenDaoImpl implements PersistentTokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String DEF_TOKEN_BY_SERIES_SQL = "SELECT username,series,token,last_used FROM persistent_logins WHERE series = ?";
    private static final String DEF_INSERT_TOKEN_SQL = "INSERT INTO persistent_logins (username, series, token, last_used) VALUES(?,?,?,?)";
    private static final String DEF_UPDATE_TOKEN_SQL = "UPDATE persistent_logins SET token = ?, last_used = ? WHERE series = ?";
    private static final String DEF_REMOVE_USER_TOKENS_SQL = "DELETE FROM persistent_logins WHERE username = ?";

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {

        jdbcTemplate.update(DEF_INSERT_TOKEN_SQL, persistentRememberMeToken.getUsername(), persistentRememberMeToken.getSeries(),
                persistentRememberMeToken.getTokenValue(), persistentRememberMeToken.getDate());
    }

    @Override
    public void updateToken(String series, String tokenValue, Date date) {

        jdbcTemplate.update(DEF_UPDATE_TOKEN_SQL, tokenValue, date, series);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {

        return jdbcTemplate.queryForObject(DEF_TOKEN_BY_SERIES_SQL,
                (rs, rowNum) -> new PersistentRememberMeToken(rs.getString(1), rs
                        .getString(2), rs.getString(3), rs.getTimestamp(4)), series);
    }

    @Override
    public void removeUserTokens(String series) {

        jdbcTemplate.update(DEF_REMOVE_USER_TOKENS_SQL, series);
    }
}
