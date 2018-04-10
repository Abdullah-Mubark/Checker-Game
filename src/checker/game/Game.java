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

//        board.getBoard()[1][0] = null;
//        board.getBoard()[0][1] = null;
//        board.getBoard()[0][3] = null;
//        board.getBoard()[0][5] = null;
//        board.getBoard()[0][7] = null;
//        board.getBoard()[2][7] = null;
//        board.setWhiteCheckers(board.getWhiteCheckers() - 6);
        while (!Game.board.CheckGameComplete()) {

            // Black's turn
            Black.calculateAllpossibleMoves(board);
            ArrayList<Move> movesForBlack = Black.allowedMoves;
            Move nextMoveBlack = movesForBlack.get((int) (Math.random() * movesForBlack.size()));
            System.out.println("Moves avaliable are: (BLACK)");
            System.out.println("----------------------------");
            for (Move m : movesForBlack) {
                System.out.println(m.toString());
            }
            System.out.println("----------------------------");
            makeMove(nextMoveBlack);
            board.Display();

            //check if black already won
            if (Game.board.CheckGameComplete()) {
                break;
            }

            // White's turn
            White.calculateAllpossibleMoves(board);
            ArrayList<Move> movesForWhite = White.allowedMoves;
            Move nextMoveWhite = movesForWhite.get((int) (Math.random() * movesForWhite.size()));
            System.out.println("Moves avaliable are: (White)");
            System.out.println("----------------------------");
            for (Move m : movesForWhite) {
                System.out.println(m.toString());
            }
            System.out.println("----------------------------");
            makeMove(nextMoveWhite);
            board.Display();

            //board.Display();
//            if(Game.board.CheckGameDraw(Player.white)){
//                break;
//            }
//
//           
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

        board.getBoard()[x2][y2] = new Checker(ct, new Position(x2, y2));
        board.getBoard()[x1][y1] = null;

        if (move.getIsCapture()) {
            MoveDir dir = x2 < x1 ? (y2 < y1 ? MoveDir.forwardLeft : MoveDir.forwardRight)
                    : (y2 < y1 ? MoveDir.backwardLeft : MoveDir.backwardRight);

            // Removing Piece from the board
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
            if (ct == CheckerType.BLACK_KING || ct == CheckerType.BLACK_REGULAR) {
                board.setWhiteCheckers(board.getWhiteCheckers() - 1);
            } else {
                board.setBlackCheckers(board.getBlackCheckers() - 1);
            }
        }

        //promote to King
        if (ct == CheckerType.BLACK_REGULAR && x2 == 0) {
            board.getBoard()[x2][y2].setType(CheckerType.BLACK_KING);
        } else if (ct == CheckerType.WHITE_REGULAR && x2 == Board.rows - 1) {
            board.getBoard()[x2][y2].setType(CheckerType.WHITE_KING);
        }
    }
}
