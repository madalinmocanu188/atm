package com.bank.atm.beans;

/**
 * A transaction has a sum (retrieved or put on the account) and a type
 */
public class Transaction {

    private TransactionType transactionType;

    private int sum;

    public Transaction(TransactionType type, int sum){
        this.transactionType = type;
        this.sum = sum;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
