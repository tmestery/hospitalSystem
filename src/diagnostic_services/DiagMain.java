package diagnostic_services;

import java.util.Scanner;
import diagnostic_services.file_system.*;

public class DiagMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FilerSystem fs = new FilerSystem();
		
		boolean verified = false;
		boolean running = true;
		System.out.println();
		System.out.println("Q: Quit any time.");
		System.out.println("Please log in.");
		System.out.print("User id: ");
		
		while (!verified) {
			String in = sc.next();
			int userId = 0;
			
			System.out.println(in);
			
			if (in.equalsIgnoreCase("q")) {
				break;
			} else {
				try {
					userId = Integer.parseInt(in);
				} catch (Exception e) {
					System.out.println("Wrong input type. " + e);
				}
			}
			
			verified = fs.isHealthCareProvider(userId);
			if (!verified) {
				System.out.print("Invalid id, please try again: ");
			}
		}
		
		System.out.println();
		
		while (verified && running) {
			System.out.print(
"""		
What would you like to do?
1: View all current charts.
2: Exit
"""
);			String in  = sc.next();

			if (in.equalsIgnoreCase("q")) {
				running = false;
			} else {
				switch(Integer.parseInt(in)) {
				case 1:
					System.out.println();
					fs.print();
					
					break;
				case 2:
					running = false;
				}
			}
		}
	}
}
