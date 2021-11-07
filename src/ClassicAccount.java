
public class ClassicAccount {
	
	private int accountNumber;
	private double balance;

	
	public ClassicAccount(int ac, double bal) {
		accountNumber = ac;
		balance = bal;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double deposit) {
		balance = balance + deposit;
	}
	
	public boolean withdrawal(double withdrawal) {
		if(balance - withdrawal <0) {
			return false;
		}
		balance = balance - withdrawal;
		return true;
	}
	
	public void changeBalance(double newBalance) {
		balance = newBalance;
	}
	
}
