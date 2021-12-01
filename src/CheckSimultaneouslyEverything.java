public class CheckSimultaneouslyEverything {
    public static void main(String[] args) {
        ClassicAccount saveAcc;
        Customer customer1;
        Customer customer2;
        saveAcc = new SavingsAccount(28, 764, 1.5);

        customer1 = new Customer("Liz");
        customer2 = new Customer("John");

        customer1.openAccount(saveAcc);
        customer2.openAccount(saveAcc);

        RunnableBalance r1 = new RunnableBalance(customer1, saveAcc);
        RunnableDeposit r2 = new RunnableDeposit(customer1, saveAcc, 1000);
        RunnableWithdraw r3 = new RunnableWithdraw(customer2, saveAcc, 123);
        RunnableBalance r4 = new RunnableBalance(customer2, saveAcc);


        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);



        t1.start();
        t2.start();
        t3.start();
        t4.start();





    }
}
