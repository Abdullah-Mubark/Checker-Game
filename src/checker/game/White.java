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
public class White {
    
    public static ArrayList<Move> allowedMoves;

    White() {
        allowedMoves = new ArrayList<>();
    }

    public static void calculateAllpossibleMoves(Board board) {
        allowedMoves = getForcedMoves(board);
        if (allowedMoves.isEmpty()) {
            allowedMoves = getNonForcedMoves(board);
        }
    }

    private static ArrayList<Move> getForcedMoves(Board board) {
        Checker[][] gameBoard = board.getBoard();
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                // check if it is a black 
                if ((gameBoard[i][j] == null) || (!Board.CheckerIsWhite(gameBoard[i][j]))) {
                    continue;
                }
                Move forwardLeftCapture = getCaptureForwardLeft(gameBoard[i][j].getPosition(), board);
                Move forwardRightCapture = getCaptureForwardRight(gameBoard[i][j].getPosition(), board);

                if (forwardRightCapture != null) {
                    moves.add(forwardRightCapture);
                }
                if (forwardLeftCapture != null) {
                    moves.add(forwardLeftCapture);
                }

                // if checker is king
                if (gameBoard[i][j].getType() == CheckerType.WHITE_KING) {

                    Move backwardLeftCapture = getCaptureBackwardLeft(gameBoard[i][j].getPosition(), board);
                    Move backwardRightCapture = getCaptureBackwardRight(gameBoard[i][j].getPosition(), board);

                    if (backwardLeftCapture != null) {
                        moves.add(backwardLeftCapture);
                    }
                    if (backwardRightCapture != null) {
                        moves.add(backwardRightCapture);
                    }
                }
            }
        }
        return moves;
    }

    private static ArrayList<Move> getNonForcedMoves(Board board) {
        Checker[][] gameBoard = board.getBoard();
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                // check if it is a black 
                if ((gameBoard[i][j] == null) || (!Board.CheckerIsWhite(gameBoard[i][j]))) {
                    continue;
                }

                Move forwardLeftMove = getForwardLeft(gameBoard[i][j].getPosition(), board);
                Move forwardRightMoves = getForwardRight(gameBoard[i][j].getPosition(), board);

                if (forwardRightMoves != null) {
                    moves.add(forwardRightMoves);
                }
                if (forwardLeftMove != null) {
                    moves.add(forwardLeftMove);
                }

                // if checker is king
                if (gameBoard[i][j].getType() == CheckerType.WHITE_KING) {

                    Move backwardLeftMoves = getBackwardLeft(gameBoard[i][j].getPosition(), board);
                    Move backwardRightMoves = getBackwardRight(gameBoard[i][j].getPosition(), board);

                    if (backwardLeftMoves != null) {
                        moves.add(backwardLeftMoves);
                    }
                    if (backwardRightMoves != null) {
                        moves.add(backwardRightMoves);
                    }
                }
            }
        }
        return moves;

    }

    private static Move getForwardLeft(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() < gameBoard.length - 1 && position.getX() < gameBoard.length - 1) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() + 1][position.getY() + 1] == null) {
                //Empty cell yay!
                //return a new move with the new location
                return new Move(position, new Position(position.getX() + 1, position.getY() + 1));
            }
        }
        return null;
    }

    private static Move getForwardRight(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() >= 1 && position.getX() < gameBoard.length - 1) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() + 1][position.getY() - 1] == null) {
                //Empty cell yay!
                //return a new move with the new location
                return new Move(position, new Position(position.getX() + 1, position.getY() - 1));
            }
        }
        return null;
    }

    private static Move getBackwardRight(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() >= 1 && position.getX() >= 1) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() - 1][position.getY() - 1] == null) {
                //Empty cell yay!
                //return a new move with the new location
                return new Move(position, new Position(position.getX() - 1, position.getY() - 1));
            }
        }
        return null;
    }

    private static Move getBackwardLeft(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() < gameBoard.length - 1 && position.getX() >= 1) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() - 1][position.getY() + 1] == null) {
                //Empty cell yay!
                //return a new move with the new location
                return new Move(position, new Position(position.getX() - 1, position.getY() + 1));
            }
        }
        return null;
    }

    private static Move getCaptureForwardLeft(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() < gameBoard.length - 2 && position.getX() < board.getBoard().length - 2) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() + 2][position.getY() + 2] == null
                    && gameBoard[position.getX() + 1][position.getY() + 1] != null) {
                //Empty cell yay!
                //check if an enemy checker is in the way
                if (Board.CheckerIsBlack(gameBoard[position.getX() + 1][position.getY() + 1])) {
                    return new Move(position, new Position(position.getX() + 2, position.getY() + 2), true);
                }
            }

        }
        return null;
    }

    private static Move getCaptureForwardRight(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() >= 2 && position.getX() < board.getBoard().length - 2) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() + 2][position.getY() - 2] == null
                    && gameBoard[position.getX() + 1][position.getY() - 1] != null) {
                //Empty cell yay!
                //check if enemy checker is in the way
                if (Board.CheckerIsBlack(gameBoard[position.getX() + 1][position.getY() - 1])) {
                    return new Move(position, new Position(position.getX() + 2, position.getY() - 2), true);
                }

            }

        }
        return null;
    }

    private static Move getCaptureBackwardLeft(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() < gameBoard.length - 2 && position.getX() >= 2) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() - 2][position.getY() + 2] == null
                    && gameBoard[position.getX() - 1][position.getY() + 1] != null) {
                //Empty cell yay!
                //check if an enemy checker is in the way
                if (Board.CheckerIsBlack(gameBoard[position.getX() - 1][position.getY() + 1])) {
                    return new Move(position, new Position(position.getX() - 2, position.getY() + 2), true);
                }
            }

        }
        return null;
    }

    private static Move getCaptureBackwardRight(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() >= 2 && position.getX() >= 2) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() - 2][position.getY() - 2] == null
                    && gameBoard[position.getX() - 1][position.getY() - 1] != null) {
                //Empty cell yay!
                //check if enemy checker is in the way
                if (Board.CheckerIsBlack(gameBoard[position.getX() - 1][position.getY() - 1])) {
                    return new Move(position, new Position(position.getX() - 2, position.getY() - 2), true);
                }

            }

        }
        return null;
    }
}
