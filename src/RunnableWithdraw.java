public class RunnableWithdraw implements Runnable{
    private static final int DELAY = 1;
    private ClassicAccount account;
    private Customer customer;
    private double amount;

    public RunnableWithdraw(Customer c, ClassicAccount acc, double a){
        account = acc;
        customer = c;
        amount = a;
    }

    @Override
    public void run() {
        try{
            customer.withdraw(account, amount);
            Thread.sleep(DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}
