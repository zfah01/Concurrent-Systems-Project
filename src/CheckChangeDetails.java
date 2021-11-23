
public class CheckChangeDetails {

	public static void main(String[] args) {
		
		Customer customer1 = new Customer("Liz");
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        
        RunnableModifyDetails r1 = new RunnableModifyDetails(employee1, "Ellie", customer1);
        RunnableModifyDetails r2 = new RunnableModifyDetails(employee2, "Elizabeth", customer1);
        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        
        t1.start();
        t2.start();

	}

}
