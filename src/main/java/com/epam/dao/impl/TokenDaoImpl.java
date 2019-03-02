package com.epam.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository("tokenRepositoryDao")
public class TokenDaoImpl implements PersistentTokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        String qr = "INSERT INTO token (email, series, date, token) VALUES (?,?,?,?)";
        jdbcTemplate.update(qr, persistentRememberMeToken.getUsername(), persistentRememberMeToken.getSeries(),
                persistentRememberMeToken.getDate(), persistentRememberMeToken.getTokenValue());
    }

    @Override
    public void updateToken(String series, String tokenValue, Date date) {
        String SQL = "UPDATE token SET token = ?, last_used = ? WHERE series = ?";
        jdbcTemplate.update(SQL, tokenValue, date, series);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String series) {
        String SQL = "SELECT * FROM token WHERE series = ?";
        return jdbcTemplate.queryForObject(SQL, new Object[]{series}, new BeanPropertyRowMapper<>(PersistentRememberMeToken.class));
    }

    @Override
    public void removeUserTokens(String series) {
        String SQL = "DELETE FROM token WHERE series = ?";
        jdbcTemplate.update(SQL, series);
    }
}
