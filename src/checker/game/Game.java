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
        board.getBoard()[1][0] = null;
        board.getBoard()[1][4] = null;
        board.getBoard()[0][1] = null;
        board.getBoard()[0][3] = null;
        board.getBoard()[0][5] = null;
        board.getBoard()[0][7] = null;
        board.getBoard()[2][1] = null;
        board.getBoard()[2][7] = null;
        board.setWhiteCheckers(board.getWhiteCheckers() - 8);
        while (!Game.board.CheckGameComplete()) {
            ArrayList<Move> nonForcedMoves = Black.getNonForcedMoves(board);
            ArrayList<Move> ForcedMoves = Black.getForcedMoves(board);
            Move nextMove;
            if (ForcedMoves.size() > 0) {
                System.out.println("\n\nELIMINATE!!!!\n");
                nextMove = ForcedMoves.get((int) Math.random() * ForcedMoves.size());
            } else {
                nextMove = nonForcedMoves.get((int) Math.random() * nonForcedMoves.size());
            }
            //System.out.println(nextMove.toString());
            makeMove(nextMove);
            board.Display();
            System.out.println(board.getWhiteCheckers());
            //board.Display();
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
//        ArrayList<Move> moves = Black.getNonForcedMoves(board);
//        System.out.println(moves.size());
//        ArrayList<Move> moves2 = Black.getForcedMoves(board);
//        System.out.println(moves2.size());
//        
//        for (Move move : moves) {
//            System.out.println(move.toString());     
//        }
//        System.out.println("");
//        for (Move move2 : moves2) {
//            System.out.println(move2.toString());
//        }
//
//        makeMove(moves.get(0));
////        makeMove(moves.get(2));
//        board.Display();
    }

    // make sure a move is valid
    private void makeMove(Move move) {
        int x1 = move.getIntialpos().getX();
        int y1 = move.getIntialpos().getY();
        int x2 = move.getFinalpos().getX();
        int y2 = move.getFinalpos().getY();
        CheckerType ct = board.getBoard()[x1][y1].getType();

        if (!move.getIsCapture()) {
            board.getBoard()[x2][y2] = new Checker(ct, new Position(x2, y2));
            board.getBoard()[x1][y1] = null;
        } else {
            board.getBoard()[x2][y2] = new Checker(ct, new Position(x2, y2));
            board.getBoard()[x1][y1] = null;

            MoveDir dir = x2 < x1 ? (y2 < y1 ? MoveDir.forwardLeft : MoveDir.forwardRight)
                    : (y2 < y1 ? MoveDir.backwardLeft : MoveDir.backwardRight);

            // Removing Black Piece from the board
            switch (dir) {
                case forwardRight:
                    board.getBoard()[x1 - 1][y1 + 1] = null;
                    break;
                case forwardLeft:
                    board.getBoard()[x1 - 1][y1 - 1] = null;
                    break;
                case backwardRight:
                    board.getBoard()[x1 + 1][y1 + 1] = null;
                    break;
                case backwardLeft:
                    board.getBoard()[x1 + 1][y1 - 1] = null;
                    break;
            }
            
            board.setWhiteCheckers(board.getWhiteCheckers() - 1);

        }

        //promote to King
        if (ct == CheckerType.BLACK_REGULAR && x2 == 0) {
            board.getBoard()[x2][y2].setType(CheckerType.BLACK_KING);
        } else if (ct == CheckerType.WHITE_REGULAR && x2 == Board.rows - 1) {
            board.getBoard()[x2][y2].setType(CheckerType.WHITE_KING);
        }
    }
}
