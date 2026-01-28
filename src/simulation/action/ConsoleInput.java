package simulation.action;

import java.util.Scanner;

public class ConsoleInput {

    public static int readNumber(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String input = scanner.nextLine().trim();
            if(input.matches("\\d+")){
                return Integer.parseInt(input);
            }
            System.out.println("Введи число без символов и букв");
        }
    }
}
