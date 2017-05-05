/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * Service.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-3-9, TangWeicheng, Create file
 */

package com.tplink.service;

import com.tplink.domain.Account;
import com.tplink.utils.AccountEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private Account defaultAccount;

    public boolean validLogin(Account account) {
        account.setPassword(
                AccountEncoder.getEncodePassword(account.getAccount(), account.getPassword()));
        boolean flag = account.checkAccount(defaultAccount);

        if (flag) {
            account.setKey(UUID.randomUUID().toString());
        }
        return flag;
    }

}
