import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please provide a valid argument.");
            printUsage();
            return; // Exit the program
        }

        // Check arguments
        switch (args[0]) {
            case "l":
                loadData();
                break;
            case "s":
                selectRandomEmployee();
                break;
            case "+":
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to add.");
                    printUsage();
                    return;
                }
                addEmployee(args[0].substring(1));
                break;
            case "?":
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to search.");
                    printUsage();
                    return;
                }
                searchEmployee(args[0].substring(1));
                break;
            case "c":
                countWords();
                break;
            case "u":
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to update.");
                    printUsage();
                    return;
                }
                updateEmployee(args[0].substring(1));
                break;
            case "d":
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to delete.");
                    printUsage();
                    return;
                }
                deleteEmployee(args[0].substring(1));
                break;
            default:
                System.out.println("Error: Invalid argument provided. Please provide a valid command.");
                printUsage();
                break;
        }
    }

    // Method to load data
    private static void loadData() {
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

    // Method to select a random employee
    private static void selectRandomEmployee() {
        System.out.println("Loading data ...");
        try {
            String[] employees = readFile().split(",");
            System.out.println(employees[new Random().nextInt(employees.length)]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    // Method to add a new employee
    private static void addEmployee(String newEmployee) {
        System.out.println("Loading data ...");
        try {
            appendToFile(newEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    // Method to search for an employee
    private static void searchEmployee(String employeeToSearch) {
        System.out.println("Loading data ...");
        try {
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

    // Method to count words in the file
    private static void countWords() {
        System.out.println("Loading data ...");
        try {
            String[] words = readFile().split("\\s+");
            System.out.println(words.length + " word(s) found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Loaded.");
    }

    // Method to update an employee
    private static void updateEmployee(String employeeToUpdate) {
        System.out.println("Loading data ...");
        try {
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

    // Method to delete an employee
    private static void deleteEmployee(String employeeToDelete) {
        System.out.println("Loading data ...");
        try {
            List<String> employeeList = new ArrayList<>(Arrays.asList(readFile().split(",")));
            employeeList.remove(employeeToDelete);

            writeFile(String.join(",", employeeList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data Deleted.");
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

    // Print usage instructions
    private static void printUsage() {
        System.out.println("\nUsage: ");
        System.out.println("l\t\t\tLoad employee data");
        System.out.println("s\t\t\tSelect a random employee");
        System.out.println("+<name>\t\t\tAdd a new employee");
        System.out.println("?<name>\t\t\tSearch for an employee");
        System.out.println("c\t\t\tCount words in the file");
        System.out.println("u<name>\t\t\tUpdate an existing employee");
        System.out.println("d<name>\t\t\tDelete an employee");
        System.out.println("Example usage: java EmployeeManager +John");
    }
}