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
public class BoardEvaluation {

    public final int POINT_WON = 100000;
    public final int POINT_KING = 2000;
    public final int POINT_NORMAL = 1000;
    public final int POINT_NORMAL_MOVES = 40;
    public final int POINT_ATTACK_MOVES = 80;
    public final int POINT_CENTRAL_PIECE = 100;
    public final int POINT_END_PIECE = 50;

    public static BoardEvaluation getInstance() {
        return new BoardEvaluation();
    }

    public int evaluateBoard(Board board, PlayerType player) {
        int boardValue = 0;

        if (player == PlayerType.BLACK) {
            boardValue = evaluateValueofBoardForBlack(board);
        } else {
            boardValue = evaluateValueofBoardForWhite(board);
        }

        return boardValue;
    }

    private int evaluateValueofBoardForWhite(Board board) {

        int wValue = 0;
        if (board.isWhiteWinner()) {
            wValue += POINT_WON;
            return wValue;
        } else {
            wValue = WhiteBlackPiecesDifferencePoints(board);
            wValue += BoardAttacksPoints(board);
            wValue /= board.getBlackCheckers();

        }

        return wValue;
    }

    private int evaluateValueofBoardForBlack(Board board) {

        int bValue = 0;
        if (board.isBlackWinner()) {
            bValue -= POINT_WON;
            return bValue;
        } else {
            bValue = WhiteBlackPiecesDifferencePoints(board);
            bValue -= BoardAttacksPoints(board);
            bValue /= board.getWhiteCheckers();
        }

        return bValue;
    }

    private int WhiteBlackPiecesDifferencePoints(Board board) {

        int value = 0;
        // Scan the board
        Checker[][] gameBoard = board.getBoard();
        for (int i = 0; i < gameBoard[0].length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if ((gameBoard[i][j] == null)) {
                    continue;
                }
                //Points Checkers Count
                Checker c = gameBoard[i][j];
                int row = c.getPosition().getX();
                int col = c.getPosition().getY();
                switch (c.getType()) {
                    case WHITE_REGULAR:
                        value += POINT_NORMAL;
                        break;
                    case WHITE_KING:
                        value += POINT_KING;
                        break;
                    case BLACK_REGULAR:
                        value -= POINT_NORMAL;
                        break;
                    case BLACK_KING:
                        value -= POINT_KING;
                        break;
                }

                // Points for Central Checkers 
                if ((board.CheckerIsWhite(c))
                        && (((row == 3 && col == 3) || (row == 3 && col == 5)))) {
                    value += POINT_CENTRAL_PIECE;
                }
                if ((board.CheckerIsBlack(c))
                        && (((row == 4 && col == 2) || (row == 4 && col == 4)))) {
                    value -= POINT_CENTRAL_PIECE;
                }

                // Points for End Checkers 
                if (((c.getType() == CheckerType.WHITE_REGULAR))
                        && (((row == 0 && col == 2) || (row == 0 && col == 4) || (row == 0 && col == 6)))) {
                    value += POINT_END_PIECE;
                }
                if ((c.getType() == CheckerType.BLACK_REGULAR)
                        && (((row == 1 && col == 7) || (row == 3 && col == 7) || (row == 5 && col == 7)))) {
                    value -= POINT_END_PIECE;
                }

            }
        }

        return value;
    }

    private int BoardAttacksPoints(Board board) {

        int value = 0;
        Black black = new Black();
        black.calculateAllpossibleMoves(board);
        ArrayList<Move> blackMoves = black.allowedMoves;

        White white = new White();
        white.calculateAllpossibleMoves(board);
        ArrayList<Move> whiteMoves = white.allowedMoves;

        // calculate moves points for black
        for (Move Bmove : blackMoves) {
            if (Bmove.getIsCapture()) {
                value -= POINT_ATTACK_MOVES;
            } else {
                value -= POINT_NORMAL_MOVES;
            }

        }
        // calculate moves points for white
        for (Move Wmove : whiteMoves) {
            if (Wmove.getIsCapture()) {
                value += POINT_ATTACK_MOVES;
            } else {
                value += POINT_NORMAL_MOVES;
            }
        }
        return value;
    }
}
