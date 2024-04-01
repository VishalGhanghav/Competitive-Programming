package com;

import java.util.HashMap;
import java.util.Map;

class Wallet {
    private String accountType;
    private String accountHolder;
    private String gst;
    private double balance;

    public Wallet(String accountType, String accountHolder, String gst, double balance) {
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.gst = gst;
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getGst() {
        return gst;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

