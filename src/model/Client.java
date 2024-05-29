// Client.java
package model;

import main.Payable;

public class Client extends Person implements Payable {
    private int memberId;
    private Amount balance;

    public static final int MEMBER_ID = 456;
    public static final double BALANCE = 50.00;

    public Client(String name, int memberId) {
        super(name);
        this.memberId = memberId;
        this.balance = new Amount(BALANCE, "EUR");
    }

    @Override
    public boolean pay(Amount amount) {
        if (balance.getValue() >= amount.getValue()) {
            balance.setValue(balance.getValue() - amount.getValue());
            return true;
        } else {
            System.out.println("Saldo insuficiente. Deuda pendiente: " + (amount.getValue() - balance.getValue()) + " " + balance.getCurrency());
            balance.setValue(0);
            return false;
        }
    }

    public int getMemberId() {
        return memberId;
    }

    public Amount getBalance() {
        return balance;
    }
}
