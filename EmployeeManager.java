import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please provide a valid argument.");
            return; // Exit the program
        }

        // Check arguments
        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readFile().split(",")) {
                    System.out.println(employee);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                String[] employees = readFile().split(",");
                System.out.println(employees[new Random().nextInt(employees.length)]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                appendToFile(args[0].substring(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String employeeToSearch = args[0].substring(1);
                if (Arrays.asList(readFile().split(",")).contains(employeeToSearch)) {
                    System.out.println("Employee " + employeeToSearch + " found!");
                } else {
                    System.out.println("Employee " + employeeToSearch + " not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                // Simplified word count logic: split by whitespace and count non-empty words
                String[] words = readFile().split("\\s+");
                System.out.println(words.length + " word(s) found.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String employeeToUpdate = args[0].substring(1);
                String[] employees = readFile().split(",");

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(employeeToUpdate)) {
                        employees[i] = "Updated";
                    }
                }

                writeFile(String.join(",", employees));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Updated.");
        } 

        else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                String employeeToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(readFile().split(",")));
                employeeList.remove(employeeToDelete);

                writeFile(String.join(",", employeeList));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Deleted.");
        }
    }

    // Method to read file and return the contents as a String
    private static String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE_PATH)));
        return reader.readLine();
    }

    // Method to append a new employee to the file
    private static void appendToFile(String newEmployee) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE_PATH, true));
        writer.write(", " + newEmployee);
        writer.close();
    }

    // Method to write to the file
    private static void writeFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE_PATH));
        writer.write(content);
        writer.close();
    }
}
