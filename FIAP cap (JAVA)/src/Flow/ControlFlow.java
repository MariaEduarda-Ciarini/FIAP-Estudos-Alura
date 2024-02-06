package Flow;

public class ControlFlow {
	public static void main(String[] args) {
		
		int age = 60;
		if (age >= 20 && age < 50) {
			System.out.println("Of legal age");
		}else if (age >= 50){
			System.out.println("Arrived halfway through life");
		}else  {
			System.out.println("Younger age");
		}
	}
}
