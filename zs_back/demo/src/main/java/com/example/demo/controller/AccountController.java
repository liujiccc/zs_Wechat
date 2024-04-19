package com.example.demo.controller;


import com.example.demo.pojo.Account;
import com.example.demo.pojo.CustomResult;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
     AccountService accountService;

    @GetMapping("/all")
    public List<Account> getAllRecord(){
        return accountService.getAll();
    }
    @PostMapping("/add")
    public CustomResult add(@RequestBody Account requestAccount){
        Account account = new Account();
        account.setCategory(requestAccount.getCategory());
        account.setSubCategory(requestAccount.getSubCategory());
        account.setDate(requestAccount.getDate());
        account.setDesc(requestAccount.getDesc());
        account.setPay(requestAccount.getPay());
        account.setType(requestAccount.getType());
        account.setUserid(requestAccount.getUserid());
        account.setValue(requestAccount.getValue());
        accountService.updateAAccount(account);
        return new CustomResult("ok");
    }

}
