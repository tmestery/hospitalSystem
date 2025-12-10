package diagnostic_services;

import diagnostic_services.file_system.*;
import diagnostic_services.iteration3.BiopsyMain;
import diagnostic_services.iteration_2.It2Main;
import java.util.Scanner;


public class DiagMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FilerSystem fs = new FilerSystem();

		boolean running = true;
		System.out.println();

		System.out.println();

		while (running) {
			System.out.print(
				"""
				What would you like to do?
				1) Chart System
				2) Sample Manager
				3) Pre-Hospital Care Reporter (PRC)
				4) Biopsy Manager
				5) Exit
				>\s"""
			);

			String in = sc.nextLine();
			System.out.println();

			if (in.equalsIgnoreCase("exit")) {
				running = false;
			} else {
				switch(Integer.parseInt(in)) {
					case 1 -> { // Chart System
						int userId = askUserId(sc, fs);

						if (userId == -1) {
							break;
						}

						System.out.print(
							"""
							Chart_System
							Select an option:
							1) Display All Charts
							2) Create Chart
							3) Exit Chart System
							>\s"""
						);
						in = sc.nextLine();
						System.out.println();

						switch(in) {
							case "1" -> {
								fs.displayCharts();
							}
							case "2" -> {
								fs.submitChart(userId, sc);
							}
							case "3" -> running = false;
						}
					}
					case 2 -> {
						int userId = askUserId(sc, fs);

						if (userId == -1) {
							break;
						}

						It2Main.sampleManager(userId, fs, sc);
					}
					case 3 -> {

						int userId = askUserId(sc, fs);

						if (userId == -1) {
							break;
						}

						It2Main.pcrReporter(userId, fs, sc);
					}
					case 4 -> {
						BiopsyMain.main(sc);
					}
					case 5 -> {
						running = false;
					}
				}
			}
		}
	}

	private static int askUserId(Scanner sc, FilerSystem fs) {
		boolean verified = false;
		System.out.println("Please log in.");
		System.out.println("'Exit': leave diagnostic services.");
		System.out.print("User id: ");
		int userId = 0;

		while (!verified) {
			String in = sc.nextLine();

			if (in.equalsIgnoreCase("exit")) {
				break;
			} else {
				try {
					userId = Integer.parseInt(in);
					return userId;
				} catch (Exception e) {
					System.out.print("Invalid id, please try again: ");
				}
			}

			verified = fs.isHealthCareProvider(userId) || fs.isLabTech(userId) || fs.isEMT(userId);
			if (!verified) {
				System.out.print("Invalid id, please try again: ");
			}
		}
		return -1;
	}
}
