public class Main {
    public static void main(String[] args){

        Human human1 = new Human("Jane", "Doe", "Female", 32, false);

        for (int i = 0; i < human1.idStrings.length - 1; i++) {
            for(int j = 0; j < human1.idStrings.length - i - 1; j++) {
                if(human1.idStrings[j + 1].length() > human1.idStrings[j].length()) {
                    String swap = human1.idStrings[j];
                    human1.idStrings[j] = human1.idStrings[j + 1];
                    human1.idStrings[j + 1] = swap;
                }
            }
        }
        for (int i = 0; i < human1.idStrings.length; i++){
            while (human1.idStrings[i].length() < human1.idStrings[0].length()){
                if (human1.idStrings[i].length() < human1.idStrings[0].length()){
                    human1.idStrings[i] += " ";
                } else {
                    break;
                }
            }
        }
        System.out.print("+");
        for (int i = 0; i < human1.idStrings[0].length()+2; i++){
            System.out.print("-");
        }
        System.out.println("+");
        for (int i = 0; i < human1.idStrings.length; i++){
            System.out.println("| " + human1.idStrings[i] + " |");
        }
        System.out.print("+");
        for (int i = 0; i < human1.idStrings[0].length()+2; i++){
            System.out.print("-");
        }
        System.out.println("+");

    }
}