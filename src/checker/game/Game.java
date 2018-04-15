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
    public static BoardEvaluation boardeval;

    public Game() {
        board = Board.getInstance();
        boardeval = BoardEvaluation.getInstance();
    }

    public void PlayGame() {

        while (true) {

            // Black's turn
            //************************************************************************************************
            Black.calculateAllpossibleMoves(board);
            if (board.CheckGameDraw(PlayerType.BLACK)) {
                congratulateWinner(PlayerType.WHITE);
                break;
            }

            ArrayList<Move> movesForBlack = Black.allowedMoves;
            Move nextMoveBlack = movesForBlack.get((int) (Math.random() * movesForBlack.size()));

            System.out.println("Moves avaliable are: (BLACK)");
            System.out.println("----------------------------");
            for (Move m : movesForBlack) {
                System.out.println(m.toString());
            }
            System.out.println("Board Evaluation is:" + boardeval.evaluateBoard(board, PlayerType.BLACK));
            System.out.println("----------------------------\n");

            makeMove(nextMoveBlack);
            board.Display();

            //check if black already won
            if (board.CheckGameComplete()) {
                congratulateWinner(PlayerType.BLACK);
                break;
            }
            //************************************************************************************************

            // White's turn
            //************************************************************************************************
            White.calculateAllpossibleMoves(board);
            if (board.CheckGameDraw(PlayerType.WHITE)) {
                congratulateWinner(PlayerType.BLACK);
                break;
            }
            ArrayList<Move> movesForWhite = White.allowedMoves;
            Move nextMoveWhite = movesForWhite.get((int) (Math.random() * movesForWhite.size()));

            System.out.println("Moves avaliable are: (White)");
            System.out.println("----------------------------");
            for (Move m : movesForWhite) {
                System.out.println(m.toString());
            }
            System.out.println("Board Evaluation is:" + boardeval.evaluateBoard(board, PlayerType.WHITE));
            System.out.println("----------------------------\n");

            makeMove(nextMoveWhite);
            board.Display();

            //check if white already won
            if (board.CheckGameComplete()) {
                congratulateWinner(PlayerType.WHITE);
                break;
            }
            //************************************************************************************************
        }

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

    public static void congratulateWinner(PlayerType player) {

        System.out.println("\n\n");
        System.out.println("----------------------------");
        if (player.equals(PlayerType.WHITE)) {
            System.out.println("Congratulation!!!!!!!!!! White has Won.");
        } else {
            System.out.println("Congratulation!!!!!!!!!! Black has Won.");
        }
        System.out.println("----------------------------");
    }
}
