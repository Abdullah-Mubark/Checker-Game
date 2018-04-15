/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checker.game;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Abdullah
 */
public class Game {

    public static Board board;
    public static BoardEvaluation boardeval;
    public static Black black;
    public static White white;

    public Game() {
        board = new Board();
        boardeval = BoardEvaluation.getInstance();
        black = new Black();
        white = new White();
    }

    public void PlayGame() {

        while (true) {

            // Black's turn
            //************************************************************************************************
            black.calculateAllpossibleMoves(board);
            if (board.CheckGameDraw(PlayerType.BLACK)) {
                congratulateWinner(PlayerType.WHITE);
                break;
            }

            ArrayList<Move> movesForBlack = black.allowedMoves;
            //Move nextMoveBlack = movesForBlack.get((int) (Math.random() * movesForBlack.size()));

            //System.out.println("Moves avaliable are: (BLACK)");
            System.out.println("----------------------------");
//            for (Move m : movesForBlack) {
//                System.out.println(m.toString());
//            }
            Move nextMoveBlack = PickMove(movesForBlack);
            System.out.println("Board Evaluation is:" + boardeval.evaluateBoard(board, PlayerType.BLACK));
            System.out.println("----------------------------\n");

            board.makeMove(nextMoveBlack);
            board.Display();

            //check if black already won
            if (board.CheckGameComplete()) {
                congratulateWinner(PlayerType.BLACK);
                break;
            }
            //************************************************************************************************

            // White's turn
            //************************************************************************************************
            white.calculateAllpossibleMoves(board);
            if (board.CheckGameDraw(PlayerType.WHITE)) {
                congratulateWinner(PlayerType.BLACK);
                break;
            }
            ArrayList<Move> movesForWhite = white.allowedMoves;
            Move nextMoveWhite = new Move(new Position(0, 0), new Position(0, 0));//movesForWhite.get((int) (Math.random() * movesForWhite.size()));
            int max = Integer.MIN_VALUE;
            AlphaBeta ap = new AlphaBeta();
            for (Move m : movesForWhite) {
                int alpha = ap.alphaBeta(board, PlayerType.WHITE, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, m);
                System.out.println(alpha);
                if (alpha > max) {
                    max = alpha;
                    nextMoveWhite = m;
                }
            }

            System.out.println("Moves avaliable are: (White)");
            System.out.println("----------------------------");
            for (Move m : movesForWhite) {
                System.out.println(m.toString());
            }
            System.out.println("Board Evaluation is:" + boardeval.evaluateBoard(board, PlayerType.WHITE));
            System.out.println("----------------------------\n");

            board.makeMove(nextMoveWhite);
            board.Display();

            //check if white already won
            if (board.CheckGameComplete()) {
                congratulateWinner(PlayerType.WHITE);
                break;
            }
            //************************************************************************************************
        }

    }

    public Move PickMove(ArrayList<Move> moves) {
        Scanner s = new Scanner(System.in);
        System.out.println("Pick one of the following moves");
        for (int i = 0; i < moves.size(); i++) {
            System.out.println(i + ": " + moves.get(i).toString());
        }
        String input = s.nextLine();
        while (input.matches("") && Integer.parseInt(input) >= moves.size()) {
            System.out.println("Wrong input");
            input = s.nextLine();
        }
        return moves.get(Integer.parseInt(input));
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
