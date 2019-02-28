package com.epam.dao.impl;

import com.epam.dao.TokenDao;
import com.epam.model.TokenModel;
import org.springframework.stereotype.Repository;

@Repository("TokenDao")
public class TokenDaoImpl implements TokenDao {
    @Override
    public TokenModel getTokenByUserId(Integer id) {
        return null;
    }

    @Override
    public TokenModel getTokenByUserEmail(String Email) {
        return null;
    }
}
