import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Employee {

	//private String accountName;
    private Lock accountLock;
    private Lock nameLock;

    private Condition checkSufficientFunds;
    private Condition checkName;
    private List<ClassicAccount> accounts;

    public Employee() {
        accountLock = new ReentrantLock();
        nameLock = new ReentrantLock();
        checkSufficientFunds = accountLock.newCondition();
        checkName = nameLock.newCondition();
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
            System.out.println("Thread with id "+account.getAccountNumber()+ " has starting balance of " + account.getBalance());
            account.withdrawal(amount);
            System.out.println("Thread with id "+account.getAccountNumber()+" wants to withdraw amount: " + amount);
            System.out.println("Thread with id " +account.getAccountNumber()+ " has new balance of " + account.getBalance());
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
            System.out.println("Thread with id "+account.getAccountNumber()+ " has starting balance of " + account.getBalance());
            account.deposit(amount);
            System.out.println("Thread with id "+account.getAccountNumber()+" wants to deposit amount: "+ amount);
            System.out.println("Thread with id "+account.getAccountNumber()+ " has new balance of " + account.getBalance());
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
    
    public Boolean setStandingOrder(ClassicAccount source, ClassicAccount destination, double amount, int time) throws InterruptedException {
    	accountLock.lock();
        try{
            if(!withdraw(source,amount)){
                System.out.println("Not enough funds");
                return false;
            }
            Thread.sleep(time);
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
    
    public void deleteAccount(ClassicAccount account) {
    	accounts.remove(account);
    }

    public ClassicAccount getAccount(int accID){
        ClassicAccount account = null;

        return account;
    }

    public void setName(Customer c, String accountName){
        c.setName(accountName);
    }

    public String getName(Customer c){
        return c.getName();
    }



    public double viewBalance(Customer c, ClassicAccount account){
        accountLock.lock();
        try{
            System.out.println(c.getName() + "'s account ID: " + account.getAccountNumber());
        } finally {
            checkSufficientFunds.signalAll();
            accountLock.unlock();
        }
        return account.getBalance();
    }
    
    public void setBalance(ClassicAccount account, double bal) {
    	accountLock.lock();
    	try{
    		account.changeBalance(bal);
        } finally {
        	accountLock.unlock();
        }
    }

}
