public class RunnableDeposit implements Runnable{
    private static final int DELAY = 2;
    private ClassicAccount account;
    private Customer customer;
    private double amount;

    public RunnableDeposit(Customer c, ClassicAccount acc, double a){
        account = acc;
        customer = c;
        amount = a;
    }

    @Override
    public void run() {
        try{
            customer.deposit(account, amount);
            Thread.sleep(DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}

