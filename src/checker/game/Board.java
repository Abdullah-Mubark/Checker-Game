/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

/**
 *
 * @author Kamal
 */
public class Board {

    private int blackCheckers;
    private int whiteCheckers;
    public static final int rows = 8;
    public static final int cols = 8;
    Checker[][] board;

    public Board() {
        this.blackCheckers = this.whiteCheckers = 12;

        this.board = new Checker[][]{
            {null, new Checker(CheckerType.WHITE_REGULAR, new Position(0, 1)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(0, 3)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(0, 5)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(0, 7))},
            {new Checker(CheckerType.WHITE_REGULAR, new Position(1, 0)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(1, 2)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(1, 4)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(1, 6)), null},
            {null, new Checker(CheckerType.WHITE_REGULAR, new Position(2, 1)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(2, 3)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(2, 5)), null, new Checker(CheckerType.WHITE_REGULAR, new Position(2, 7))},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {new Checker(CheckerType.BLACK_REGULAR, new Position(5, 0)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(5, 2)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(5, 4)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(5, 6)), null},
            {null, new Checker(CheckerType.BLACK_REGULAR, new Position(6, 1)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(6, 3)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(6, 5)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(6, 7))},
            {new Checker(CheckerType.BLACK_REGULAR, new Position(7, 0)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(7, 2)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(7, 4)), null, new Checker(CheckerType.BLACK_REGULAR, new Position(7, 6)), null},};
    }

    public Board(Checker[][] board) {
        this.blackCheckers = this.whiteCheckers = 12;

        this.board = new Checker[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, cols);
        }
    }

    public Board duplicate() {
        Board newBoard = new Board(this.board);
        newBoard.blackCheckers = this.blackCheckers;
        newBoard.whiteCheckers = this.whiteCheckers;

        return newBoard;
    }

    public void Display() {
        this.DisplayColIndex();
        this.DrawHorizontalLine();

        for (int i = 0; i < rows; i++) {
            this.DisplayRowIndex(i);
            this.DrawVerticalLine();

            for (int j = 0; j < cols; j++) {
                System.out.print(this.BoardPiece(i, j));
                this.DrawVerticalLine();
            }

            this.DisplayRowIndex(i);
            System.out.println();
            this.DrawHorizontalLine();
        }

        this.DisplayColIndex();
        System.out.println();
    }

    private void DisplayRowIndex(int i) {
        System.out.print(" " + i + " ");
    }

    private void DisplayColIndex() {
        System.out.print("   ");
        for (int colIndex = 0; colIndex < cols; colIndex++) {
            System.out.print("   " + colIndex + "  ");
        }
        System.out.println();
    }

    private void DrawHorizontalLine() {
        System.out.println("    _______________________________________________");
    }

    private void DrawVerticalLine() {
        System.out.print("|");
    }

    private String BoardPiece(int i, int j) {
        assert (i > 0 && i < rows && j > 0 && j < cols);
        String str = new String();

        if (this.board[i][j] == null) {
            str = "     ";
        } else if (this.board[i][j].getType() == CheckerType.WHITE_REGULAR) {
            str = "  W  ";
        } else if (this.board[i][j].getType() == CheckerType.BLACK_REGULAR) {
            str = "  B  ";
        } else if (this.board[i][j].getType() == CheckerType.WHITE_KING) {
            str = "  KW ";
        } else if (this.board[i][j].getType() == CheckerType.BLACK_KING) {
            str = "  KB ";
        }

        return str;
    }

    public boolean CheckGameComplete() {
        return (this.blackCheckers == 0 || this.whiteCheckers == 0);
    }

    public boolean CheckGameDraw(PlayerType turn) {
//        
//        ArrayList<Move> possibleMoveSeq = Robot.expandMoves(this.duplicate(), turn);
//        
//        if(possibleMoveSeq.isEmpty()){
//            return true;
//            
//        }else{
//            return false;
//        }
        return true;
    }

}
