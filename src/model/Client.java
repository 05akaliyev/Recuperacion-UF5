// Client.java
package model;

public class Client extends Person {

	private int memberld;
	private Amount balance;
	final static int MEMBER_ID = 456;
    final static double BALANCE = 50.00;
	
	public Client(String name) {
		super(name);
		this.balance = new Amount(BALANCE);
	}

	public int getMemberld() {
		return memberld;
	}

	public void setMemberld(int memberld) {
		this.memberld = memberld;
	}

	public Amount getBalance() {
		return balance;
	}

	public void setBalance(Amount balance) {
		this.balance = balance;
	}

	public boolean pay(Amount saleAmount) {

		balance.setValue(balance.getValue() - saleAmount.getValue());
		if(balance.getValue() <= 0) {
			System.out.println("Saldo necesario para pagar: " + balance);
			return false;
		}else {
			System.out.println("La venta fue pagada. Nuevo saldo de cliente: " + balance);
			return true;
		}
	}
	
}
