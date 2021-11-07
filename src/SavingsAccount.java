
public class SavingsAccount extends ClassicAccount {
	
	private double interest;
	
	public SavingsAccount(int acc, double bal, double inter ) {
		super(acc, bal);
		interest = inter;
	}
	
	public double getInterest() {
		return interest;
	}
	
	public void changeInterest(double newInt) {
		interest = newInt;
	}
	
	public void addInterest() {
		double accumInt = super.getBalance() *interest /100;
		super.deposit(accumInt);
	}

}
