/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

/**
 *
 * @author Abdullah
 */
public class Game {

    public static Board board;

    public Game() {
        board = new Board();
    }

    public void PlayGame() {
        while (!Game.board.CheckGameComplete()) {
//            if(Game.board.CheckGameDraw(Player.white)){
//                break;
//            }
//            
//            White.Move();
//            if(Game.board.CheckGameComplete()){
//                UserInteractions.DisplayGreetings(Player.white);
//                Game.board.Display();
//                break;
//            }
//
//            if(Game.board.CheckGameDraw(Player.black)){
//                break;
//            }
//            
//            Game.board.Display();
//            
//            Black.Move();
//            if(Game.board.CheckGameComplete()){
//                UserInteractions.DisplayGreetings(Player.black);                
//                Game.board.Display();
//                break;
//            }
//
        }
    }
}
