public class Human {
    String FirstName;
    String LastName;
    String FullName;
    String Gender;
    int age;
    String Maried;
    String[] IDStrings = new String[5];
    public Human(String FN, String LN, String GN, int AG, boolean MR){
        FirstName = FN;
        LastName = LN;
        FullName = FirstName + " " + LastName;
        Gender = GN;
        age = AG;
        if (MR == true){
            Maried = "Yes";
        } else {
            Maried = "No";
        }
        IDStrings[0] = "Name: " + FullName;
        IDStrings[1] = "Year Of Birth: " + (2023 - age);
        IDStrings[2] = "Is Maried: " + Maried;
        IDStrings[3] = "Gender: " + Gender;
        IDStrings[4] = "ID Number " + hashCode();
    }
    public static void main(String[] args){

        Human Human1 = new Human("Jane", "Doe", "Female", 32, false);
        for (int i = 0; i < Human1.IDStrings.length - 1; i++) {
            for(int j = 0; j < Human1.IDStrings.length - i - 1; j++) {
                if(Human1.IDStrings[j + 1].length() > Human1.IDStrings[j].length()) {
                    String swap = Human1.IDStrings[j];
                    Human1.IDStrings[j] = Human1.IDStrings[j + 1];
                    Human1.IDStrings[j + 1] = swap;
                }
            }
        }
        for (int i = 0; i < Human1.IDStrings.length; i++){
            while (Human1.IDStrings[i].length() < Human1.IDStrings[0].length()){
                if (Human1.IDStrings[i].length() < Human1.IDStrings[0].length()){
                    Human1.IDStrings[i] += " ";
                } else {
                    break;
                }
            }
        }
        System.out.print("+");
        for (int i = 0; i < Human1.IDStrings[0].length()+2; i++){
            System.out.print("-");
        }
        System.out.println("+");
        for (int i = 0; i < Human1.IDStrings.length; i++){
            System.out.println("| " + Human1.IDStrings[i] + " |");
        }
        System.out.print("+");
        for (int i = 0; i < Human1.IDStrings[0].length()+2; i++){
            System.out.print("-");
        }
        System.out.println("+");

    }
}
// I am tried to do simple variation of ID printing in terminal
// In class Human exists constructor after that Strings are sorted and if it necesory made...
//longer, because with out that ID is not rectangle
//also I take hash code like ID number, because for every class hash number is unique like ID numbers.
//P.S. I dont how it works on pc, because my java IDE works very bad and I am coding in online IDE