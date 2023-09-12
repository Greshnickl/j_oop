public class Main {
    String FirstName = "Undefined";
    String LastName = "Undefined";
    String FullName = FirstName + " " + LastName;
    int age = 0;
    boolean Maried = false;
    public static void main(String[] args) {
        Main myObj = new Main();

        System.out.println(myObj.FullName);
    }
}