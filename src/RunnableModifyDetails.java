public class RunnableModifyDetails implements Runnable{
    private static final int DELAY = 2;
    private ClassicAccount account;
    private Employee employee;
    private String newName;
    private Customer customer;

    public RunnableModifyDetails(Employee e, String n, Customer c){
        employee = e;
        newName = n;
        customer = c;
    }

    @Override
    public void run() {
        try{
            employee.setName(customer, newName);
            Thread.sleep(DELAY);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.exit(1);
        }

    }
}
