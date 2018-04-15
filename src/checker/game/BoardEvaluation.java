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
    private static BoardEvaluation instance;
    public final int POINT_WON = 100000;
    public final int POINT_KING = 2000;
    public final int POINT_NORMAL = 1000;
    public final int POINT_NORMAL_ATTACK = 50;
    public final int POINT_KILL_ATTACK = 100;
    
    public static BoardEvaluation getInstance() {
        if (instance == null) {
            return new BoardEvaluation();
        } else {
            return instance;
        }
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
                Checker c = gameBoard[i][j];
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

            }
        }

        return value;
    }

    private int BoardAttacksPoints(Board board) {
        
        int value = 0;
        ArrayList<Move> blackMoves = Black.allowedMoves;
        ArrayList<Move> whiteMoves = Black.allowedMoves;
        // calculate moves points for black
        for (Move Bmove : blackMoves) {
            if (Bmove.getIsCapture()) {
                value -= POINT_KILL_ATTACK;
            } else {
                value -= POINT_NORMAL_ATTACK;
            }
            
        }
        // calculate moves points for white
        for (Move Wmove : whiteMoves) {
            if (Wmove.getIsCapture()) {
                value += POINT_KILL_ATTACK;
            } else {
                value += POINT_NORMAL_ATTACK;
            }
        }
        return value;
    }
}
