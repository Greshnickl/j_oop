import java.util.Date;
public class Student {
    String firstName;
    String lastName;
    String email;
    Date enrolmentDate;
    Date dateOfBirth;
    Student(String firstName, String lastName, String email, Date enrolmentDate, Date dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrolmentDate = enrolmentDate;
        this.dateOfBirth = dateOfBirth;
    }
}
