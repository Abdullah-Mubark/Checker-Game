package checker.game;

import java.util.ArrayList;

public class Black {

    ArrayList<Move> allowedMoves;

    Black() {
        allowedMoves = new ArrayList<>();
    }

    public static ArrayList<Move> getForcedMoves(Board board) {
        Checker[][] gameBoard = board.getBoard();
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] != null
                        && (gameBoard[i][j].getType() == CheckerType.BLACK_REGULAR || gameBoard[i][j].getType() == CheckerType.BLACK_KING)) {
                    Move forwardLeftCapture = getCaptureForwardLeft(gameBoard[i][j].getPosition(), board);
                    Move forwardRightCapture = getCaptureForwardRight(gameBoard[i][j].getPosition(), board);
                    if (forwardRightCapture != null) {
                        moves.add(forwardRightCapture);
                    }
                    if (forwardLeftCapture != null) {
                        moves.add(forwardLeftCapture);
                    }
                }
            }
        }
        return moves;
    }

    public static ArrayList<Move> getNonForcedMoves(Board board) {
        Checker[][] gameBoard = board.getBoard();
        ArrayList<Move> moves = new ArrayList<>();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] != null
                        && (gameBoard[i][j].getType() == CheckerType.BLACK_REGULAR || gameBoard[i][j].getType() == CheckerType.BLACK_KING)) {
                    Move forwardLeftMove = getForwardLeft(gameBoard[i][j].getPosition(), board);
                    Move forwardRightMoves = getForwardRight(gameBoard[i][j].getPosition(), board);
                    if (forwardRightMoves != null) {
                        moves.add(forwardRightMoves);
                    }
                    if (forwardLeftMove != null) {
                        moves.add(forwardLeftMove);
                    }
                }
            }
        }
        return moves;
    }

    private static Move getForwardLeft(Position position, Board board) {
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

    private static Move getForwardRight(Position position, Board board) {
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
        if (position.getY() >= 2 && position.getX() >= 2) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() - 2][position.getY() - 2] == null
                    && gameBoard[position.getX() - 1][position.getY() - 1] != null) {
                //Empty cell yay!
                //check if enemy checker is in the way
                if (((gameBoard[position.getX() - 1][position.getY() - 1].getType() == CheckerType.WHITE_REGULAR)
                        || (gameBoard[position.getX() - 1][position.getY() - 1].getType() == CheckerType.WHITE_KING))) {
                    return new Move(position, new Position(position.getX() - 2, position.getY() - 2), true);
                }

            }

        }
        return null;
    }

    private static Move getCaptureForwardRight(Position position, Board board) {
        Checker[][] gameBoard = board.getBoard();
        if (position.getY() < gameBoard.length - 2 && position.getX() >= 2) /*Valid Movement*/ {
            //check if next cell is empty
            if (gameBoard[position.getX() - 2][position.getY() + 2] == null
                    && gameBoard[position.getX() - 1][position.getY() + 1] != null) {
                //Empty cell yay!
                //check if an enemy checker is in the way
                if (((gameBoard[position.getX() - 1][position.getY() + 1].getType() == CheckerType.WHITE_REGULAR)
                        || (gameBoard[position.getX() - 1][position.getY() + 1].getType() == CheckerType.WHITE_KING))) {
                    return new Move(position, new Position(position.getX() - 2, position.getY() + 2), true);
                }
            }

        }
        return null;
    }

}
