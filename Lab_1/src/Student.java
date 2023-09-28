import java.util.Date;
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private Date enrolmentDate;
    private Date dateOfBirth;
    Student(String firstName, String lastName, String email, Date enrolmentDate, Date dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrolmentDate = enrolmentDate;
        this.dateOfBirth = dateOfBirth;
    }
    //
    //gets
    //
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public Date getEnrolmentDate(){
        return enrolmentDate;
    }
    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    //
    //sets
    //
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setEnrolmentDate(Date enrolmentDate){
        this.enrolmentDate = enrolmentDate;
    }
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
}
