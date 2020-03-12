package com.bank.atm.beans;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankAccount {

    private List<Transaction> history = new ArrayList<>();

    private int currentSum = 0;

    public List<Transaction> getHistory() {
        return history;
    }

    public void setHistory(List<Transaction> history) {
        this.history = history;
    }

    public synchronized int getCurrentSum() {
        return currentSum;
    }

    public synchronized void setCurrentSum(int currentSum) {
        this.currentSum = currentSum;
    }
}
