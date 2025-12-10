package nursing_services.careplan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarePlanStore {

    private static final String FILE_NAME = "careplans.csv";
    public static void appendCarePlanUpdate(CarePlanUpdate update) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(update.toCSV() + "\n");
        } catch (IOException e) {
            System.out.println("Error in writing care plan file: " + e.getMessage());
        }
    }

    
    public static List<CarePlanUpdate> loadCarePlans(String patientId) {

        List<CarePlanUpdate> list = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = parseCSV(line);
                if (parts.length < 8) continue;

                if (!parts[0].equals(patientId)) continue;

                list.add(new CarePlanUpdate(
                        parts[0],   
                        parts[1],   
                        parts[2],   
                        parts[3],   
                        parts[4],   
                        parts[5],   
                        parts[6],   
                        parts[7]    
                ));
            }
        } catch (IOException e) {
            System.out.println("Error reading care plan file: " + e.getMessage());
        }

        return list;
    }

    private static String[] parseCSV(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') inQuotes = !inQuotes;
            else if (c == ',' && !inQuotes) {
                result.add(sb.toString().trim());
                sb.setLength(0);
            } else sb.append(c);
        }
        result.add(sb.toString().trim());

        return result.toArray(new String[0]);
    }
}
