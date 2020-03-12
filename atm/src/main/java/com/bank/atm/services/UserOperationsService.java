package com.bank.atm.services;

import com.bank.atm.beans.Transaction;
import com.bank.atm.exceptions.InsufficientFundsException;

import java.util.List;

public interface UserOperationsService {
    int addMoney(int sum);

    int retrieveMoney(int sum) throws InsufficientFundsException;

    List<Transaction> getAllTransactions(int no);

    int getCurrentSold();
}
