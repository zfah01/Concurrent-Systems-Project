public class TestSimultaneouslyBalance {
    public static void main(String[] args) {
        ClassicAccount savingAcc;
        ClassicAccount classic1, classic2;
        Customer cust1, cust2;
        savingAcc = new SavingsAccount(1, 765,0.5);
        classic1 = new ClassicAccount(2, 876);
        classic2 = new ClassicAccount(3, 543);
        cust1 = new Customer("Alex");
        cust2 = new Customer("Alice");

        cust1.openAccount(classic1);
        cust2.openAccount(classic2);
        cust1.openAccount(savingAcc);
        cust2.openAccount(savingAcc);

        RunnableBalance r1 = new RunnableBalance(cust1, classic1);
        RunnableBalance r2 = new RunnableBalance(cust2, classic2);
        RunnableBalance r3 = new RunnableBalance(cust1, savingAcc);
        RunnableBalance r4 = new RunnableBalance(cust2, savingAcc);

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
