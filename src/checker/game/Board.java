/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

import java.util.ArrayList;

/**
 *
 * @author Kamal
 */
public class Board {

    private int blackCheckers;
    private int whiteCheckers;
    public static final int rows = 8;
    public static final int cols = 8;
    private Checker[][] board;

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

    public int getBlackCheckers() {
        return blackCheckers;
    }

    public void setBlackCheckers(int blackCheckers) {
        this.blackCheckers = blackCheckers;
    }

    public int getWhiteCheckers() {
        return whiteCheckers;
    }

    public void setWhiteCheckers(int whiteCheckers) {
        this.whiteCheckers = whiteCheckers;
    }

    public Board duplicate() {
        Board newBoard = new Board(this.board);
        newBoard.blackCheckers = this.blackCheckers;
        newBoard.whiteCheckers = this.whiteCheckers;

        return newBoard;
    }

    // make sure a move is valid
    public void makeMove(Move move) {
        int x1 = move.getIntialpos().getX();
        int y1 = move.getIntialpos().getY();
        int x2 = move.getFinalpos().getX();
        int y2 = move.getFinalpos().getY();
        CheckerType ct = board[x1][y1].getType();

        board[x2][y2] = new Checker(ct, new Position(x2, y2));
        board[x1][y1] = null;

        if (move.getIsCapture()) {
            MoveDir dir = x2 < x1 ? (y2 < y1 ? MoveDir.forwardLeft : MoveDir.forwardRight)
                    : (y2 < y1 ? MoveDir.backwardLeft : MoveDir.backwardRight);

            // Removing Piece from the board
            switch (dir) {
                case forwardRight:
                    board[x1 - 1][y1 + 1] = null;
                    break;
                case forwardLeft:
                    board[x1 - 1][y1 - 1] = null;
                    break;
                case backwardRight:
                    board[x1 + 1][y1 + 1] = null;
                    break;
                case backwardLeft:
                    board[x1 + 1][y1 - 1] = null;
                    break;
            }
            if (ct == CheckerType.BLACK_KING || ct == CheckerType.BLACK_REGULAR) {
                this.whiteCheckers--;
            } else {
                this.blackCheckers--;
            }
        }
        //promote to King
        if (ct == CheckerType.BLACK_REGULAR && x2 == 0) {
            board[x2][y2].setType(CheckerType.BLACK_KING);
        } else if (ct == CheckerType.WHITE_REGULAR && x2 == Board.rows - 1) {
            board[x2][y2].setType(CheckerType.WHITE_KING);
        }
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
        ArrayList<Move> possibleMoves;

        if (turn == PlayerType.BLACK) {
            Black black = new Black();
            black.calculateAllpossibleMoves(this);
            possibleMoves = black.allowedMoves;
        } else {
            White white = new White();
            white.calculateAllpossibleMoves(this);
            possibleMoves = white.allowedMoves;
        }

        return possibleMoves.isEmpty();
    }

    public Checker[][] getBoard() {
        return board;
    }

    public void setBoard(Checker[][] board) {
        this.board = board;
    }

    public boolean CheckerIsBlack(Checker c) {
        return c.getType() == CheckerType.BLACK_REGULAR || c.getType() == CheckerType.BLACK_KING;
    }

    public boolean CheckerIsWhite(Checker c) {
        return c.getType() == CheckerType.WHITE_REGULAR || c.getType() == CheckerType.WHITE_KING;
    }

    public boolean isWhiteWinner() {
        return blackCheckers == 0;
    }

    public boolean isBlackWinner() {
        return whiteCheckers == 0;
    }
}
