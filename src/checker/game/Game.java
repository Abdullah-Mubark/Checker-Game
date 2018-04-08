/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

import java.util.ArrayList;

/**
 *
 * @author Abdullah
 */
public class Game {

    public static Board board;

    public Game() {
        board = Board.getInstance();
    }

    public void PlayGame() {
        while (!Game.board.CheckGameComplete()) {
            break;
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

        }
        ArrayList<Move> moves = Black.getMoves(board);
        System.out.println(moves.size());
        for(Move move: moves){
            System.out.println(move.toString());
        }

        makeMove(new Move(new Position(5, 2), new Position(4 ,3)));
        board.Display();
    }

    // make sure a move is valid
    private void makeMove(Move move){
        board.getBoard()[move.getIntialpos().getX()][move.getIntialpos().getY()] = null;
        board.getBoard()[move.getFinalpos().getX()][move.getFinalpos().getY()] = new Checker(CheckerType.BLACK_REGULAR, move.getFinalpos());
    }
}
