
public class CheckWithrawalFunds {
    public static void main(String[] args) {
	ClassicAccount classAcc1 = new ClassicAccount(28, 5);
	Customer customer1 = new Customer("Liz");
	
	customer1.openAccount(classAcc1);
	
	RunnableWithdraw r1 = new RunnableWithdraw(customer1, classAcc1, 7);
	RunnableDeposit r2 = new RunnableDeposit(customer1, classAcc1, 2);
	
	Thread t1 = new Thread(r1);
	Thread t2 = new Thread(r2);
	
	t1.start();
	t2.start();

    }

}
