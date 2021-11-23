
public class RunnableEmployeeTransfer implements Runnable{
    private static final int DELAY = 1;
    private ClassicAccount account1;
    private ClassicAccount account2;
    private Employee employee;
    private double amount;

    public RunnableEmployeeTransfer(Employee e, ClassicAccount acc1, ClassicAccount acc2,double a){
        account1 = acc1;
        account2 = acc2;
        employee = e;
        amount = a;
    }

    @Override
    public void run() {
        try{
            employee.transfer(account1, account2, amount);
            Thread.sleep(DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}