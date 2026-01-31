import java.util.ArrayList;
import java.util.Scanner;

/**
 * Console-based Student Grade Tracker
 */
public class GradeTrackerConsole {
    private ArrayList<Student> students;
    private Scanner scanner;
    private static final int MAX_GRADES_PER_STUDENT = 10;
    
    public GradeTrackerConsole() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public void run() {
        System.out.println("========================================");
        System.out.println("   STUDENT GRADE TRACKER SYSTEM");
        System.out.println("========================================\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addGradeToStudent();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    displaySummaryReport();
                    break;
                case 6:
                    running = false;
                    System.out.println("\nThank you for using Grade Tracker!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Add New Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Student Details");
        System.out.println("4. View All Students");
        System.out.println("5. Display Summary Report");
        System.out.println("6. Exit");
        System.out.println("-----------------");
    }
    
    private void addStudent() {
        System.out.print("\nEnter student name: ");
        String name = scanner.nextLine();
        
        if (findStudent(name) != null) {
            System.out.println("Student already exists!");
            return;
        }
        
        Student student = new Student(name, MAX_GRADES_PER_STUDENT);
        students.add(student);
        System.out.println("Student '" + name + "' added successfully!");
    }
    
    private void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("\nNo students in the system. Add a student first.");
            return;
        }
        
        System.out.print("\nEnter student name: ");
        String name = scanner.nextLine();
        Student student = findStudent(name);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        double grade = getDoubleInput("Enter grade (0-100): ");
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Must be between 0 and 100.");
            return;
        }
        
        student.addGrade(grade);
        System.out.println("Grade added successfully!");
    }
    
    private void viewStudentDetails() {
        if (students.isEmpty()) {
            System.out.println("\nNo students in the system.");
            return;
        }
        
        System.out.print("\nEnter student name: ");
        String name = scanner.nextLine();
        Student student = findStudent(name);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n" + student.toString());
    }
    
    private void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students in the system.");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("ALL STUDENTS");
        System.out.println("========================================");
        
        for (Student student : students) {
            System.out.println("\n" + student.toString());
            System.out.println("----------------------------------------");
        }
    }
    
    private void displaySummaryReport() {
        if (students.isEmpty()) {
            System.out.println("\nNo students in the system.");
            return;
        }
        
        System.out.println("\n========================================");
        System.out.println("       SUMMARY REPORT");
        System.out.println("========================================");
        System.out.println("Total Students: " + students.size());
        System.out.println("----------------------------------------");
        
        double overallAverage = 0;
        double overallHighest = 0;
        double overallLowest = 100;
        int totalGrades = 0;
        
        System.out.printf("%-20s %-10s %-10s %-10s %-10s\n", 
            "Name", "Grades", "Average", "Highest", "Lowest");
        System.out.println("========================================");
        
        for (Student student : students) {
            if (student.getGradeCount() > 0) {
                double avg = student.calculateAverage();
                double high = student.getHighestGrade();
                double low = student.getLowestGrade();
                
                System.out.printf("%-20s %-10d %-10.2f %-10.2f %-10.2f\n",
                    student.getName(), 
                    student.getGradeCount(),
                    avg, high, low);
                
                overallAverage += avg;
                if (high > overallHighest) overallHighest = high;
                if (low < overallLowest) overallLowest = low;
                totalGrades++;
            }
        }
        
        if (totalGrades > 0) {
            System.out.println("========================================");
            System.out.printf("Class Average: %.2f\n", overallAverage / totalGrades);
            System.out.printf("Highest Grade in Class: %.2f\n", overallHighest);
            System.out.printf("Lowest Grade in Class: %.2f\n", overallLowest);
        }
        System.out.println("========================================");
    }
    
    private Student findStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
    
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Invalid input. " + prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
    
    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Invalid input. " + prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }
    
    public static void main(String[] args) {
        GradeTrackerConsole tracker = new GradeTrackerConsole();
        tracker.run();
    }
}