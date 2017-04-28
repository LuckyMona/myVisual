/*
 * Copyright (C) 2015, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * HomeController.java
 *
 * Description
 *
 * Author TangWeicheng
 *
 * Ver 1.0, 2017-3-9, TangWeicheng, Create file
 */

package com.tplink.controller;

import com.tplink.annotation.LogRequired;
import com.tplink.domain.Account;
import com.tplink.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("prototype")
@RequestMapping("/account")
public class AccountController extends BasicController {

    @Autowired
    AccountService accountService;

    public static final String ACCOUNT_LOGIN_KEY = "user_account";

    @RequestMapping(value = "/login")
    @ResponseBody
    @LogRequired
    public Object login(Account account, HttpServletRequest request) {
        boolean flag = accountService.validLogin(account);
        if (flag) {
            request.getSession().setAttribute(ACCOUNT_LOGIN_KEY, account);
            setType(SUCCESS);
        } else {
            setType(ERROR);
            setErrorMessage("用户名或密码错误！");
        }
        return getResult();
    }

}
