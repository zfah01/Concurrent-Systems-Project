
public class CheckEverythingEmployee {
	
    public static void main(String[] args) {
    	ClassicAccount classAcc1 = new ClassicAccount(28, 764);
    	ClassicAccount classAcc2 = new SavingsAccount(13, 6500, 1.5);
    	
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        
        Customer customer1 = new Customer("Liz");
        Customer customer2 = new Customer("John");
        
        customer1.openAccount(classAcc1);
        customer2.openAccount(classAcc2);
        customer1.openAccount(classAcc2);
        
        
        RunnableBalance r1 = new RunnableBalance(customer1, classAcc2);
        RunnableDeposit r2 = new RunnableDeposit(customer1, classAcc2, 1000);
        RunnableWithdraw r3 = new RunnableWithdraw(customer2, classAcc2, 123);
        RunnableBalance r4 = new RunnableBalance(customer2, classAcc2);

        RunnableEmployeeTransfer r5 = new RunnableEmployeeTransfer(employee1, classAcc1, classAcc2, 78.70);
        RunnableEmployeeTransfer r6 = new RunnableEmployeeTransfer(employee2, classAcc2,classAcc1, 34.86);
        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);
        Thread t5 = new Thread(r5);
        Thread t6 = new Thread(r6);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();


        
        
        
        
        
        
        
    }

}
