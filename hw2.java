import java.util.Scanner;

public class hw2 {
	public static void main(String[] args) {
		String command;
		Scanner sc = new Scanner(System.in);
		VendingMachine v = new VendingMachine(0, 0, 0, 0, false);
		while(sc.hasNextLine()) {
			command = sc.nextLine();
			if(command.compareTo("exit") == 0) {
				break;
			}
			else {
				System.out.println(v.lambda());
				v.delta(command);
			}
		}
	}
}
