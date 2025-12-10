package diagnostic_services.iteration3;

import java.util.Scanner;
public class BiopsyMain {
    public static void main(Scanner scnr) {
        BiopsyManager biopsyManager = new BiopsyManager();

        while (true) {
            System.out.print(
                """
                1. Create a new biopsy record
                2. View biopsy records
                3. View relative biopsies
                4. Delete biopsy records
                5. Update biopsy status
                6. Exit
                >\s"""
            );

            String input = scnr.nextLine();
            System.out.println();

            switch (input) {
                case "1" -> biopsyManager.createBiopsy(scnr);
                case "2" -> {
                    biopsyManager.viewBiopsies(scnr);
                }
                case "3" -> {
                    System.out.println("Please input your id: ");

                    int id = Integer.parseInt(scnr.nextLine());
                    System.out.println();
                    biopsyManager.viewBiopsiesByUserId(id);
                }
                case "4" -> biopsyManager.deleteBiopsy(scnr);
                case "5" -> biopsyManager.updateBiopsy(scnr);
                case "6" -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }
}