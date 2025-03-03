import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check if no arguments were provided
        if (args.length == 0) {
            System.out.println("Error: No arguments provided. Please provide a valid argument.");
            printUsageInstructions();
            return; // Exit the program if no arguments are passed
        }

        // Use a switch statement to handle different operations based on the provided argument
        switch (args[0]) {
            case "l": // Load employee data
                loadEmployeeData();
                break;
            case "s": // Select a random employee
                selectRandomEmployee();
                break;
            case "+": // Add a new employee
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to add.");
                    printUsageInstructions();
                    return;
                }
                addNewEmployee(args[0].substring(1)); // Pass the employee name without the '+' symbol
                break;
            case "?": // Search for an employee
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to search.");
                    printUsageInstructions();
                    return;
                }
                searchEmployeeByName(args[0].substring(1)); // Pass the employee name without the '?' symbol
                break;
            case "c": // Count the words in the employee data
                countWordsInEmployeeData();
                break;
            case "u": // Update an existing employee's name
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to update.");
                    printUsageInstructions();
                    return;
                }
                updateEmployeeName(args[0].substring(1)); // Pass the new employee name without the 'u' symbol
                break;
            case "d": // Delete an employee by name
                if (args.length < 2) {
                    System.out.println("Error: No employee name provided to delete.");
                    printUsageInstructions();
                    return;
                }
                deleteEmployeeByName(args[0].substring(1)); // Pass the employee name without the 'd' symbol
                break;
            default: // Invalid argument provided
                System.out.println("Error: Invalid argument provided. Please provide a valid command.");
                printUsageInstructions();
                break;
        }
    }

    // Method to load employee data from the file and print it
    private static void loadEmployeeData() {
        System.out.println("Loading employee data ...");
        try {
            // Read the file and print each employee's name
            String[] employees = readEmployeeFile().split(",");
            for (String employee : employees) {
                System.out.println(employee);
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to load employee data.");
            e.printStackTrace();
        }
        System.out.println("Employee data loaded.");
    }

    // Method to select and print a random employee from the list
    private static void selectRandomEmployee() {
        System.out.println("Loading employee data ...");
        try {
            // Split the file contents by commas and pick a random employee
            String[] employees = readEmployeeFile().split(",");
            System.out.println("Randomly selected employee: " + employees[new Random().nextInt(employees.length)]);
        } catch (IOException e) {
            System.out.println("Error: Failed to load employee data.");
            e.printStackTrace();
        }
        System.out.println("Employee data loaded.");
    }

    // Method to add a new employee to the file
    private static void addNewEmployee(String newEmployeeName) {
        System.out.println("Loading employee data ...");
        try {
            // Append the new employee name to the file
            appendEmployeeToFile(newEmployeeName);
        } catch (IOException e) {
            System.out.println("Error: Failed to add new employee.");
            e.printStackTrace();
        }
        System.out.println("New employee added.");
    }

    // Method to search for an employee by name
    private static void searchEmployeeByName(String employeeName) {
        System.out.println("Loading employee data ...");
        try {
            // Split the file contents by commas and search for the employee
            String[] employees = readEmployeeFile().split(",");
            if (Arrays.asList(employees).contains(employeeName)) {
                System.out.println("Employee " + employeeName + " found!");
            } else {
                System.out.println("Employee " + employeeName + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to search for employee.");
            e.printStackTrace();
        }
        System.out.println("Employee search completed.");
    }

    // Method to count the number of words in the employee data
    private static void countWordsInEmployeeData() {
        System.out.println("Loading employee data ...");
        try {
            // Split the file contents by whitespace and count the number of words
            String[] words = readEmployeeFile().split("\\s+");
            System.out.println("Word count: " + words.length + " word(s) found.");
        } catch (IOException e) {
            System.out.println("Error: Failed to count words.");
            e.printStackTrace();
        }
        System.out.println("Word count completed.");
    }

    // Method to update an existing employee's name
    private static void updateEmployeeName(String oldEmployeeName) {
        System.out.println("Loading employee data ...");
        try {
            // Split the file contents and update the employee name if found
            String[] employees = readEmployeeFile().split(",");
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].equals(oldEmployeeName)) {
                    employees[i] = "Updated";
                    break;
                }
            }
            // Save the updated list of employees back to the file
            writeEmployeeDataToFile(String.join(",", employees));
        } catch (IOException e) {
            System.out.println("Error: Failed to update employee name.");
            e.printStackTrace();
        }
        System.out.println("Employee name updated.");
    }

    // Method to delete an employee by name
    private static void deleteEmployeeByName(String employeeName) {
        System.out.println("Loading employee data ...");
        try {
            // Split the file contents into a list and remove the employee
            List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeeFile().split(",")));
            employeeList.remove(employeeName);

            // Save the updated list of employees back to the file
            writeEmployeeDataToFile(String.join(",", employeeList));
        } catch (IOException e) {
            System.out.println("Error: Failed to delete employee.");
            e.printStackTrace();
        }
        System.out.println("Employee deleted.");
    }

    // Method to read employee data from the file and return it as a string
    private static String readEmployeeFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.EMPLOYEE_FILE_PATH)));
        return reader.readLine();
    }

    // Method to append a new employee name to the file
    private static void appendEmployeeToFile(String newEmployeeName) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE_PATH, true));
        writer.write(", " + newEmployeeName);
        writer.close();
    }

    // Method to write updated employee data to the file
    private static void writeEmployeeDataToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE_PATH));
        writer.write(content);
        writer.close();
    }

    // Method to print usage instructions
    private static void printUsageInstructions() {
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