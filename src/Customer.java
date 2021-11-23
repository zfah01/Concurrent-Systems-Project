import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private String accountName;
    private Lock accountLock;
    private Lock nameLock;

    private Condition checkSufficientFunds;
    private Condition checkName;
    private List<ClassicAccount> accounts;

    public Customer(String accountName){
        accountLock = new ReentrantLock();
        nameLock = new ReentrantLock();
        checkSufficientFunds = accountLock.newCondition();
        checkName = nameLock.newCondition();
        setName(accountName);
        accounts = new ArrayList<>(3);
    }

    public Boolean withdraw(ClassicAccount account, double amount){
        boolean waiting = true;
        accountLock.lock();
        try{
            while(!account.transferState(amount)){
                if(!waiting){
                    Thread.currentThread().interrupt();
                }
                waiting=checkSufficientFunds.await(1, TimeUnit.SECONDS);
            }
            System.out.println(this.getName()+ " has starting balance of " + account.getBalance());
            if(account.getClass().equals(StudentAccount.class)) {
                ((StudentAccount) account).withdrawal(amount);
            }else{
                account.withdrawal(amount);
            }
            System.out.println(this.getName()+" wants to withdraw amount: " + amount);
            System.out.println(this.getName()+ " has new balance of " + account.getBalance());
        } catch (InterruptedException e) {
            System.out.println("Unable to wait any longer");
            return false;
        } finally {
            checkSufficientFunds.signalAll();
            accountLock.unlock();
        }
        return true;
    }

    public Boolean deposit(ClassicAccount account, double amount){
        accountLock.lock();
        try{
            System.out.println(this.getName()+ " has starting balance of " + account.getBalance());
            account.deposit(amount);
            System.out.println(this.getName()+" wants to deposit amount: "+ amount);
            System.out.println(this.getName()+ " has new balance of " + account.getBalance());
        } finally {
            checkSufficientFunds.signalAll();
            accountLock.unlock();
        }
        return true;
    }

    public Boolean transfer(ClassicAccount source, ClassicAccount destination, double amount){
        accountLock.lock();
        try{
            if(!withdraw(source,amount)){
                System.out.println("Not enough funds");
                return false;
            }
            deposit(destination,amount);
        } finally {
            checkSufficientFunds.signalAll();
            accountLock.unlock();
        }
        return true;
    }

    public void openAccount(ClassicAccount account){
        accounts.add(account);
    }

    public ClassicAccount getAccount(int accID){
        ClassicAccount account = null;

        return account;
    }

    public double viewBalance(ClassicAccount account){
        accountLock.lock();
        try{
            System.out.println(this.getName() + "'s account ID: " + account.getAccountNumber() + " Balance : " + account.getBalance());
        } finally {
            checkSufficientFunds.signalAll();
            accountLock.unlock();
        }
        return account.getBalance();
    }

    public void setName(String accountName){
        nameLock.lock();
        String defaultName = getName();
        try{
            this.accountName = accountName;
            if(defaultName != null){
                System.out.println("Name changed from : " + defaultName+ " to " + accountName);
            }
        } finally {
            checkName.signalAll();
            nameLock.unlock();
        }
    }

    public String getName(){
        nameLock.lock();
        try{
            return accountName;
        } finally{
            checkName.signalAll();
            nameLock.unlock();
        }
    }




}
