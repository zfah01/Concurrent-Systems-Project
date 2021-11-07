import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<ClassicAccount> accountsArray = new ArrayList<ClassicAccount>();
		ClassicAccount current = null;
		boolean exists = false;
		boolean account = true;
		//random accounts added for testing
		ClassicAccount account1 = new ClassicAccount(1, 246.67);
		StudentAccount account2 = new StudentAccount(2, 6.89, 2000);
		SavingsAccount account3 = new SavingsAccount(3, 1872.70, 0.5);
		accountsArray.add(account1);
		accountsArray.add(account2);
		accountsArray.add(account3);
		//just for testing
		for(int i = 0; i < accountsArray.size();i++) {
			System.out.println(accountsArray.get(i).getClass());
		}
		
		while(true) {
			System.out.println("Welcome to the bank. Please enter your account number." + accountsArray.size());
			Scanner myScan = new Scanner(System.in);
			int aNumber = myScan.nextInt();
			for(int i = 0; i < accountsArray.size();i++) {
				System.out.println("checking");
				if(accountsArray.get(i).getAccountNumber() == aNumber) {
					current = accountsArray.get(i);
					exists = true;
				}
			}
			if(exists == false) {//create account
				System.out.println("Account with that number does not exist. Would you like to create an account?");
				Scanner myScan2 = new Scanner(System.in);
				String answer = myScan2.nextLine();
				if (answer.equals("yes")) {
					System.out.println("Please choose an account type: Classic, Student, Savings");
					if (myScan2.nextLine().toLowerCase().trim().equals("student")) {
						StudentAccount newAccount = new StudentAccount((accountsArray.size()+1),0, 500);
						System.out.println("A student account has been created. Your account number is: " + newAccount.getAccountNumber());
						accountsArray.add(newAccount);
						current = newAccount;
					}else if(myScan2.nextLine().toLowerCase().trim().equals("savings")){
						SavingsAccount newAccount = new SavingsAccount((accountsArray.size()+1),0,0.5);
						System.out.println("A savings account has  been created. Your account number is: " + newAccount.getAccountNumber());
						accountsArray.add(newAccount);
						current = newAccount;
					}else {
						ClassicAccount newAccount = new ClassicAccount((accountsArray.size()+1),0);
						System.out.println("A classic account has  been created. Your account number is: " + newAccount.getAccountNumber());
						accountsArray.add(newAccount);
						current = newAccount;
					}
					
				}else {
					account = false;//make it jump out of loop
				}
			}
			if(account == true) {
				boolean exit = false;
				while(!exit) {
					if(current.getClass().equals(ClassicAccount.class)) {
						System.out.println("Please enter what you would like to do: ");
						System.out.println("Balance/Deposit/Withdraw/Transfer/Delete/Exit");
						Scanner myScan2 = new Scanner(System.in);
						String response = myScan2.nextLine().toLowerCase().trim();
						if (response.equals("balance")) {
							System.out.println("Your balance is: " + current.getBalance());
						}
						if (response.equals("deposit")) {
							System.out.println("How much would you like to deposit?");
							double answer = myScan2.nextDouble();
							current.deposit(answer);
							System.out.println("Your balance is now: " + current.getBalance());
						}
						if (response.equals("withdraw")) {
							System.out.println("How much would you like to withdraw?");
							double answer = myScan2.nextDouble();
							if(current.withdrawal(answer)== false) {
								System.out.println("Insufficient Funds");
							}else {
								System.out.println("Your balance is now: " + current.getBalance());
							}
						}
						if (response.equals("transfer")) {
							System.out.println("Please enter the account number of transferee: ");
							double answer = myScan2.nextInt();
							ClassicAccount transferee = null;
							exists = false;
							for(int i = 0; i < accountsArray.size();i++) {
								System.out.println("checking");
								if(accountsArray.get(i).getAccountNumber() == answer) {
									transferee = accountsArray.get(i);
									exists = true;
								}
							}
							if(exists) {
								System.out.println("How much would you like to transfer?");
								answer = myScan2.nextInt();
								if(current.withdrawal(answer)== false) {
									System.out.println("Insufficient Funds");
								}else {
									System.out.println("Your balance is now: " + current.getBalance());
									transferee.deposit(answer);
								}
							}else {
								System.out.println("Account does not exist.");
							}
						}
						if (response.equals("delete")) {
							System.out.println("Are you sure you want to delete your account?");
							String answer = myScan2.nextLine().toLowerCase().trim();
							if (answer.equals("yes")) {
								for(int i = 0; i < accountsArray.size();i++) {
									if(accountsArray.get(i).getAccountNumber() == current.getAccountNumber()) {
										accountsArray.remove(i);
									}
								}
							}
						}
						if (response.equals("exit")) {
							System.out.println("Goodbye and thank you for using bank 17.");
							break;
						}
						else {
							System.out.println("Invalid resopnse. Please try again.");
						}
					}
					if(current.getClass().equals(StudentAccount.class)) {
						System.out.println("Please enter what you would like to do: ");
						System.out.println("Balance/Overdraft/Deposit/Withdraw/Transfer/Delete/Exit");
						Scanner myScan2 = new Scanner(System.in);
						String response = myScan2.nextLine().toLowerCase().trim();
						if (response.equals("balance")) {
							System.out.println("Your balance is: " + current.getBalance());
						}
						if (response.equals("deposit")) {
							System.out.println("How much would you like to deposit?");
							double answer = myScan2.nextDouble();
							current.deposit(answer);
							System.out.println("Your balance is now: " + current.getBalance());
						}
						if (response.equals("withdraw")) {
							System.out.println("How much would you like to withdraw?");
							double answer = myScan2.nextDouble();
							if(current.withdrawal(answer)== false) {
								System.out.println("Insufficient Funds");
							}else {
								System.out.println("Your balance is now: " + current.getBalance());
							}
						}
						if (response.equals("transfer")) {
							System.out.println("Please enter the account number of transferee: ");
							double answer = myScan2.nextInt();
							ClassicAccount transferee = null;
							exists = false;
							for(int i = 0; i < accountsArray.size();i++) {
								System.out.println("checking");
								if(accountsArray.get(i).getAccountNumber() == answer) {
									transferee = accountsArray.get(i);
									exists = true;
								}
							}
							if(exists) {
								System.out.println("How much would you like to transfer?");
								answer = myScan2.nextInt();
								if(current.withdrawal(answer)== false) {
									System.out.println("Insufficient Funds");
								}else {
									System.out.println("Your balance is now: " + current.getBalance());
									transferee.deposit(answer);
								}
							}else {
								System.out.println("Account does not exist.");
							}
						}
						if (response.equals("delete")) {
							System.out.println("Are you sure you want to delete your account?");
							String answer = myScan2.nextLine().toLowerCase().trim();
							if (answer.equals("yes")) {
								for(int i = 0; i < accountsArray.size();i++) {
									if(accountsArray.get(i).getAccountNumber() == current.getAccountNumber()) {
										accountsArray.remove(i);
									}
								}
							}
						}
						if (response.equals("overdraft")){
							System.out.println("Your overdraft is: "+ ((StudentAccount)current).getOverdraft());
						}
						if (response.equals("exit")) {
							System.out.println("Goodbye and thank you for using bank 17.");
							break;
						}
						else {
							System.out.println("Invalid resopnse. Please try again.");
						}
					}
					if(current.getClass().equals(SavingsAccount.class)) {
						System.out.println("Please enter what you would like to do: ");
						System.out.println("Balance/Interest/Deposit/Withdraw/Transfer/Delete/Exit");
						Scanner myScan2 = new Scanner(System.in);
						String response = myScan2.nextLine().toLowerCase().trim();
						if (response.equals("balance")) {
							System.out.println("Your balance is: " + current.getBalance());
						}
						if (response.equals("interest")) {
							System.out.println("Your interest rate is: " + ((SavingsAccount)current).getInterest());
						}
						if (response.equals("deposit")) {
							System.out.println("How much would you like to deposit?");
							double answer = myScan2.nextDouble();
							current.deposit(answer);
							System.out.println("Your balance is now: " + current.getBalance());
						}
						if (response.equals("withdraw")) {
							System.out.println("How much would you like to withdraw?");
							double answer = myScan2.nextDouble();
							if(current.withdrawal(answer)== false) {
								System.out.println("Insufficient Funds");
							}else {
								System.out.println("Your balance is now: " + current.getBalance());
							}
						}
						if (response.equals("transfer")) {
							System.out.println("Please enter the account number of transferee: ");
							double answer = myScan2.nextInt();
							ClassicAccount transferee = null;
							exists = false;
							for(int i = 0; i < accountsArray.size();i++) {
								System.out.println("checking");
								if(accountsArray.get(i).getAccountNumber() == answer) {
									transferee = accountsArray.get(i);
									exists = true;
								}
							}
							if(exists) {
								System.out.println("How much would you like to transfer?");
								answer = myScan2.nextInt();
								if(current.withdrawal(answer)== false) {
									System.out.println("Insufficient Funds");
								}else {
									System.out.println("Your balance is now: " + current.getBalance());
									transferee.deposit(answer);
								}
							}else {
								System.out.println("Account does not exist.");
							}
						}
						if (response.equals("delete")) {
							System.out.println("Are you sure you want to delete your account?");
							String answer = myScan2.nextLine().toLowerCase().trim();
							if (answer.equals("yes")) {
								for(int i = 0; i < accountsArray.size();i++) {
									if(accountsArray.get(i).getAccountNumber() == current.getAccountNumber()) {
										accountsArray.remove(i);
									}
								}
							}
						}
						if (response.equals("exit")) {
							System.out.println("Goodbye and thank you for using bank 17.");
							break;
						}
						else {
							System.out.println("Invalid resopnse. Please try again.");
						}
					}
					
					
				}
			}
		}
		
	
		// TODO Auto-generated method stub

	}

}
