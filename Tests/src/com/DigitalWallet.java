package com;

import java.util.HashMap;
import java.util.Map;

public class DigitalWallet {
    private static Map<String, Wallet> wallets = new HashMap<>();
    private static double transactionFeeEarned = 0;

    public static void createWallet(String accountType, String accountHolder, String gst, double amount) {
        Wallet wallet = new Wallet(accountType, accountHolder, gst, amount);
        wallets.put(accountHolder, wallet);
        System.out.println("Wallet created for " + accountHolder + " with initial balance: " + amount);
    }

    public static void addMoney(String accountHolder, double amount) {
        Wallet wallet = wallets.get(accountHolder);
        if (wallet != null) {
            wallet.setBalance(wallet.getBalance() + amount);
            System.out.println(amount + " added to the wallet of " + accountHolder);
        } else {
            System.out.println("Wallet not found for " + accountHolder);
        }
    }

    public static void transferMoney(String sender, String receiver, double amount) {
        Wallet senderWallet = wallets.get(sender);
        Wallet receiverWallet = wallets.get(receiver);

        if (senderWallet != null && receiverWallet != null) {
            if (senderWallet.getAccountType().equals("Personal") && receiverWallet.getAccountType().equals("Personal")) {
                // Apply transaction fee for Personal to Personal transfers
                amount -= 5;
                transactionFeeEarned += 5;
            }

            if (senderWallet.getBalance() >= amount) {
                senderWallet.setBalance(senderWallet.getBalance() - amount);
                receiverWallet.setBalance(receiverWallet.getBalance() + amount);

                System.out.println("Transfer successful from " + sender + " to " + receiver);
                System.out.println("New balance of " + sender + ": " + senderWallet.getBalance());
                System.out.println("New balance of " + receiver + ": " + receiverWallet.getBalance());

                // Check for the bonus condition
                if (senderWallet.getBalance() == receiverWallet.getBalance()) {
                    senderWallet.setBalance(senderWallet.getBalance() + 10);
                    receiverWallet.setBalance(receiverWallet.getBalance() + 10);
                    System.out.println("Bonus reward of F₹10 to both " + sender + " and " + receiver);
                }
            } else {
                System.out.println("Transfer failed. Insufficient balance in " + sender + "'s wallet.");
            }
        } else {
            System.out.println("Sender or receiver wallet not found.");
        }
    }

    public static void balance(String accountHolder) {
        Wallet wallet = wallets.get(accountHolder);
        if (wallet != null) {
            System.out.println(accountHolder + "'s balance: " + wallet.getBalance());
        } else {
            System.out.println("Wallet not found for " + accountHolder);
        }
    }

    public static void overview() {
        System.out.println("Overview:");
        for (Wallet wallet : wallets.values()) {
            System.out.println(wallet.getAccountHolder() + "\t" + wallet.getBalance());
        }
    }

    public static void main(String[] args) {
        createWallet("Personal", "Harry", "", 100);
        createWallet("Personal", "Ron", "", 95);
        createWallet("Personal", "Hermione", "", 104);
        createWallet("Business", "Albus", "GST1234", 200);
        createWallet("Business", "Draco", "GST5678", 500);

        overview();

        transferMoney("Albus", "Draco", 30);
        transferMoney("Hermione", "Harry", 2);
        transferMoney("Albus", "Ron", 5);

        System.out.println("Failed");

        overview();

        transferMoney("Harry", "Albus", 100);

        System.out.println("Failed");

        balance("Harry");
        balance("Albus");

        System.out.println("Transaction fee earned: " + transactionFeeEarned);
    }
}
