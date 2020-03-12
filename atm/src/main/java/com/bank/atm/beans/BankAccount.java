package com.bank.atm.beans;

import java.util.List;

public class BankAccount {

    private List<Transaction> history;

    private int currentSum;

    public List<Transaction> getHistory() {
        return history;
    }

    public void setHistory(List<Transaction> history) {
        this.history = history;
    }

    public int getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(int currentSum) {
        this.currentSum = currentSum;
    }
}
