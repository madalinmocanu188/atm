package com.bank.atm.services;

import com.bank.atm.beans.BankAccount;
import com.bank.atm.beans.Transaction;
import com.bank.atm.beans.TransactionType;
import com.bank.atm.exceptions.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service("UserOperations")
public class UserOperationsServiceImpl implements UserOperationsService {

    private Logger logger = Logger.getLogger(UserOperationsServiceImpl.class.getName());

    @Autowired
    private BankAccount bankAccount;

    /**
     * @param sum -> the sum to put in the bank account
     * @return -> The new available sum in the bank account.
     */
    @Override
    public int addMoney(int sum) {
        bankAccount.setCurrentSum(bankAccount.getCurrentSum() + sum);
        bankAccount.getHistory().add(new Transaction(TransactionType.PUT, sum));
        logger.info("Sum " + sum + " has been added to your bank account");
        return bankAccount.getCurrentSum();
    }

    /**
     * @param sum -> sum to retrieve
     * @return -> the current sold after retrieving
     * @throws InsufficientFundsException - Thrown if there are insufficient funds to retrieve.
     */
    @Override
    public int retrieveMoney(int sum) throws InsufficientFundsException {
        if (sum > bankAccount.getCurrentSum()) {
            logger.info("You do not have sufficient funds to retrieve sum " + sum);
            throw new InsufficientFundsException("Insufficient funds");
        }
        bankAccount.getHistory().add(new Transaction(TransactionType.RETRIEVE, sum));
        bankAccount.setCurrentSum(bankAccount.getCurrentSum() - sum);
        return bankAccount.getCurrentSum();
    }

    /**
     * Gets a number no of transactions.
     *
     * @param no -> number of transactions.
     * @return A list of transactions
     */
    @Override
    public List<Transaction> getAllTransactions(int no) {
        return bankAccount.getHistory().stream()
                .skip(Math.max(0, bankAccount.getHistory().size() - no))
                .collect(Collectors.toList());
    }

    @Override
    public int getCurrentSold() {
        return bankAccount.getCurrentSum();
    }
}
