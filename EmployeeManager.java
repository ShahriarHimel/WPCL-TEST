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
                String line = readFile();
                String[] employees = line.split(",");
                for (String employee : employees) {
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
                String line = readFile();
                String[] employees = line.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employees.length);
                System.out.println(employees[randomIndex]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                String newEmployee = args[0].substring(1);
                appendToFile(newEmployee);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                String[] employees = line.split(",");
                boolean employeeFound = false;
                String employeeToSearch = args[0].substring(1);

                for (int i = 0; i < employees.length && !employeeFound; i++) {
                    if (employees[i].equals(employeeToSearch)) {
                        System.out.println("Employee found!");
                        employeeFound = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + characters.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Data Loaded.");
        } 

        else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                String line = readFile();
                String[] employees = line.split(",");
                String employeeToUpdate = args[0].substring(1);

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
                String line = readFile();
                String[] employees = line.split(",");
                String employeeToDelete = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employees));
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