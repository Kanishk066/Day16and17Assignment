package com.bl.algorithmproblems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BankingCashCounter {
    private int cashBalance;
    private Queue<Customer> queue;

    public BankingCashCounter(int initialCashBalance) {
        cashBalance = initialCashBalance;
        queue = new LinkedList<Customer>();
    }

    public void enqueueCustomer(Customer customer) {
        queue.offer(customer);
    }

    public void dequeueCustomer() {
        if (!queue.isEmpty()) {
            Customer customer = queue.poll();
            if (customer.getTransactionType() == TransactionType.DEPOSIT) {
                cashBalance += customer.getAmount();
            } else {
                if (customer.getAmount() > cashBalance) {
                    System.out.println("Insufficient funds to withdraw " + customer.getAmount() + " for customer " + customer.getName());
                } else {
                    cashBalance -= customer.getAmount();
                }
            }
        }
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the initial cash balance: ");
        int initialCashBalance = scanner.nextInt();
        BankingCashCounter cashCounter = new BankingCashCounter(initialCashBalance);
        System.out.println("Cash balance: " + cashCounter.getCashBalance());
        while (true) {
            System.out.println("Enter a customer name or 'quit' to exit: ");
            String name = scanner.next();
            if (name.equals("quit")) {
                break;
            }
            System.out.println("Enter the transaction type (d/w): ");
            String transactionTypeStr = scanner.next();
            TransactionType transactionType = null;
            if (transactionTypeStr.equals("d")) {
                transactionType = TransactionType.DEPOSIT;
            } else if (transactionTypeStr.equals("w")) {
                transactionType = TransactionType.WITHDRAW;
            } else {
                System.out.println("Invalid transaction type.");
                continue;
            }
            System.out.println("Enter the transaction amount: ");
            int amount = scanner.nextInt();
            Customer customer = new Customer(name, transactionType, amount);
            cashCounter.enqueueCustomer(customer);
        }
        while (!cashCounter.queue.isEmpty()) {
            cashCounter.dequeueCustomer();
        }
        System.out.println("Final cash balance: " + cashCounter.getCashBalance());
        scanner.close();
    }

}

enum TransactionType {
    DEPOSIT,
    WITHDRAW
}

class Customer {

    private String name;
    private TransactionType transactionType;
    private int amount;

    public Customer(String name, TransactionType transactionType, int amount) {
        this.name = name;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getAmount() {
        return amount;
    }
}
