package checker.game;

import java.util.ArrayList;

public final class Black {
    ArrayList<Move> moves;

    Black(){
        moves = new ArrayList<>();
    }

    public static ArrayList<Move> getMoves(Board board){
        Checker[][] gameBoard = board.getBoard();
        ArrayList<Move> moves = new ArrayList<>();

        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j < gameBoard.length; j++){
                /*
                * TODO: Add Black King
                * */
//                if(gameBoard[i][j])
                if(gameBoard[i][j] != null && gameBoard[i][j].getType() == CheckerType.BLACK_REGULAR){
                    Move forwardLeftMove = getForwardLeft(gameBoard[i][j].getPosition(), board);
                    Move forwardRightMoves = getForwardRight(gameBoard[i][j].getPosition(), board);
                    if(forwardRightMoves != null){
                        moves.add(forwardRightMoves);
                    }
                    if(forwardLeftMove != null){
                        moves.add(forwardLeftMove);
                    }
                }
            }
        }
        return moves;
    }

    private static Move getForwardLeft(Position position, Board board){
        Checker[][] gameBoard = board.getBoard();
        if(position.getY() >= 1 && position.getX() >= 1) /*Valid Movement*/{
            //check if next cell is empty
            if(gameBoard[position.getX() - 1][position.getY() - 1] == null){
                //Empty cell yay!
                //return a new move with the new location
                return new Move(position, new Position(position.getX() - 1, position.getY() - 1));
            } else {
                // Occupied Cell
            }

        } else {
            //invalid shit
            return null;
        }
        return null;
    }

    private static Move getForwardRight(Position position, Board board){
        Checker[][] gameBoard = board.getBoard();
        if(position.getY() < gameBoard.length - 1 && position.getX() >= 1) /*Valid Movement*/{
            //check if next cell is empty
            if(gameBoard[position.getX() - 1][position.getY() + 1] == null){
                //Empty cell yay!
                //return a new move with the new location
                return new Move(position, new Position(position.getX() - 1, position.getY() + 1));
            } else {
                // Occupied Cell
            }

        } else {
            //invalid shit
            return null;
        }
        return null;
    }
}
