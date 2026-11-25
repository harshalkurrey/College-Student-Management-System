import java.io.*;
import java.util.*;

// Model class representing a single student record
class Student {
    private String id;
    private String name;
    private String course;
    private double cgpa;

    public Student(String id, String name, String course, double cgpa) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.cgpa = cgpa;
    }

    public String getId()     { return id; }
    public String getName()   { return name; }
    public String getCourse() { return course; }
    public double getCgpa()   { return cgpa; }

    // Console display format: ID | Name | Course | CGPA
    @Override
    public String toString() {
        return id + " | " + name + " | " + course + " | " + cgpa;
    }

    // File storage format: ID|Name|Course|CGPA
    public String toFileString() {
        return id + "|" + name + "|" + course + "|" + cgpa;
    }
}

public class Main {

    private static final String FILE_NAME = "students.txt";
    private static final int FIRST_ID = 101; // Starting numeric ID -> S101

    // ArrayList preserves order; HashMap enables fast lookup by Student ID
    private static ArrayList<Student> students = new ArrayList<>();
    private static HashMap<String, Student> studentMap = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile(); // Load saved records on startup

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();
            System.out.println(); // Gap after option selection

            switch (choice) {
                case "1": addStudent();    break;
                case "2": viewAll();       break;
                case "3": searchById();    break;
                case "4": sortByCgpa();    break;
                case "5": deleteStudent(); break;
                case "6": searchByName();  break;
                case "7":
                    saveToFile(); // Persist records on exit
                    System.out.println("Data saved to " + FILE_NAME + ". Goodbye!\n");
                    return;
                default:
                    System.out.println("Invalid option. Please select 1-7.\n");
            }
        }
    }

    // Display console menu options (1-7) with gap before 1 and after 7
    private static void showMenu() {
        System.out.println("\n===== College Student Management System =====\n");
        System.out.println("1. Add a student");
        System.out.println("2. View all students");
        System.out.println("3. Search student by ID");
        System.out.println("4. Sort students by CGPA");
        System.out.println("5. Delete a student");
        System.out.println("6. Search student by name");
        System.out.println("7. Save and Exit\n");
        System.out.print("Select an option: ");
    }

    // Add a new student (Name, Course, CGPA together with gap above & below)
    private static void addStudent() {
        System.out.println();
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("\nName cannot be empty.\n");
            return;
        }

        System.out.print("Course: ");
        String course = sc.nextLine().trim();
        if (course.isEmpty()) {
            System.out.println("\nCourse cannot be empty.\n");
            return;
        }

        System.out.print("CGPA: ");
        double cgpa;
        try {
            cgpa = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid CGPA. Please enter a number.\n");
            return;
        }

        // Validate CGPA range [0.0, 10.0] and reject NaN/Infinity
        if (Double.isNaN(cgpa) || Double.isInfinite(cgpa) || cgpa < 0 || cgpa > 10) {
            System.out.println("\nInvalid CGPA. It must be between 0 and 10.\n");
            return;
        }

        String id = generateId();
        if (studentMap.containsKey(id)) {
            System.out.println("\nDuplicate ID " + id + ". Student not added.\n");
            return;
        }

        Student s = new Student(id, name, course, cgpa);
        students.add(s);
        studentMap.put(id, s);
        System.out.println("\nStudent added. ID: " + s + "\n");
    }

    // Generate next ID as (max existing ID + 1) so deleted IDs are never reused
    private static String generateId() {
        int max = FIRST_ID - 1;
        for (Student s : students) {
            int num = Integer.parseInt(s.getId().substring(1));
            if (num > max) max = num;
        }
        return "S" + (max + 1);
    }

    // Display all students in clean spaced columns (without |)
    private static void viewAll() {
        System.out.println();
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }
        System.out.println("All students (" + students.size() + "):\n");
        System.out.printf("%-8s %-22s %-18s %-5s%n", "ID", "Name", "Course", "CGPA");
        for (Student s : students) {
            System.out.printf("%-8s %-22s %-18s %-5.1f%n",
                    s.getId(), s.getName(), s.getCourse(), s.getCgpa());
        }
        System.out.println();
    }

    // Search student by ID using HashMap (O(1) average lookup)
    private static void searchById() {
        System.out.println();
        System.out.print("Student ID: ");
        String id = sc.nextLine().trim().toUpperCase();
        Student s = studentMap.get(id);
        if (s == null) {
            System.out.println("\nStudent ID " + id + " not found.\n");
        } else {
            System.out.println("\nFound: " + s + "\n");
        }
    }

    // Search student by name using partial, case-insensitive Linear Search
    private static void searchByName() {
        System.out.println();
        System.out.print("Student name: ");
        String key = sc.nextLine().trim().toLowerCase();
        if (key.isEmpty()) {
            System.out.println("\nSearch name cannot be empty.\n");
            return;
        }
        boolean found = false;
        System.out.println();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(key)) {
                System.out.println("Found: " + s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No student found with name \"" + key + "\".");
        }
        System.out.println();
    }

    // Sort a copy of the list by CGPA in descending order using Comparator
    private static void sortByCgpa() {
        System.out.println();
        if (students.isEmpty()) {
            System.out.println("No students found.\n");
            return;
        }
        ArrayList<Student> sorted = new ArrayList<>(students);
        sorted.sort(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return Double.compare(b.getCgpa(), a.getCgpa());
            }
        });

        System.out.println("Sorted list (" + sorted.size() + " student"
                + (sorted.size() == 1 ? "" : "s") + "):\n");
        int i = 1;
        for (Student s : sorted) {
            System.out.println(i++ + ". " + s);
        }
        System.out.println();
    }

    // Remove student from both ArrayList and HashMap
    private static void deleteStudent() {
        System.out.println();
        System.out.print("Student ID: ");
        String id = sc.nextLine().trim().toUpperCase();
        Student s = studentMap.get(id);
        if (s == null) {
            System.out.println("\nStudent ID " + id + " not found.\n");
            return;
        }
        students.remove(s);
        studentMap.remove(id);
        System.out.println("\nStudent " + id + " deleted.\n");
    }

    // Load valid, non-duplicate records from students.txt at startup
    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split("\\|");
                if (p.length != 4) continue; // Skip malformed lines
                try {
                    String id = p[0].trim();
                    String name = p[1].trim();
                    String course = p[2].trim();
                    double cgpa = Double.parseDouble(p[3].trim());

                    // Skip blank fields, invalid CGPA, or duplicate IDs
                    if (id.isEmpty() || name.isEmpty() || course.isEmpty()
                            || Double.isNaN(cgpa) || Double.isInfinite(cgpa)
                            || cgpa < 0 || cgpa > 10 || studentMap.containsKey(id)) {
                        continue;
                    }

                    Student s = new Student(id, name, course, cgpa);
                    students.add(s);
                    studentMap.put(id, s);
                } catch (NumberFormatException ignored) {
                    // Skip lines with non-numeric CGPA
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Write all student records to students.txt in pipe-separated format
    private static void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}