public class RunnableBalance implements Runnable {
    private static final int DELAY = 1;
    private ClassicAccount account;
    private Customer customer;

    public RunnableBalance(Customer c, ClassicAccount acc){
        account = acc;
        customer = c;
    }

    @Override
    public void run() {
        try{
            customer.viewBalance(account);
            Thread.sleep(DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }

    }


}
