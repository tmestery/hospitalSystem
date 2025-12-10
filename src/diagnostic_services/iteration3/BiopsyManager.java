package diagnostic_services.iteration3;

import diagnostic_services.file_system.Biopsy;
import diagnostic_services.file_system.FilerSystem;
import diagnostic_services.file_system.Status;

import java.util.Scanner;
import roles.Doctor;
import roles.Patient;

public class BiopsyManager {
  FilerSystem filerSystem = new FilerSystem();

  public void createBiopsy(Scanner scnr) {
    Biopsy.Category type = null;
    Doctor doctor;
    Patient patient;

    while (true) {
      System.out.print("Enter the ID of the doctor performing the biopsy: ");
      String docInput = scnr.nextLine();
      try {
        int docId = Integer.parseInt(docInput);
        doctor = filerSystem.getDoctorById(docId);

        if (doctor != null) {
          type = doctor.getCategory();
          break;
        } else {
          System.out.println("Doctor not found. Please try again.");
        }

      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid doctor ID.");
      }
    }

    while (true) {
      System.out.print("Enter the ID of the patient undergoing the biopsy: ");
      String patInput = scnr.nextLine();
      try {
        int patId = Integer.parseInt(patInput);
        patient = filerSystem.getPatientById(patId);

        if (patient != null) {
          break;
        } else {
          System.out.println("Patient not found. Please try again.");
        }

      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid patient ID.");
      }
    }

    Biopsy biopsy = new Biopsy(type, doctor, patient);

    System.out.println("Would you like to save? (Y/N)");
    System.out.print(">\s");
    String in = scnr.nextLine();
    if (in.equalsIgnoreCase("N")) {
      System.out.println("Biopsy creation cancelled.");
      return;
    }

    filerSystem.save(biopsy);
  }

  public void viewBiopsies(Scanner scnr) {
    filerSystem.displayBiopsies();
  }

  public void viewBiopsiesByUserId(int id) {
    filerSystem.displayBiopsiesByUserId(id);
  }

  public void deleteBiopsy(Scanner scnr) {
    System.out.print("Enter the ID of the biopsy to delete: ");
    String input = scnr.nextLine();

    int id = Integer.parseInt(input);
    filerSystem.deleteBiopsy(id);
  }

  public void updateBiopsy(Scanner scnr) {
    Biopsy biopsy = null;
    String in;

    while (biopsy == null) {
      System.out.print("""
        Please enter the id of the biopsy you wish to update or Q to quit.
        >\s"""
      );

      in = scnr.nextLine();
      biopsy = filerSystem.getBiopsyById(Integer.parseInt(in));
    }

    OUTER:
    while (true) {
      System.out.print("""
        What would you like to do?
        1. Update Status
        2. Add Notes
        3. Complete
        >\s"""
      );

      in = scnr.nextLine();
      System.out.println();

      switch (in) {
        case "1" -> {
          System.out.print("""
            What status would you like to set it to?
            1. PENDING
            2. IN PROGRESS
            3. COMPLETED
            4. CANCELLED
            5. Exit
            >\s"""
          );

          in = scnr.nextLine();
          switch (in) {
            case "1" -> { biopsy.setStatus(Status.PENDING); }
            case "2" -> { biopsy.setStatus(Status.IN_PROGRESS); }
            case "3" -> { biopsy.setStatus(Status.COMPLETED); }
            case "4" -> { biopsy.setStatus(Status.CANCELLED); }
            case "5" -> {
              break;
            }
          }
        }
        case "2" -> {
          System.out.println("Enter notes you would like to add.");
          in = scnr.nextLine();
          biopsy.addNotes(in);
        }
        case "3" -> {
          break OUTER;
        }
      }
    }

    System.out.print("""
      Would you like to save? (Y/N)
      >\s"""
    );

    in = scnr.nextLine();
    System.out.println();

    if (in.equalsIgnoreCase("N")) {
      return;
    }

    filerSystem.save(biopsy);
  }
}
