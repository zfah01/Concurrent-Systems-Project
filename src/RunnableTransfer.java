public class RunnableTransfer implements Runnable{
    private static final int DELAY = 1;
    private ClassicAccount account1;
    private ClassicAccount account2;
    private Customer customer;
    private double amount;

    public RunnableTransfer(Customer c, ClassicAccount acc1, ClassicAccount acc2,double a){
        account1 = acc1;
        account2 = acc2;
        customer = c;
        amount = a;
    }

    @Override
    public void run() {
        try{
            customer.transfer(account1, account2, amount);
            Thread.sleep(DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}
