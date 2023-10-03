import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
    private StudyField studyField;
    private List<Student> students;

    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "Faculty: \n" +
                "name='" + name + '\'' +
                ", abbreviation= '" + abbreviation + '\'' +
                ", studyField= '" + studyField.getName() + '\'' +
                ", students=" + students.size() + "\n";
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.format("%s,%s,%s%n", name, abbreviation, studyField.getName()));
        } catch (IOException e) {
            System.err.print("Error saving faculty to file");
        }
    }

    public static List<Faculty> loadFromFile(String filePath, List<StudyField> studyFields) {
        List<Faculty> faculties = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String facultyName = parts[0].trim();
                    String facultyAbbreviation = parts[1].trim();
                    String studyFieldName = parts[2].trim();
                    StudyField studyField = findStudyFieldByName(studyFields, studyFieldName);
                    if (studyField != null) {
                        Faculty faculty = new Faculty(facultyName, facultyAbbreviation, studyField);
                        faculties.add(faculty);
                    } else {
                        System.err.println("Invalid study field found in faculty data");
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println("Error reading faculty data from file");
        }
        return faculties;
    }

    private static StudyField findStudyFieldByName(List<StudyField> studyFields, String name) {
        for (StudyField studyField : studyFields) {
            if(studyField.getName().equalsIgnoreCase(name)) {
                return studyField;
            }
        }
        return null;
    }

}
