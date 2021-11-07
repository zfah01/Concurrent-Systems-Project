
public class StudentAccount extends ClassicAccount{
	
	private double overdraft;
	private double tempBalance;
	
	public StudentAccount(int acc, double bal, double over) {
		super(acc, bal);
		
		overdraft = over;
		
	}
	public double getOverdraft() {
		return overdraft;
	}
	
	public void changeOverdraft(double newOver) {
		overdraft = newOver;
	}
	
	public boolean withdrawal(int withdrawal) {
		if((super.getBalance() + overdraft) - withdrawal < 0) {
			return false;
		}
		super.changeBalance(super.getBalance() - withdrawal);
		return true;
		
		
		
	}

}
