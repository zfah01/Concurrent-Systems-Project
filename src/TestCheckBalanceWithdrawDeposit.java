public class TestCheckBalanceWithdrawDeposit {
    public static void main(String[] args) {
    ClassicAccount stuAcc;
    Customer customer1;
    Customer customer2;
    stuAcc = new StudentAccount(3, 1023, 45);

    customer1 = new Customer("Zak");
    customer2 = new Customer("Ali");

        customer1.openAccount(stuAcc);
        customer2.openAccount(stuAcc);


    RunnableDeposit r1 = new RunnableDeposit(customer2, stuAcc, 300);
    RunnableWithdraw r2 = new RunnableWithdraw(customer2, stuAcc, 123);


    Thread t1 = new Thread(r1);
    Thread t2 = new Thread(r2);



        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        t1.start();
        try{
            Thread.sleep(2000);
            customer1.viewBalance(stuAcc);
        }catch(
                InterruptedException e){
            System.out.println("Interrupted");
        }




}
}
