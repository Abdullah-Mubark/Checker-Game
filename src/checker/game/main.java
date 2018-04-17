package checker.game;

import java.util.Scanner;

public class main {
    public static void main(String args[]){        
        Game game = new Game();
        System.out.println("Pick The diffuculty:\n0 for easy\n1 for diffucult");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
         while (!input.matches("^\\d+$") && input.matches("0") && input.matches("1")) {
            System.out.println("Wrong input");
            input = s.nextLine();
        }
        Game.board.Display();
        game.PlayGame(1);
    }    
}