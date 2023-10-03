import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.Date;

public class Main {
    private static final String STUDENT_FILE = "C:\\Student_Management\\src\\students.txt";
    private static final String FACULTY_FILE = "C:\\Student_Management\\src\\faculties.txt";

    public static void main(String[] args) {
        List<Student> students = loadStudentsFromFIle();
        List<Faculty> faculties = loadFacultiesFromFile();
        List<StudyField> studyFields = Arrays.asList(
                new StudyField("Mechanical Engineering"),
                new StudyField("Software Engineering"),
                new StudyField("Food Technology"),
                new StudyField("Urbanism & Architecture"),
                new StudyField("Veterinary Medicine")
        );

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create a new student");
            System.out.println("2. Display existing students");
            System.out.println("3. Create a new faculty");
            System.out.println("4. Display existing faculties");
            System.out.println("5. Assign a student to a faculty");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter student details:");
                    Student newStudent = Student.inputStudentFromConsole();
                    Faculty studentFaculty = selectFaculty(faculties);
                    if (studentFaculty != null) {
                        newStudent.setFaculty(studentFaculty);
                        students.add(newStudent);
                        saveStudentsToFile(students);
                    } else {
                        System.out.println("Invalid faculty selection");
                    }

                    break;
                case 2:
                    System.out.println("Existing Students:");
                    for (Student existingStudent : students) {
                        System.out.println(existingStudent);
                    }
                    break;
                case 3:
                    System.out.println("Enter faculty details:");
                    System.out.print("Enter faculty name: ");
                    String facultyName = scanner.nextLine();
                    System.out.print("Enter faculty abbreviation: ");
                    String facultyAbbreviation = scanner.nextLine();
                    System.out.print("Select a study field (1-5):");
                    for (int i = 0; i < studyFields.size(); i++) {
                        System.out.println((i + 1) + ". " + studyFields.get(i).getName());
                    }
                    int studyFieldIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume the newline character
                    if (studyFieldIndex >= 0 && studyFieldIndex < studyFields.size()) {
                        Faculty newFaculty = new Faculty(facultyName, facultyAbbreviation, studyFields.get(studyFieldIndex));
                        faculties.add(newFaculty);
                        newFaculty.saveToFile("faculties.txt"); // Save the new faculty to the file
                    } else {
                        System.out.println("Invalid study field selection.");
                    }
                    break;
                case 4:
                    System.out.println("Existing Faculties: \n");
                    for (Faculty existingFaculty : faculties) {
                        System.out.println(existingFaculty);
                    }
                    break;
                case 5:
                    System.out.println("Assign a student to a faculty:");
                    System.out.println("Select a student:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i).getFirstName() + " " + students.get(i).getLastName());
                    }
                    int studentIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume the newline character
                    if (studentIndex >= 0 && studentIndex < students.size()) {
                        System.out.println("Select a faculty:");
                        for (int i = 0; i < faculties.size(); i++) {
                            System.out.println((i + 1) + ". " + faculties.get(i).getName());
                        }
                        int facultyIndex = scanner.nextInt() - 1;
                        scanner.nextLine(); // Consume the newline character
                        if (facultyIndex >= 0 && facultyIndex < faculties.size()) {
                            Student selectedStudent = students.get(studentIndex);
                            Faculty selectedFaculty = faculties.get(facultyIndex);
                            selectedStudent.setFaculty(selectedFaculty);
                            selectedFaculty.addStudent(selectedStudent);
                            System.out.println("Student assigned to faculty.");
                        } else {
                            System.out.println("Invalid faculty selection.");
                        }
                    } else {
                        System.out.println("Invalid student selection.");
                    }
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, 4, 5, or 6.");
            }
        }
    }

    private static List<Student> loadStudentsFromFIle() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String email = parts[2];
                    Date enrollmentDate = Student.parseDate(parts[3]);
                    Date dateOfBirth = Student.parseDate(parts[4]);
                    String studyFieldName = parts[5].trim();
                    StudyField studyField = new StudyField(studyFieldName);
                    Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
                    student.setFaculty(new Faculty("N/A", "N/A", studyField));
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data from file");
        }
        return students;
    }

    private static List<Faculty> loadFacultiesFromFile() {
        List<Faculty> faculties = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FACULTY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String facultyName = parts[0].trim();
                    String facultyAbbreviation = parts[1].trim();
                    String studyFieldName = parts[2].trim();
                    StudyField studyField = new StudyField(studyFieldName);
                    faculties.add(new Faculty(facultyName, facultyAbbreviation, studyField));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading faculty data from file.");
        }
        return faculties;
    }

    private static void saveStudentsToFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Student student : students) {
                String line = String.format("%s,%s,%s,%s,%s,%s\n",
                        student.getFirstName(),
                        student.getLastName(),
                        student.getEmail(),
                        Student.dateToString(student.getEnrollmentDate()),
                        Student.dateToString(student.getDateOfBirth()),
                        student.getFaculty().getStudyField().getName()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error saving student to file");
        }
    }

    private static Faculty selectFaculty(List<Faculty> faculties) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available Faculties");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }

        System.out.print("Enter the number of the faculty (1-" + faculties.size() + "): ");
        int facultyIndex = scanner.nextInt();

        if (facultyIndex >= 1 && facultyIndex <= faculties.size()) {
            return faculties.get(facultyIndex - 1); // Subtract 1 to get the correct index
        } else {
            System.out.println("Invalid faculty selection.");
            return null;
        }
    }
    }