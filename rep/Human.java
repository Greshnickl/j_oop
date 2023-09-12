public class Human {
    String firstName;
    String lastName;
    String fullName;
    String gender;
    int age;
    String maried;
    String[] idStrings = new String[5];

    public Human(String firstName, String lastName, String gender, int age, boolean mr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.gender = gender;
        this.age = age;
        if (mr == true) {
            maried = "Yes";
        } else {
            maried = "No";
        }
        idStrings[0] = "Name: " + fullName;
        idStrings[1] = "Year Of Birth: " + (2023 - age);
        idStrings[2] = "Is Maried: " + maried;
        idStrings[3] = "Gender: " + gender;
        idStrings[4] = "ID Number " + hashCode();
    }
}