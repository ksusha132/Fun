package com.epam.dao;

import com.epam.model.TokenModel;

public interface TokenDao {
    TokenModel getTokenByUserId(Integer id);

    TokenModel getTokenByUserEmail(String Email);
}
