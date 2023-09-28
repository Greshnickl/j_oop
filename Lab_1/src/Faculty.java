import java.util.List;
public class Faculty {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;
    Faculty(String name, String abbreviation, List<Student> students, StudyField studyField){
        this.name = name;
        this.abbreviation = abbreviation;
        this.students = students;
        this.studyField = studyField;
    }
    //
    // gets
    //
    public String getName(){
        return name;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
    public List<Student> getStudents(){
        return students;
    }
    public StudyField getStudyField(){
        return studyField;
    }
    //
    // sets
    //
    public void setName(String name){
        this.name = name;
    }
    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public void setStudents(List<Student> students){
        this.students = students;
    }
    public void setStudyField(StudyField studyField){
        this.studyField = studyField;
    }
}
