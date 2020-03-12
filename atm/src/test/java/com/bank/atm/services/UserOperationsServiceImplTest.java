package com.bank.atm.services;

import com.bank.atm.beans.BankAccount;
import com.bank.atm.beans.Transaction;
import com.bank.atm.beans.TransactionType;
import com.bank.atm.exceptions.InsufficientFundsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserOperationsService.class, UserOperationsServiceImpl.class, BankAccount.class})
public class UserOperationsServiceImplTest {

    @Autowired
    private UserOperationsService service;

    @Test
    public void testAddMoney() {
        service.addMoney(100);
        Assert.assertEquals(100, service.getCurrentSold());

        service.addMoney(2);
        Assert.assertEquals(102, service.getCurrentSold());
    }

    @Test
    public void testRetrieveMoney() throws InsufficientFundsException {
        service.addMoney(100);
        service.retrieveMoney(50);
        Assert.assertEquals(50, service.getCurrentSold());

        service.retrieveMoney(50);
        Assert.assertEquals(0, service.getCurrentSold());
    }

    @Test(expected = InsufficientFundsException.class)
    public void testRetrieveMoneyException() throws InsufficientFundsException {
        service.addMoney(20);

        service.retrieveMoney(service.getCurrentSold() + 1);
    }

    @Test
    public void testGetAllTransactions() throws InsufficientFundsException {
        service.addMoney(6);
        service.retrieveMoney(2);
        service.retrieveMoney(3);
        //getting the last 2 transactions.
        List<Transaction> transactions = service.getAllTransactions(2);
        Assert.assertEquals(2, transactions.size());

        Assert.assertEquals(TransactionType.RETRIEVE, transactions.get(0).getTransactionType());
        Assert.assertEquals(2, transactions.get(0).getSum());

        Assert.assertEquals(TransactionType.RETRIEVE, transactions.get(1).getTransactionType());
        Assert.assertEquals(3, transactions.get(1).getSum());
    }
}
