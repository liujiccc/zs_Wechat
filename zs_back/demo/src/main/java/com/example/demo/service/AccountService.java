package com.example.demo.service;


import com.example.demo.dao.AccountDao;
import com.example.demo.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;
    public List<Account> getAll(){
        List<Account> accountList = accountDao.findAll();
        return accountList;
    }

    public void updateAAccount(Account account){
        accountDao.save(account);
    }

}
