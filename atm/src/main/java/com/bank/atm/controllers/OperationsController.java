package com.bank.atm.controllers;

import com.bank.atm.beans.Transaction;
import com.bank.atm.exceptions.InsufficientFundsException;
import com.bank.atm.services.UserOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atm")
public class OperationsController {

    @Autowired
    private UserOperationsService userOperationsService;

    @DeleteMapping("{sum}")
    public Integer retrieveFunds(@PathVariable("sum") Integer sum)
            throws InsufficientFundsException {
        return userOperationsService.retrieveMoney(sum);
    }

    @PutMapping("{sum}")
    public Object addFunds(@PathVariable("sum") Integer sum) {
        return userOperationsService.addMoney(sum);
    }

    @GetMapping("{no}")
    public List<Transaction> getTransactionsHistory(@PathVariable("no") Integer no) {
        return userOperationsService.getAllTransactions(no);
    }
}
