import java.util.List;
public class Faculty {
    String name;
    String abbreviation;
    List<Student> students;
    StudyField studyField;
    Faculty(String name, String abbreviation, List<Student> students, StudyField studyField){
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = students;
        this.studyField = studyField;

    }
}
